package com.nick.money.envelopes.controller;

import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.nick.money.envelopes.data.Envelope;
import com.nick.money.envelopes.data.EnvelopeRepository;
import com.nick.money.envelopes.data.MonthlyEnvelope;
import com.nick.money.envelopes.data.MonthlyEnvelopeChange;
import com.nick.money.envelopes.data.MonthlyEnvelopeRepository;
import com.nick.money.envelopes.data.MonthlyEnvelopeRequest;
import com.nick.money.envelopes.data.MonthlyEnvelopeResponse;
import com.nick.money.envelopes.service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EnvelopeController {

    // TODO put a business service layer at some point
    @Autowired
    private EnvelopeRepository envelopeRepository;
    @Autowired
    private MonthlyEnvelopeRepository monthlyEnvelopeRepository;


    @Autowired
    private CardService cardService;

    @GetMapping("/envelope")
    public Iterable<Envelope> getEnvelopes() {
        log.info("Getting all envelopes");
        return envelopeRepository.findAll();
    }

    @PutMapping("/envelope")
    public Envelope putEnvelop(@RequestBody Envelope envelope) {
        log.info("Saving envelope: " + envelope);
        return envelopeRepository.save(envelope);
    }

    @GetMapping("/envelope/{id}")
    public Envelope getEnvelope(@PathVariable("id") String id) {
        log.info("Envelope request for id: " + id);
        return envelopeRepository.findById(id)
                .orElseThrow(() -> new EnvelopeNotFound("Item: " + id + " Not Found"));
    }

    @PostMapping("/envelope/{id}")
    public Envelope getEnvelope(@PathVariable("id") String id, @RequestBody Envelope envelope) {
        log.info("Updateing Envelope: " + envelope);
        envelope.setId(id);
        return envelopeRepository.save(envelope);
    }


    @GetMapping("/envelope/{id}/{year}")
    public List<MonthlyEnvelope> getYearlyEnvelopes(@PathVariable("id") String id, @PathVariable("year") long year) {
        log.info("getting year of envelopes " + year);
        return monthlyEnvelopeRepository.findByParentIdAndYear(id, year);
    }

    @GetMapping("/envelope/{id}/{year}/{month}")
    public MonthlyEnvelope getMonthlyEnvelope(@PathVariable("id") String id, @PathVariable("year") long year,
            @PathVariable("month") String month) {
                log.info("getting year/month of envelope "+id+" year:" + year+" month:"+month);
        return monthlyEnvelopeRepository.findByParentIdAndYearAndMonth(id, year, Month.valueOf(month).toString());
    }

    @PutMapping("/envelope/{id}")
    @SneakyThrows
    public MonthlyEnvelope createMonthlyEnvelope(@PathVariable("id") String id,
            @RequestBody MonthlyEnvelopeRequest monthlyEnvelope) {

       return envelopeRepository.findById(id).map(parent->{
        log.info("adding "+monthlyEnvelope+" to "+parent);
        val now = Calendar.getInstance();
        val month = new MonthlyEnvelope();
        month.setMonth(Month.of(now.get(Calendar.MONTH)+1).toString());
        month.setYear(Long.valueOf(now.get(Calendar.YEAR)));
        month.setParentId(id);
        month.setTotal(monthlyEnvelope.getTotal());
        month.setRemaining(monthlyEnvelope.getTotal());
        val van = cardService.createNewVan(parent.getPanId());
        month.setVanId(van.getId());
        month.setLastFour(van.getNumber().substring(11, 15));
        month.setExpDate(van.getExpMonth()<10?"0":""+van.getExpMonth()+"/"+van.getExpYear());
        return monthlyEnvelopeRepository.save(month);
        }).orElseThrow(() -> {
            throw new EnvelopeNotFound("Envelope with id: "+id + "not Found");
        });
    }

    @PostMapping("/envelope/{id}/current")
    @SneakyThrows
    public MonthlyEnvelope updateMonthlyEnvelope(@PathVariable("id") String id,
            @RequestBody MonthlyEnvelopeChange monthlyEnvelope) {
                val now = Calendar.getInstance();
       val toUpdate = monthlyEnvelopeRepository.findByParentIdAndYearAndMonth(id, Long.valueOf(now.get(Calendar.YEAR)),
       Month.of(now.get(Calendar.MONTH)+1).toString());
      toUpdate.setRemaining(monthlyEnvelope.getRemaining());
      return monthlyEnvelopeRepository.save(toUpdate);
    }

    @GetMapping("/envelope/current")
    @SneakyThrows
    public List<MonthlyEnvelopeResponse> getEnvelopesPerMonth() {
        val now = Calendar.getInstance();
        return monthlyEnvelopeRepository.findByYearAndMonth(Long.valueOf(now.get(Calendar.YEAR)),
                Month.of(now.get(Calendar.MONTH)+1).toString()).stream().map(me->{
                    val mer = new MonthlyEnvelopeResponse();
                    mer.setExpDate(me.getExpDate());
                    mer.setId(me.getId());
                    mer.setLastFour(me.getLastFour());
                    mer.setMonth(me.getMonth());
                    mer.setRemaining(me.getRemaining());
                    mer.setTotal(me.getTotal());
                    mer.setVanId(me.getVanId());
                    mer.setYear(me.getYear());
                    return extracted(me, mer);
                }).collect(Collectors.toList());
    }
    @SneakyThrows
	private MonthlyEnvelopeResponse extracted(MonthlyEnvelope me,
			final com.nick.money.envelopes.data.MonthlyEnvelopeResponse mer) {
		return envelopeRepository.findById(me.getParentId()).map( (p)->{
		    mer.setPaymentEnvelope(p);
            return mer;
        }).orElseThrow(() -> {
            throw new EnvelopeNotFound("parent missing for id " + me.getId());
        });
    }
}
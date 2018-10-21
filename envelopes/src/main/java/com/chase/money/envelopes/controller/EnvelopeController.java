package com.chase.money.envelopes.controller;

import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.chase.money.envelopes.data.Envelope;
import com.chase.money.envelopes.data.EnvelopeRepository;
import com.chase.money.envelopes.data.MonthlyEnvelope;
import com.chase.money.envelopes.data.MonthlyEnvelopeRepository;
import com.chase.money.envelopes.data.MonthlyEnvelopeRequest;
import com.chase.money.envelopes.data.MonthlyEnvelopeResponse;

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
        month.setVanId(123l);
        month.setLastFour("4231");
        month.setExpDate("12/22");
        return monthlyEnvelopeRepository.save(month);
        }).orElseThrow(() -> {
            throw new EnvelopeNotFound("Envelope with id: "+id + "not Found");
        });
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
			final com.chase.money.envelopes.data.MonthlyEnvelopeResponse mer) {
		return envelopeRepository.findById(me.getParentId()).map( (p)->{
		    mer.setPaymentEnvelope(p);
            return mer;
        }).orElseThrow(() -> {
            throw new EnvelopeNotFound("parent missing for id " + me.getId());
        });
    }
}
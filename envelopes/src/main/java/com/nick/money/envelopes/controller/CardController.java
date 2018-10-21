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
import com.nick.money.envelopes.data.Pan;
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
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/pan")
    public Iterable<Pan> getPans() {
        return cardService.getPans();
    }

    @PutMapping("/pan")
    public Pan putPan(@RequestBody Pan pan) {
        return cardService.newPan(pan);
    }
}
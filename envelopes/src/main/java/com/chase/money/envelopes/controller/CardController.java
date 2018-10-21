package com.chase.money.envelopes.controller;

import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.chase.money.envelopes.data.Envelope;
import com.chase.money.envelopes.data.EnvelopeRepository;
import com.chase.money.envelopes.data.MonthlyEnvelope;
import com.chase.money.envelopes.data.MonthlyEnvelopeChange;
import com.chase.money.envelopes.data.MonthlyEnvelopeRepository;
import com.chase.money.envelopes.data.MonthlyEnvelopeRequest;
import com.chase.money.envelopes.data.MonthlyEnvelopeResponse;
import com.chase.money.envelopes.data.Pan;
import com.chase.money.envelopes.service.CardService;

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
package com.nick.money.envelopes.service;

import com.nick.money.envelopes.data.Pan;
import com.nick.money.envelopes.data.PanRepository;
import com.nick.money.envelopes.data.Van;
import com.nick.money.envelopes.data.VanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.val;

@Service
public class CardService {

    @Autowired
    private VanRepository vanRepo;

    @Autowired
    private PanRepository panRepo;

    @SneakyThrows
    public Van createNewVan(String panId) {

        return panRepo.findById(panId).map(pan -> {
            val van = new Van();
            van.setCardholderLastName(pan.getCardholderLastName());
            van.setCarholderFirstName(pan.getCarholderFirstName());
            van.setExpMonth(pan.getExpMonth());
            van.setExpYear(pan.getExpYear());
            val fake = (long) (Math.random() * 1000000000000000L) + 4000000000000000L;
            van.setNumber(String.valueOf(fake));
            return vanRepo.save(van);

        }).orElseThrow(() -> {
            throw new CardNotFound("pan with id: " + panId + " not found");
        });

    }

    public Pan newPan(Pan pan) {
        return panRepo.save(pan);
    }

    public Iterable<Pan> getPans() {
        return panRepo.findAll();
    }

    @SneakyThrows
    public Pan getPan(String id) {
        return panRepo.findById(id).orElseThrow(() -> {
            throw new CardNotFound("pan with id: " + id + " not found");
        });
    }
    @SneakyThrows
    public Van getVan(String id) {
        return vanRepo.findById(id).orElseThrow(() -> {
            throw new CardNotFound("van with id: " + id + " not found");
        });
    }
}
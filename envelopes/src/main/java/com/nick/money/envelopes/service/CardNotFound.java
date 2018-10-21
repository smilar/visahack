package com.nick.money.envelopes.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFound extends RuntimeException {

    public CardNotFound(String string) {
        super(string);
	}

	private static final long serialVersionUID = 4551895476987397739L;
}
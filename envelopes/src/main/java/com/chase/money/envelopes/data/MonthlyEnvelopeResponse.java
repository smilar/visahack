package com.chase.money.envelopes.data;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MonthlyEnvelopeResponse {

    
    private String id;

    
    @NotNull
    private Envelope paymentEnvelope;

    @NotNull
    private String month;

    @NotNull
    @Min(1970)
    @Max(2990)
    private Long year;

    @Min(0)
    @NotNull
    private BigDecimal total;

    @NotNull
    private BigDecimal remaining;

    @NotNull
    private Long vanId;

    @NotNull
    @Length(max=4,min=4)
    private String lastFour;
    
    @NotNull
    @Length(max=5,min=5)
    private String expDate;

}
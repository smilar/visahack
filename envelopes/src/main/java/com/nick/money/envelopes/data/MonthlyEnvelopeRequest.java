package com.nick.money.envelopes.data;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MonthlyEnvelopeRequest{
    private BigDecimal total;
}
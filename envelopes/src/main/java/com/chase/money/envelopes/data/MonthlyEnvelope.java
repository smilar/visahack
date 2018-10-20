package com.chase.money.envelopes.data;

import java.math.BigDecimal;
import java.time.Month;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class MonthlyEnvelope{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @NotNull
    private Envelope parentEnvelope;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Month month;
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
}
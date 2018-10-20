package com.chase.money.envelopes.data;

import java.time.Month;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyEnvelopeRepository extends JpaRepository<MonthlyEnvelope, Long> {

    List<MonthlyEnvelope> findByParentEnvelopeIdAndYear(long id, long year);
    
	MonthlyEnvelope findByParentEnvelopeIdAndYearAndMonth(long id, long year,Month month);
	List<MonthlyEnvelope> findByYearAndMonth(long year,Month month);


}
package com.chase.money.envelopes.data;

import java.time.Month;
import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MonthlyEnvelopeRepository extends CrudRepository<MonthlyEnvelope, Long> {

    List<MonthlyEnvelope> findByParentIdAndYear(long id, long year);
    
	MonthlyEnvelope findByParentIdAndYearAndMonth(long id, long year,Month month);
	List<MonthlyEnvelope> findByYearAndMonth(long year,Month month);


}
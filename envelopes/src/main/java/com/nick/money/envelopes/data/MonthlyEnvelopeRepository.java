package com.nick.money.envelopes.data;

import java.time.Month;
import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MonthlyEnvelopeRepository extends CrudRepository<MonthlyEnvelope, String> {

    List<MonthlyEnvelope> findByParentIdAndYear(String id, long year);
    
	MonthlyEnvelope findByParentIdAndYearAndMonth(String id, long year,String month);
	List<MonthlyEnvelope> findByYearAndMonth(long year,String month);


}
package com.nick.money.envelopes.data;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
@EnableScan
public interface VanRepository extends CrudRepository<Van, String> {

}
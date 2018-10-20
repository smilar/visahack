package com.chase.money.envelopes;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories
public class EnvelopesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnvelopesApplication.class, args);
	}
}

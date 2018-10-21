package com.visa.innovation.virtualaccountmanagement.repository;

import com.visa.innovation.virtualaccountmanagement.entity.PaymentAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAccountsRepository extends CrudRepository<PaymentAccount, Long> {
}

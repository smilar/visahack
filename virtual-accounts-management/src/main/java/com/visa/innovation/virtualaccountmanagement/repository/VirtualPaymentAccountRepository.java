package com.visa.innovation.virtualaccountmanagement.repository;

import com.visa.innovation.virtualaccountmanagement.entity.VirtualPaymentAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualPaymentAccountRepository extends CrudRepository<VirtualPaymentAccount, Long> {
}

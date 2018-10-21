package com.visa.innovation.virtualaccountmanagement.service;

import com.visa.innovation.virtualaccountmanagement.entity.PaymentAccount;
import com.visa.innovation.virtualaccountmanagement.entity.VirtualPaymentAccount;
import com.visa.innovation.virtualaccountmanagement.repository.PaymentAccountsRepository;
import com.visa.innovation.virtualaccountmanagement.repository.VirtualPaymentAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PaymentAccountService {


    private final PaymentAccountsRepository accountsRepository;
    private final VirtualPaymentAccountRepository virtualPaymentAccountRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public PaymentAccountService(PaymentAccountsRepository accountsRepository, VirtualPaymentAccountRepository virtualPaymentAccountRepository) {
        this.accountsRepository = accountsRepository;
        this.virtualPaymentAccountRepository = virtualPaymentAccountRepository;
    }


    public PaymentAccount addNewPanAccount(PaymentAccount paymentAccount) {

        if (!accountsRepository.findById(paymentAccount.getPaymentAccountId()).isPresent()) {
            accountsRepository.save(paymentAccount);
        }


        return paymentAccount;

    }

    public VirtualPaymentAccount generateNewToken(PaymentAccount paymentAccount) {

        if (!accountsRepository.findById(paymentAccount.getPaymentAccountId()).isPresent()) {
            accountsRepository.save(paymentAccount);
        }

        VirtualPaymentAccount virtualPaymentAccount = new VirtualPaymentAccount((long) (Math.random() * 1000000000000000L), paymentAccount);
        virtualPaymentAccountRepository.save(virtualPaymentAccount);

        return virtualPaymentAccount;


    }

    public List<VirtualPaymentAccount> getVirtualAccounts(Long payment_account_id) {

        Optional<PaymentAccount> paymentAccount = accountsRepository.findById(payment_account_id);
        if (paymentAccount.isPresent()) {

            Set<VirtualPaymentAccount> virtualPaymentAccounts = paymentAccount.get().getVirtualPaymentAccounts();
            logger.info("---> {}", virtualPaymentAccounts);

            List<VirtualPaymentAccount> virtualPaymentAccountList = new ArrayList<>();

            VirtualPaymentAccount virtualPaymentAccount;
            for (VirtualPaymentAccount v : virtualPaymentAccounts
            ) {

                virtualPaymentAccount = new VirtualPaymentAccount(v.getVirtualPaymentAccountId(), null);
                virtualPaymentAccountList.add(virtualPaymentAccount);
            }


            return virtualPaymentAccountList;
        }
        return null;
    }
}

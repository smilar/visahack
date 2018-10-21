package com.visa.innovation.virtualaccountmanagement.web;


import com.visa.innovation.virtualaccountmanagement.entity.PaymentAccount;
import com.visa.innovation.virtualaccountmanagement.entity.VirtualPaymentAccount;
import com.visa.innovation.virtualaccountmanagement.service.PaymentAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountManagementController {


    private final PaymentAccountService paymentAccountService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AccountManagementController(PaymentAccountService paymentAccountService) {
        this.paymentAccountService = paymentAccountService;
    }


    @PostMapping("/payment-account")
    public PaymentAccount addNewPanAccount(@RequestBody PaymentAccount paymentAccount) {
        return paymentAccountService.addNewPanAccount(paymentAccount);
    }


    @PostMapping("/virtual-account")
    public VirtualPaymentAccount generateVirtualAccount(@RequestBody PaymentAccount paymentAccount) {
        return paymentAccountService.generateNewToken(paymentAccount);
    }

    @GetMapping("/payment-account/{id}")
    public List<VirtualPaymentAccount> getAssociatedVirtualAccounts(@PathVariable("id") Long payment_account_id) {
        return paymentAccountService.getAssoicatedVirtualAccounts(payment_account_id);
    }


}

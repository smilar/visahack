package com.visa.innovation.virtualaccountmanagement.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment_accounts")
public class PaymentAccount {

    @Id
    @Column(name = "payment_account_id")
    private Long paymentAccountId;
    private Integer verificationCode;
    private String clientIdentifier;
    @OneToMany(mappedBy = "paymentAccount")
    private Set<VirtualPaymentAccount> virtualPaymentAccounts = new HashSet<>();

    public PaymentAccount() {
    }

    public PaymentAccount(Long paymentAccountId, Integer verificationCode, String clientIdentifier) {
        this.paymentAccountId = paymentAccountId;
        this.verificationCode = verificationCode;
        this.clientIdentifier = clientIdentifier;
    }

    public Long getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(Long paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public Set<VirtualPaymentAccount> getVirtualPaymentAccounts() {
        return virtualPaymentAccounts;
    }

    public void setVirtualPaymentAccounts(Set<VirtualPaymentAccount> virtualPaymentAccounts) {
        this.virtualPaymentAccounts = virtualPaymentAccounts;
    }

    @Override
    public String toString() {
        return "PaymentAccount{" +
                ", paymentAccountId=" + paymentAccountId +
                ", verificationCode=" + verificationCode +
                ", clientIdentifier='" + clientIdentifier + '\'' +
                '}';
    }
}



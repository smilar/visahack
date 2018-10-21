package com.visa.innovation.virtualaccountmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "virtual_payment_accounts")
public class VirtualPaymentAccount {

    @Id
    @Column(name = "virtual_payment_account_id")
    private Long virtualPaymentAccountId;
    @ManyToOne
    @JoinColumn(name = "payment_account_id")
    @JsonIgnore
    private PaymentAccount paymentAccount;

    public VirtualPaymentAccount() {
    }

    public VirtualPaymentAccount(Long virtualPaymentAccountId, PaymentAccount paymentAccount) {
        this.virtualPaymentAccountId = virtualPaymentAccountId;
        this.paymentAccount = paymentAccount;
    }

    public Long getVirtualPaymentAccountId() {
        return virtualPaymentAccountId;
    }

    public void setVirtualPaymentAccountId(Long virtualPaymentAccountId) {
        this.virtualPaymentAccountId = virtualPaymentAccountId;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "VirtualPaymentAccount{" +
                "virtualPaymentAccountId=" + virtualPaymentAccountId +
                ", paymentAccount=" + paymentAccount +
                '}';
    }
}

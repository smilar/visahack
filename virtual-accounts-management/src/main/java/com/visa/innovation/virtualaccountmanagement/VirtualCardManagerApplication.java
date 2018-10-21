package com.visa.innovation.virtualaccountmanagement;

import com.visa.innovation.virtualaccountmanagement.entity.PaymentAccount;
import com.visa.innovation.virtualaccountmanagement.entity.VirtualPaymentAccount;
import com.visa.innovation.virtualaccountmanagement.repository.PaymentAccountsRepository;
import com.visa.innovation.virtualaccountmanagement.repository.VirtualPaymentAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VirtualCardManagerApplication {

    private final PaymentAccountsRepository accountsRepository;
    private final VirtualPaymentAccountRepository virtualPaymentAccountRepository;
    private Logger LOG = LoggerFactory.getLogger("Application");


    public VirtualCardManagerApplication(PaymentAccountsRepository accountsRepository, VirtualPaymentAccountRepository virtualPaymentAccountRepository) {
        this.accountsRepository = accountsRepository;
        this.virtualPaymentAccountRepository = virtualPaymentAccountRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(VirtualCardManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {


            PaymentAccount paymentAccount1 = new PaymentAccount(441122111231131L, 123, "A1234");

            PaymentAccount paymentAccount2 = new PaymentAccount(441122111231151L, 133, "B1334");

            PaymentAccount paymentAccount3 = new PaymentAccount(441122161231151L, 143, "C1334");


            VirtualPaymentAccount virtualPaymentAccount = new VirtualPaymentAccount(515122111231131L, paymentAccount1);
            // virtualPaymentAccount = new VirtualPaymentAccount(615122111231131L, paymentAccount1);
            //virtualPaymentAccount = new VirtualPaymentAccount(715122111231131L, paymentAccount1);


            accountsRepository.save(paymentAccount1);
            accountsRepository.save(paymentAccount2);
            accountsRepository.save(paymentAccount3);

            virtualPaymentAccountRepository.save(virtualPaymentAccount);


            LOG.info("Total accounts count {} ", accountsRepository.count());


            for (PaymentAccount paymentAccount : accountsRepository.findAll()
            ) {
                LOG.info(paymentAccount.toString());
            }


            for (VirtualPaymentAccount v : virtualPaymentAccountRepository.findAll()
            ) {
                LOG.info(v.toString());
                LOG.info(v.getPaymentAccount().toString());
            }


        };

    }

}

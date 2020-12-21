package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DubboClientApplication {

    @Autowired
    @Qualifier("accountAService")
    private AccountService consumerService;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private AccountService providerService;

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            this.fxTransaction();
        };
    }

    public void fxTransaction() {
        try {
            Account account = new Account();
            account.setUserId(1l);
            account.setRmbAmount(new BigDecimal(-7));
            account.setDollarAmount(new BigDecimal(1));
            consumerService.pay(account);

            account = new Account();
            account.setUserId(2l);
            account.setRmbAmount(new BigDecimal(7));
            account.setDollarAmount(new BigDecimal(-1));
            providerService.pay(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

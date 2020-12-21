package io.kimmking.dubbo.demo.api;

public interface AccountDao {
    Account get(Long id);

    void pay(Account account);

//    void cancelPay(Account account);

    void updateFreezeAmount(Account account);
}

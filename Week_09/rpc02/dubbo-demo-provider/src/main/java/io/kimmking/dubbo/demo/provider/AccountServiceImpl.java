package io.kimmking.dubbo.demo.provider;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountDao;
import io.kimmking.dubbo.demo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@DubboService(version = "1.0.0")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountDao accountDao;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void pay(Account account) throws Exception {
        LOG.info("provider.pay()");
        Account acc = accountDao.get(account.getUserId());
        // 如果扣减的金额不足，抛出异常
        if(account.getDollarAmount().abs().compareTo(acc.getDollarAmount()) == 1) {
            throw new Exception();
        }
        account.setFreezeAmount(new BigDecimal(1));
        accountDao.updateFreezeAmount(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirm(Account account) {
        LOG.info("provider.confirm()");
        accountDao.pay(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancel(Account account) {
        LOG.info("provider.cancel()");
        account.setFreezeAmount(new BigDecimal(0));
        accountDao.updateFreezeAmount(account);
    }
}

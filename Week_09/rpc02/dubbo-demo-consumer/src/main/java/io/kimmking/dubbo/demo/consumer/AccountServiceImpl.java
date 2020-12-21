package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountDao;
import io.kimmking.dubbo.demo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

//@DubboService(version = "1.0.0")
@Service
@Qualifier("accountAService")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountDao accountDao;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void pay(Account account) throws Exception {
        LOG.info("consumer.pay()");
        Account acc = accountDao.get(account.getUserId());
        // 如果扣减的金额不足，抛出异常
        if(account.getRmbAmount().abs().compareTo(acc.getRmbAmount()) == 1) {
            throw new Exception();
        }
        account.setFreezeAmount(new BigDecimal(7));
        accountDao.updateFreezeAmount(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirm(Account account) {
        LOG.info("consumer.confirm()");
        accountDao.pay(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancel(Account account) {
        LOG.info("consumer.cancel()");
        account.setFreezeAmount(new BigDecimal(0));
        accountDao.updateFreezeAmount(account);
    }
}

package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account get(Long id) {
        String sql = "select dollar_amount, rmb_amount, freeze_amount from account_t where user_id=?";
        Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), id);
        return account;
    }

    @Override
    public void pay(Account account) {
        String sql = "update account_t set dollar_amount=dollar_amount+?,rmb_amount=rmb_amount+? where user_id=?";
        jdbcTemplate.update(sql, new Object[]{account.getDollarAmount(), account.getRmbAmount(), account.getUserId()});
    }

    @Override
    public void updateFreezeAmount(Account account) {
        String sql = "update account_t set freeze_amount=? where user_id=?";
        jdbcTemplate.update(sql, new Object[]{account.getFreezeAmount(), account.getUserId()});
    }
}

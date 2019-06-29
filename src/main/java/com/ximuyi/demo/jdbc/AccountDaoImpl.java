package com.ximuyi.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/***
 * @Repository 居然可以不需要写，从启动服务器的时候bean冲突可以猜测出来，但是运行会报错~
 */
@Repository
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Account account) {
        return jdbcTemplate.update("insert into account(name, money) values(?, ?)", account.getName(),account.getMoney());
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE  account SET NAME=? ,money=? WHERE id=?", account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from TABLE account where id=?",id);
    }

    @Override
    public Account findAccountById(int id) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Account.class));
        return accounts == null ||accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public List<Account> findAccountList() {
        List<Account> accounts = jdbcTemplate.query("select * from account", new Object[]{}, new BeanPropertyRowMapper(Account.class));
        return accounts != null ? accounts : new ArrayList<>();
    }
}

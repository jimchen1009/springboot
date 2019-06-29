package com.ximuyi.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    /**
     * Field accountDAO in com.ximuyi.demo.jdbc.AccountService required a single bean, but 2 were found:
     * 	- accountDaoImpl: defined in file [D:\demo\springboot\target\classes\com\ximuyi\demo\jdbc\AccountDaoImpl.class]
     * 	- IAccountDao: defined in file [D:\demo\springboot\target\classes\com\ximuyi\demo\jdbc\IAccountDao.class]
     * 为什么会出现这个问题呢？ IAccountDao仅仅是一个接口而已~
     * 方案：
     *  1. @Qualifier("accountDaoImpl")
     *  2. accountDao 改成 accountDaoImpl
     * */
    @Autowired
    IAccountDao accountDaoImpl;

    @Override
    public int add(Account account) {
        return accountDaoImpl.add(account);
    }

    @Override
    public int update(Account account) {
        return accountDaoImpl.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDaoImpl.delete(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoImpl.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return accountDaoImpl.findAccountList();
    }
}

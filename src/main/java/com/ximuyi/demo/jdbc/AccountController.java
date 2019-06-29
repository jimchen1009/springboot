package com.ximuyi.demo.jdbc;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getAccounts(){
        return accountService.findAccountList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  Account getAccountById(@PathVariable("id") int id){
        return accountService.findAccountById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name", required = true)String name,
                                 @RequestParam(value = "money" ,required = true) double money){
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t = accountService.update(account);
        return t == 1 ? account.toString() : "fail";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount( @RequestParam(value = "name")String name, @RequestParam(value = "money" )double money){
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        int t = accountService.add(account);
        return t == 1 ? account.toString() : "fail";
    }
}

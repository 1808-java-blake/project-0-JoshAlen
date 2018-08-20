package com.revature.daos;

import com.revature.beans.Account;

import java.util.List;

public interface AccountDao {
    public static final AccountDao currentAccountDao = new AccountDaoJdbc();

    int createAccount(Account a);

    List<Account> findByUserId(int userId);

    void deposit(double amount);
    void withdraw(double amount);

}

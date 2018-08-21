package com.revature.daos;

import com.revature.beans.Account;

import java.util.List;

public interface AccountDao {
    public static final AccountDao currentAccountDao = new AccountDaoJdbc();

    void createAccount(String accountType, double initDeposit, int userId);

    List<Account> findByUserId(int userId);

    void deposit(double amount);
    void withdraw(double amount);

}

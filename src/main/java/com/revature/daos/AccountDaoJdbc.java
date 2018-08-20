package com.revature.daos;

import com.revature.beans.Account;
import com.revature.util.ConnectionUtil;

import java.util.List;

public class AccountDaoJdbc implements AccountDao {
    private ConnectionUtil cu = ConnectionUtil.cu;

    public int createAccount(Account a){
        return 0;
    }

    public List<Account> findByUserId(int userId){

        return null;
    }

    public void deposit(double amount){

    }

    public void withdraw(double amount){

    }
}


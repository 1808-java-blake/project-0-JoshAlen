package com.revature.daos;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoJdbc implements AccountDao {
    private ConnectionUtil cu = ConnectionUtil.cu;

    @Override
    public void createAccount(String accountType, double initDeposit, int userId) {
        try(Connection conn = cu.getConnection()){

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO bank_account (type, balance, user_id) VALUES (?, ?, ?)"
            );

            ps.setString(1, accountType);
            ps.setDouble(2, initDeposit);
            ps.setInt(3, userId);


            int recordCreated = ps.executeUpdate();
            System.out.println("return data: " + recordCreated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Account> findByUserId(int userId){
        try (Connection conn = cu.getConnection()) {
            List<Account> accounts = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM bank_account WHERE user_id=?"
            );

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Account a = new Account();

                a.setAccountId(rs.getInt("account_id"));
                a.setBalance(rs.getDouble("balance"));
                a.setActivityLog(rs.getString("activity_log"));
                a.setType(rs.getString("type"));
                a.setCreateAt(rs.getString("create_at"));

                accounts.add(a);
            }
            return accounts;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public void deposit(double amount){

    }

    public void withdraw(double amount){

    }
}


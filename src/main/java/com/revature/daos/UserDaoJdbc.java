package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {

    private ConnectionUtil cu = ConnectionUtil.cu;
    private Logger log = Logger.getRootLogger();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(User u) {
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO bank_user (username, password, age, firstname, lastname) VALUES (?,?,?,?,?)");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getAge());
            ps.setString(4, u.getFirstName());
            ps.setString(5, u.getLastName());
            int recordsCreated = ps.executeUpdate();
            log.trace(recordsCreated + " records created");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
            for(StackTraceElement ste: e.getStackTrace()) {
                log.error(ste);
            }
            log.warn("failed to create new user");
        }

    }



    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try (Connection conn = cu.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM bank_user WHERE username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                User u = new User();
                u.setAge(rs.getInt("age"));
                u.setFirstName(rs.getString("firstname"));
                u.setLastName(rs.getString("lastname"));
                u.setUsername(rs.getString("username"));
                u.setId(rs.getInt("user_id"));
                u.setAdmin(rs.getBoolean("is_admin"));
                u.setCreateAt(rs.getString("create_at"));
                u.setBalance(rs.getDouble("balance"));
                u.setLog(rs.getString("log"));
                return u;
            } else {

                log.warn("failed to find user with provided credentials from the db");
                return null;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deposit(double amount, User user) {

        try (Connection conn = cu.getConnection()){
            user.setBalance(user.getBalance() + amount);

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE bank_user SET balance = ? WHERE user_id= ?"
            );
            ps.setDouble(1, user.getBalance());
            ps.setInt(2, user.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(double amount, User user){
        try (Connection conn = cu.getConnection()){
            user.setBalance(user.getBalance() - amount);

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE bank_user SET balance = ? WHERE user_id= ?; "
            );
            ps.setDouble(1, user.getBalance());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransactionLog(String log, User user){
        try (Connection conn = cu.getConnection()){
            user.setLog(user.getLog() + log + "\n");

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE bank_user SET log = ? WHERE user_id= ?; "
            );
            ps.setString(1, user.getLog());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
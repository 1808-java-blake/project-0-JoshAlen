package com.revature.beans;

public class Account {
    private int accountId;
    private String type;
    private double balance;
    private String activityLog;
    private String createAt;

    public Account(){
        super();
    }

    public Account(int accountId, String type, double balance, String activityLog, String createAt) {
        this.accountId = accountId;
        this.type = type;
        this.balance = balance;
        this.activityLog = activityLog;
        this.createAt = createAt;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(String activityLog) {
        this.activityLog = activityLog;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}


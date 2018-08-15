package com.revature.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private Map<String, String> accounts = new HashMap<>();

    public Map<String, String> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, String> accounts) {
        this.accounts = accounts;
    }
}

package com.memorisehelper.user;

import java.util.List;
import java.util.Map;

public class User {
    
    private String userName;
    private Map<String, String> currentLibrary;
    private List<String> userLibraries;

    public Map<String, String> getCurrentLibraries() {
        return currentLibrary;
    }

    public List<String> getUserLibraries() {
        return userLibraries;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setCurrentLibrary(Map<String, String> currentLibrary) {
        this.currentLibrary = currentLibrary;
    }
}

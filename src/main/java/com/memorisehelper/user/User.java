package com.memorisehelper.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.memorisehelper.filesystem.DiskWorker;

public class User {
    
    private String userName;
    private Map<String, String> currentLibrary;
    private List<String> userLibraries;
    private final static User USER = new User();

    private User() {}

    public static User getUser() {
        return USER;
    }

    public Map<String, String> getCurrentLibraries() {
        return currentLibrary;
    }

    public List<String> getUserLibraries() {
        return userLibraries;
    }

    public String getUserName() {
        return userName;
    }

    public void setCurrentLibrary(Map<String, String> currentLibrary) {
        this.currentLibrary = currentLibrary;
    }

    public void setName(String userName) throws IOException {
        this.userName = userName;
        this.userLibraries = new DiskWorker().getUserLibraries();
    }
}

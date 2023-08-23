package com.memorisehelper.user;

import java.io.IOException;
import java.util.List;

import com.memorisehelper.filesystem.DiskWorker;

public class User {
    
    private String userName;
    private Library library = Library.getInstance();
    private List<String> userLibraries;
    private final static User USER = new User();

    private User() {}

    public static User getUser() {
        return USER;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserLibraryName(String libraryName) {
        library.setLibraryName(libraryName);
    }

    public String getUserLibraryName() {
        return library.getLibraryName();
    }

    public void setUserName(String userName) throws IOException {
        this.userName = userName;
        this.userLibraries = new DiskWorker().getUserLibraries();
    }

    public List<String> getUserLibraries() {
        return userLibraries;
    }
    public void setUserLibraries(List<String> userLibraries) {
        this.userLibraries = userLibraries;
    }
}

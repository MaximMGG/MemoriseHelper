package com.memorisehelper.user;

import java.util.Map;

public class Library {

    private static final Library INSTANCE = new Library();

    private Map<String, String> currentLibrary;
    private String libraryName;

    private Library() {}

    public Map<String, String> getCurrentLibrary() {
        return currentLibrary;
    }

    public void setCurrentLibrary(Map<String, String> currentLibrary) {
        this.currentLibrary = currentLibrary;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public static Library getInstance() {
        return INSTANCE;
    }

}

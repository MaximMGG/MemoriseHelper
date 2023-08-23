package com.memorisehelper.user;

import java.util.HashMap;
import java.util.List;
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

    public void put(String word, String translarion) {
        currentLibrary.put(word, translarion);
    }

    public void remove(String key) {
        currentLibrary.remove(key);
    }

    public Map<String, String> parseLibrary(List<String> listLibrary) {
        Map<String, String> lib = new HashMap<>();
        for(String s : listLibrary) {
            String[] buffer = s.split(" : ");
            currentLibrary.put(buffer[0], buffer[1]);
        }
        return lib;
    }

    public void printLibrary() {
        int count = 1;
        for (Map.Entry<String, String> entry : currentLibrary.entrySet()) {
            System.out.println(count + ". " + entry.getKey() + " : " + entry.getValue());
        }
    }
}

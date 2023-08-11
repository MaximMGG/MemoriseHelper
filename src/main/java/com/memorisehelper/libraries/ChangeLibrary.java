package com.memorisehelper.libraries;

import java.util.Map;

import com.memorisehelper.utils.MemoriseUtils;

public class ChangeLibrary {
    
    private Map<String, String> currentLibrary;
    private Map<Integer, String> changedLibrary;

    public ChangeLibrary(Map<String, String> currentLibrary) {
        this.currentLibrary = currentLibrary;
        muteCurrentLibrary();
    }

    private void muteCurrentLibrary() {
        int i = 1;
        for(Map.Entry<String, String> entry : currentLibrary.entrySet()) {
            changedLibrary.put(i, entry.getKey() + " : " + entry.getValue());
        }
    }

    private void printChangedLibrary() {
        for(Map.Entry<Integer, String> entry : changedLibrary.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public void doChanges() {
        printChangedLibrary();
        System.out.println("Please wrigth down number of word that you want to change");
        changeWord(MemoriseUtils.writeInt());
    }

    private void changeWord(int wordPosition) {
        String tr = changedLibrary.get(wordPosition);
        System.out.println("What do you want to change, word or translation?");
        System.out.println("1. word\n2. translation");
        if 
    }


}

package com.memorisehelper.libraries;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.memorisehelper.utils.MemoriseUtils;

public class ChangeLibrary {
    
    private Scanner scan = new Scanner(System.in);
    private Map<String, String> currentLibrary;
    private Map<Integer, String> changedLibrary;

    public ChangeLibrary(Map<String, String> currentLibrary) throws IOException {
        this.currentLibrary = currentLibrary;
        muteCurrentLibrary();
        doChanges(); 
    }

    private void muteCurrentLibrary() {
        int i = 1;
        for(Map.Entry<String, String> entry : currentLibrary.entrySet()) {
            changedLibrary.put(i, entry.getKey() + " : " + entry.getValue());
            i++;
        }
    }

    private void printChangedLibrary() {
        for(Map.Entry<Integer, String> entry : changedLibrary.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public void doChanges() throws IOException {
        printChangedLibrary();
        System.out.println("Please wrigth down number of word that you want to change");
        changeWord(MemoriseUtils.writeInt());
    }

    private void changeWord(int wordPosition) throws IOException {
        String tr = changedLibrary.get(wordPosition);
        System.out.println("What do you want to change, word or translation?");
        System.out.println("1. word\n2. translation");
        if (MemoriseUtils.yesNo()) {
           System.out.println("Please, wright the word");
           String word = scan.nextLine();
        }   
    }

    private String parsChangedMapTranslations(int index) { 
        return "";
    }


}

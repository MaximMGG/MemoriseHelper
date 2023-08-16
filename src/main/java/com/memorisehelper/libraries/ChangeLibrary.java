package com.memorisehelper.libraries;

import java.io.IOException;
import java.util.List;
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
        int index = MemoriseUtils.writeInt();
        System.out.println("What do you want to change? \n1. Etire word and translation" + 
                " or 2. Translation ar 3. Delete word and translation or 4. Go back to menu");
        switch (MemoriseUtils.writeInt()) {
            case 1 -> changeWord(index);
            case 2 -> changeTranslation(index);
            case 3 -> deleteWord(index);
            case 4 -> 
            default -> {
                System.out.println("Sorry, but we do not have this option");
                doChanges();
            }
        }

    }

    private Object deleteWord(int index) {
        return null;
    }

    private Object changeTranslation(int index) {
        return null;
    }

    private void changeWord(int wordPosition) throws IOException {
        System.out.println("Please write new word");
        String word = scan.nextLine();
        System.out.println("Here are translations of word \"" + word + "\" that we found");
        List<String> translations = new SearchWord().getTranslations(word);
        MemoriseUtils.printTranslations(translations);
        List<Integer> userChoose = MemoriseUtils.askUserChoose();
        String newTranslation = concatinateWord(translations, userChoose);
        System.out.println("Your word is \"" + word + "\", translations is \"" + 
                newTranslation + "\"");
        saveWordInMap(word + " : " + newTranslation, wordPosition);
        doChanges();
    }

    private String concatinateWord(List<String> translations, List<Integer> userChoose) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < userChoose.size(); i++) {
            if (i != userChoose.size() - 1) {
                builder.append(translations.get(userChoose.get(i)));
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    private String parsChangedMapTranslations(int index) { 
           return ""; 
    }

    private void saveWordInMap(String word, int index) {
        changedLibrary.put(index, word);
    }

}

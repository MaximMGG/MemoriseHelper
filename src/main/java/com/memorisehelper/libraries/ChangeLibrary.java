package com.memorisehelper.libraries;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.memorisehelper.clientTouch.LibraryWorker;
import com.memorisehelper.clientTouch.StartApp;
import com.memorisehelper.clientTouch.libraryWorker.LibraryStatus;
import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.user.User;
import com.memorisehelper.utils.MemoriseUtils;

public class ChangeLibrary {
    
    private Scanner scan = new Scanner(System.in);
    private Map<String, String> currentLibrary;
    private Map<Integer, String> changedLibrary;
    private String libraryName;
    private User user = User.getUser();
    private LibraryWorker worker = LibraryWorker.getInstance();
    private SearchWord searchWord = SearchWord.getInstance();
    private DiskWorker diskWorker = DiskWorker.getInstance();

    public ChangeLibrary(Map<String, String> currentLibrary, String libraryName) throws IOException {
        this.currentLibrary = currentLibrary;
        this.libraryName = libraryName;
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

    private void unMuteCurrentLibrary() {
       currentLibrary.clear();
       for (Map.Entry<Integer, String> entry : changedLibrary.entrySet()) {
           String[] temp = entry.getValue().split(" : ");
           currentLibrary.put(temp[0], temp[1]);
       }
    }

    private void printChangedLibrary() {
        for(Map.Entry<Integer, String> entry : changedLibrary.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public void doChanges() throws IOException {
        printChangedLibrary();
        System.out.println("Please wright down number of word that you want to change" + 
                " or write \"exit\" to go back to menu");
        int index = MemoriseUtils.writeInt();
        System.out.println("What do you want to change? \n1. Etire word and translation" + 
                " \n2. Translation \n3. Delete word and translation or \n4. Go back to menu");
        switch (MemoriseUtils.writeInt()) {
            case 0 -> {} // TODO add case 0 logic
            case 1 -> changeWord(index);
            case 2 -> changeTranslation(index);
            case 3 -> deleteWord(index);
            case 4 -> {
                System.out.println("Do you want to save changed library?");
                if (MemoriseUtils.yesNo()) {
                    unMuteCurrentLibrary();
                    diskWorker.saveLibraryOnDisk(LibraryStatus.CREATE);
                    StartApp.getInstance().mainMenuUserChose();
                } else {
                    StartApp.getInstance().mainMenuUserChose();
                }
            }
            default -> {
                System.out.println("Sorry, but we do not have this option");
            }
        }
        doChanges();
    }

    private void deleteWord(int index) {
        changedLibrary.remove(index);
    }

    private void changeTranslation(int index) throws IOException {
        String word = changedLibrary.get(index).split(" : ")[0];
        System.out.println("Here is translations of word \"" + 
                word + "\"");
        List<String> translations = searchWord.getTranslations(word);
        List<Integer> userChoose = MemoriseUtils.askUserChoose();
        String newTranslation = concatinateWord(translations, userChoose);
        System.out.println("Your word is \"" + word + "\", translations is \"" + 
                newTranslation + "\"");
        saveWordInMap(word + " : " + newTranslation, index);

    }

    private void changeWord(int wordPosition) throws IOException {
        System.out.println("Please write new word");
        String word = scan.nextLine();
        System.out.println("Here are translations of word \"" + word + "\" that we found");
        List<String> translations = searchWord.getTranslations(word);
        MemoriseUtils.printTranslations(translations);
        List<Integer> userChoose = MemoriseUtils.askUserChoose();
        String newTranslation = concatinateWord(translations, userChoose);
        System.out.println("Your word is \"" + word + "\", translations is \"" + 
                newTranslation + "\"");
        saveWordInMap(word + " : " + newTranslation, wordPosition);
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

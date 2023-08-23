package com.memorisehelper.clientTouch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import com.memorisehelper.clientTouch.libraryWorker.CreateLibraryWorker;
import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.libraries.ChangeLibrary;
import com.memorisehelper.libraries.SearchWord;
import com.memorisehelper.user.User;
import com.memorisehelper.utils.MemoriseUtils;


public class LibraryWorker {

    private User user;
    private Scanner scan;
    private static final LibraryWorker INSTANCE = new LibraryWorker();
    private CreateLibraryWorker CREATELIBRARYWORKER = CreateLibraryWorker.getInstance();
    private SearchWord SEARCHWORD = SearchWord.getInstance();

    private LibraryWorker() {
        scan = new Scanner(System.in);
    }

    public static LibraryWorker GetInstance() {
        return INSTANCE;
    }

    public void createLibrary() throws IOException {
        System.out.println("Please write dawn name of your library");
        String libraryName = scan.nextLine();
        System.out.println("Awesome!");
        CREATELIBRARYWORKER.setLibraryName(libraryName);
        CREATELIBRARYWORKER.setLibrary(new HashMap<String, String>());
        System.out.println("So, let's start to wrighting words");

        boolean ready = true;
        while (ready) {
            System.out.println("Please, wright word");
            String word = scan.nextLine();
            String googledWord = MemoriseUtils.askInGoogleCorrectWord(word);
            List<String> translations = new ArrayList<>();
            if (word.equals(googledWord)) {
                translations = SEARCHWORD.getTranslations(word);
            } else {
                System.out.println("Do you meen " + googledWord + "?");
                if (yesNo()) {
                    translations = SEARCHWORD.getTranslations(googledWord);
                } else {
                    translations = SEARCHWORD.getTranslations(word);
                }
            }
            SEARCHWORD.printTranslations(translations);
            System.out.println("You can choose one or more translations");
            System.out.println("For example 1 4 5");
        }
    }

    private void putWordAtLibrary() throws IOException {
        System.out.println("Please wright down a word");
        String word = scan.nextLine();
        String wordTranslation = "";
        if (!MemoriseUtils.checkWord(word)) {
            System.out.println("You wrote some incorrect symbols or number, please, try agane");
            putWordAtLibrary();
        }
        String correctWord = MemoriseUtils.askInGoogleCorrectWord(word);
        if (!correctWord.equals(word) && correctWord.length() > 1) {
            System.out.println("Mabe your mean " + correctWord + "?");
            if (yesNo()) {
                System.out.println("Ok, here are translations of the word: " + correctWord + " that we found");
                CREATELIBRARYWORKER.putWordInLibrary(correctWord, wordTranslation);
                System.out.println("word " + correctWord + " and translation " + wordTranslation + " saved in library");
            } else {
                System.out.println("Ok, here are translations of the word: " + word + " that we found");
                CREATELIBRARYWORKER.putWordInLibrary(word, wordTranslation);
                System.out.println("word " + word + " and translation " + wordTranslation + " saved in library");
            }
        } else {
                System.out.println("Here are translations of the word: " + word + " that we found");
                CREATELIBRARYWORKER.putWordInLibrary(word, wordTranslation);
                System.out.println("word " + word + " and translation " + wordTranslation + " saved in library");
        }
        saveLibraryCrossroad();
    }

    public void saveLibraryCrossroad() throws IOException {
    }

    private int saveLibraryMenu() {
        System.out.println("1. Save library");
        System.out.println("2. Show content of library");
        System.out.println("3. Change some words");
        System.out.println("4. Delete library");
        return MemoriseUtils.writeInt();
    }



    private boolean yesNo() {
        System.out.println("1. Yes\n2. No");
        int answer = MemoriseUtils.writeInt();
        if (answer < 1 || answer > 2) {
            System.out.println("Please, wright 1 or 2");
            yesNo();
        }
        return answer == 1;
    }

    public void showAllLibraries() {
        
    }

    public Object startLearning() {
        return null;
    }

    public void changeLibrary() throws IOException {
    }

    public static LibraryWorker getWorker() {
        return null;
    }

}

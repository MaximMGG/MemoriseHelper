package com.memorisehelper.clientTouch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import com.memorisehelper.clientTouch.libraryWorker.CreateLibraryWorker;
import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.libraries.SearchWord;
import com.memorisehelper.user.Library;
import com.memorisehelper.user.User;
import com.memorisehelper.utils.MemoriseUtils;


public class LibraryWorker {

    private User user;
    private Scanner scan;
    private static final LibraryWorker INSTANCE = new LibraryWorker();
    private CreateLibraryWorker CREATELIBRARYWORKER = CreateLibraryWorker.getInstance();
    private SearchWord SEARCHWORD = SearchWord.getInstance();
    private StartApp START_APP = StartApp.getInstance();
    private Library library = Library.getInstance();
    private DiskWorker diskWorker = DiskWorker.getInstance();

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
        library.setLibraryName(libraryName);
        library.setCurrentLibrary(new HashMap<String, String>());
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
                    word = googledWord;
                } else {
                    translations = SEARCHWORD.getTranslations(word);
                }
            }
            MemoriseUtils.printTranslations(translations);
            System.out.println("You can choose one or more translations");
            System.out.println("For example 1 4 5");
            String userChoose = scan.nextLine();
            String concatTranslations = MemoriseUtils.parsingUserChoose(userChoose, translations);
            System.out.println("Your word is: " + word + "translations is: " + concatTranslations);
            System.out.println("Save word?");
            if (yesNo()) {
                CREATELIBRARYWORKER.putWordInLibrary(word, concatTranslations);
            } else {
                System.out.println("Word wasn't save'");
            }
            System.out.println("Do you want to continues wright words?");
            if (yesNo()) {
                continue;
            } else {
                ready = false;
            }
        }
        System.out.println("Content of library for now");

        MemoriseUtils.printLibrary(library);

        System.out.println("Do you want to save this library?");
        System.out.println("1. Save");
        System.out.println("2. Change some word");
        System.out.println("3. Exit without saving");
        switch (MemoriseUtils.writeInt()) {
            case 1 -> {
                CREATELIBRARYWORKER.saveLibrary();
                START_APP.mainMenuUserChose();
            }
            case 2 -> {
                changeLibrary();
            }
            case 3 -> START_APP.mainMenuUserChose();
        }
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

    public void changeLibrary() throws IOException {
        user.printUserLibraries();
        System.out.println("Wich library do you want to change");
        int a = MemoriseUtils.writeInt();
        List<String> libraries = diskWorker.getUserLibraries();
        diskWorker.getLibraryContent(libraryName);

    }

    public Object startLearning() {
        return null;
    }
}

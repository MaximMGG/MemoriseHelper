package com.memorisehelper.clientTouch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.libraries.ChangeLibrary;
import com.memorisehelper.libraries.SearchWord;
import com.memorisehelper.user.User;
import com.memorisehelper.utils.MemoriseUtils;

public class LibraryWorker {

    public static final LibraryWorker LIBRARY_WORKER = new LibraryWorker();
    private Map<String, String> currentLibrary;
    private String libraryName;
    private User user;
    private Scanner scan;

    private LibraryWorker() {
        currentLibrary = new HashMap<>();
        scan = new Scanner(System.in);
    }

    public static LibraryWorker getWorker() {
        return LIBRARY_WORKER;
    }

    public void createLibrary() throws IOException {
        System.out.println("Please write dawn name of your library");
        libraryName = scan.nextLine();
        System.out.println("Awesome!");
        System.out.println("So, let's start to wrighting words");
        putWordAtLibrary();
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
                wordTranslation = getTranslations(correctWord);
                currentLibrary.put(correctWord, wordTranslation);
                System.out.println("word " + correctWord + " and translation " + wordTranslation + " saved in library");
            } else {
                System.out.println("Ok, here are translations of the word: " + word + " that we found");
                wordTranslation = getTranslations(word);
                currentLibrary.put(word, wordTranslation);
                System.out.println("word " + word + " and translation " + wordTranslation + " saved in library");
            }
        } else {
                System.out.println("Here are translations of the word: " + word + " that we found");
                wordTranslation = getTranslations(word);
                currentLibrary.put(word, wordTranslation);
                System.out.println("word " + word + " and translation " + wordTranslation + " saved in library");
        }
        saveLibraryCrossroad();
    }

    public void saveLibraryCrossroad() throws IOException {
        System.out.println("Do you want to continius wright words?");
        if (yesNo()) {
            putWordAtLibrary();
        } else {
            switch (saveLibraryMenu()) {
                case 1 -> {
                    new DiskWorker().saveLibraryOnDisk(currentLibrary, libraryName);
                    System.out.println("Library saved");
                    StartApp.getInstance().mainMenuUserChose();
                }
                case 2 -> {
                    showContentOfNotSaveLibrary();
                    saveLibraryCrossroad();
                }
                case 3 -> {
                    new ChangeLibrary(currentLibrary, libraryName);
                }
                case 4 -> {
                    System.out.println("Library was delete");
                    StartApp.getInstance().mainMenuUserChose();
                }
                default -> {
                    System.out.println("We don't have this option, please try agane");
                    saveLibraryCrossroad();
                }
            }
        }
    }

    private int saveLibraryMenu() {
        System.out.println("1. Save library");
        System.out.println("2. Show content of library");
        System.out.println("3. Change some words");
        System.out.println("4. Delete library");
        return MemoriseUtils.writeInt();
    }

    private String getTranslations(String word) throws IOException {
        List<String> translations = new SearchWord().getTranslations(word);
        for (int i = 0; i < translations.size(); i++) {
            System.out.println((i + 1) + ". " + translations.get(i));
        }
        System.out.println("You can choose one or more translations");
        System.out.println("Wright for example: 1 2 5");
        String tr = prepareteTranslations(translations);
        return tr;
    }

    private String prepareteTranslations(List<String> translations) {
        String userChoose = scan.nextLine();
        List<Integer> numbers = MemoriseUtils.parsingUserChoose(userChoose);
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            build.append(translations.get(numbers.get(i) - 1));
            if (i != numbers.size() - 1) {
                build.append(", ");
            }
        }
        return build.toString();
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

    private void showContentOfNotSaveLibrary() {
        int i = 1;
        for(Map.Entry<String, String> entry : currentLibrary.entrySet()) {
            System.out.println(i + ". " + entry.getKey() + " - " + entry.getValue());
            i++;
        }
    }

    public void showAllLibraries() {
        
    }

    public Object startLearning() {
        return null;
    }

    public void changeLibrary() {

    }

}

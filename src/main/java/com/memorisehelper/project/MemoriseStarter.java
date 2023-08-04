package com.memorisehelper.project;

import java.io.IOException;
import java.lang.FdLibm.Pow;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.memorisehelper.hardwirework.DiskWorker;
import com.memorisehelper.savelibrary.SearchWord;
import com.memorisehelper.utils.MemoriseUtil;

/**
 * Hello world!
 *
 */
public class MemoriseStarter {

    private Scanner scan;
    private Map<String, String> library;
    private SearchWord sw;
    private boolean continious;
    private DiskWorker diskWorker;

    public MemoriseStarter() {
        library = new HashMap<>();
        sw = new SearchWord();
        diskWorker = new DiskWorker();
    }

    public void startSavinLibraryModule() throws IOException {
        continious = true;
        while (continious) {
            saveWord();
            System.out.println("");
        }
        if (!(library.isEmpty()))
        System.out.print("Do you want to save your library?");
        int askUser = askUser();
        if (askUser == 1) {
           diskWorker.saveLibraryOnDisk(library);
        }
        
    }

    private String writeWord() throws IOException {
        System.out.print("Please wright word that you would like to memorise ==> ");
        String word = "";
        while (true) {
            word = scan.nextLine();
            if (MemoriseUtil.checkWord(word))
                break;
        }
        String checkWord = MemoriseUtil.askInGoogleCorrectWord(word);
        if (!(word.equals(checkWord))) {
            System.out.println("Meybe you meen -> checkWord?" + "\n" + "1. Yes" + "\n" + "2. No");
        }
        int answer = scan.nextInt();
        if (answer == 1) {
            return checkWord;
        }
        return word;
    }

    private void saveWord() throws IOException {
        String word = writeWord();
        String translation = writeTranslation(word);
        System.out.println("Your word is : " + word + "and translation is : " + (translation));
        System.out.println("Continious?");
        if (askUser() == 1) {
            library.put(word, translation);
        } else {
            continious = false;
            exit();
        }
    }


    private String writeTranslation(String word) throws IOException {
        System.out.println("Which translation do you prefer? (Choose one ore more)");
        List<String> translations = sw.getTranslations(word);
        for (int i = 0; i < translations.size(); i++) {
            System.out.printf((i + 1) + ". " + translations.get(i));
            if (i == 0) {
                System.out.printf("(DEFOULT)");
            }
            System.out.println();
        }
        String userChoose = scan.nextLine();
        return getTranslations(userChoose, translations);
    }

    private String getTranslations(String userChoose, List<String> translations) {
        List<Integer> numbersOfTranslation = MemoriseUtil.parsingUserChoose(userChoose);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbersOfTranslation.size(); i++) {
            sb.append(translations.get(numbersOfTranslation.get(i) - 1));
            if (i != numbersOfTranslation.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private int askUser() {
        System.out.print("1. Yes\n2. No\n");
        return scan.nextInt();
    }

    private void exit() { 
    }
}

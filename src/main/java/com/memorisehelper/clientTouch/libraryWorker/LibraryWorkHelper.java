package com.memorisehelper.clientTouch.libraryWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.memorisehelper.libraries.SearchWord;
import com.memorisehelper.utils.MemoriseUtils;

public class LibraryWorkHelper {

    private static SearchWord searchWord = SearchWord.getInstance();
    private static Scanner scan = new Scanner(System.in);
    private static String word;
    private static List<String> translations;

    public static void main(String[] args) throws IOException {
        takeWordAndGetTranslations();
    }

    public static String takeWordAndGetTranslations() throws IOException {

        System.out.println("Please, write down you word");
        word = scan.nextLine();

        translations = new ArrayList<>();

        if (MemoriseUtils.checkWord(word)) {
            String correctWord = MemoriseUtils.askInGoogleCorrectWord(word);
            if (word.equalsIgnoreCase(correctWord)) {
                translations = searchWord.getTranslations(word);
            } else if (!word.equalsIgnoreCase(correctWord) && !correctWord.isBlank()){
                System.out.println("Maybe you meen " + correctWord + "?");
                if (MemoriseUtils.yesNo()) {
                    translations = searchWord.getTranslations(correctWord);
                    word = correctWord;
                } else {
                    translations = searchWord.getTranslations(word);
                }
            }
        } else {
            System.out.println("You wrote some incorrent symbols");
            takeWordAndGetTranslations();
        }
        return word + " : " + getConcatinateTranslations();
    }
   
    private static String getConcatinateTranslations() {

        System.out.printf("Here is translations of word : %s\n", word);
        System.out.println("You can chose one or more (1, 2, 5)");
        System.out.println("Or wright you own tranlstion (1, 2, Привет, Хай)");
        String[] userChoose = scan.nextLine().split(", ");
        List<String> concat = new ArrayList<>();

        for(String t : userChoose) {
            try {
                concat.add(translations.get(Integer.parseInt(t) - 1));
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    concat.add(t);
                } else if (e instanceof IndexOutOfBoundsException) {
                    System.out.println("We don't have translations with this number, please, try agane");
                    getConcatinateTranslations();
                }
            }
        }
        StringBuilder trans = new StringBuilder();
        for (int i = 0; i < concat.size(); i++) {
            trans.append(concat.get(i));
            if (i < concat.size()) {
                trans.append(", ");
            }
        }
        return trans.toString();
    }
}

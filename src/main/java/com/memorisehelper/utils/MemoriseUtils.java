package com.memorisehelper.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.memorisehelper.user.Library;

public class MemoriseUtils {

    private static final String GOOGLE_ASKING = "https://www.google.com/search?q=";
    private static final Scanner scan = new Scanner(System.in);
    private static Library library = Library.getInstance();

    public static boolean checkWord(String word) {
        Pattern pattern = Pattern.compile("^[A-z]*$");
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static String askInGoogleCorrectWord(String word) throws IOException {
        String url = GOOGLE_ASKING + word;
        Document doc = Jsoup.connect(url).get();
        Elements el = doc.select("body > div[class=main] > div > div[class=GyAeWb] > div[class=s6JM6d] > div[id=taw]" +
                " > div[id=oFNiHe] > p > a > b > i");
        return el.text();
    }

    public static String parsingUserChoose(String userChoose, List<String> translations) {
        String[] bufer = userChoose.split(" ");
        List<Integer> temp = new ArrayList<>();
        try {
            for (int i = 0; i < bufer.length; i++) {
                temp.add(Integer.parseInt(bufer[i]));
            }
        } catch (Exception e) {
            System.out.println("Sorry, but you wrote a not number, try agane");
            String u = scan.nextLine();
            parsingUserChoose(u, translations);
        }

        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i < temp.size(); i++) {
                builder.append(translations.get(temp.get(i) - 1));
                if (i != temp.size() - 1) {
                    builder.append(", ");
                }
            }
        } catch (Exception e) {
            System.out.println("Sorry, but you wrote incorrect number, we don't have this translation");
            System.out.println("Please, try agane");
            String u = scan.nextLine();
            parsingUserChoose(u, translations);
        }
        return builder.toString();
    }

    public static int writeInt() {
        int number = 0;
        try {
            String temp = scan.nextLine();
            if (temp.equals("exit")) {
                return 0;
            } else 
                return Integer.parseInt(temp);
        } catch (Exception e) {
            System.out.println("This symbol is not number, please write number");
            writeInt();
        }
        return 0;
    }
    
    public static boolean yesNo() {
        System.out.println("1. Yes\n2. No");
        int answer = MemoriseUtils.writeInt();
        if (answer < 1 || answer > 2) {
            System.out.println("Please, wright 1 or 2");
            yesNo();
        }
        return answer == 1;
    }

    public static void printTranslations(List<String> translations) {
        for (int i = 0; i < translations.size(); i++) {
           System.out.println((i + 1) + translations.get(i));
        }
    }

    public static List<Integer> askUserChoose() {
        return null;
    }

    public static void printLibrary(Library library) {
        int count = 1;
        for(Map.Entry<String, String> entry : library.getCurrentLibrary().entrySet()) {
            System.out.println(count + ". ");
        }
    }

    public static String getUserChangeLibrary(List<String> userLibraries) {
        String userLibrary = "";
        int index = writeInt();
        try {
            userLibrary = userLibraries.get(index);
        } catch (Exception e) {
            System.out.println("You don't have library with this number, please write agane");
            getUserChangeLibrary(userLibraries);
        }
        return userLibrary;
    }

    public static String changedWord(int index) {
        try {
            int i = 1;
            for(Map.Entry<String, String> entry : library.getCurrentLibrary().entrySet()) {
                if (i == index) {
                    return entry.getKey() + " : " + entry.getValue();
                } else
                    i++;
            }
        } catch (Exception e) {
            System.out.println("You don't have word with this number, please write number agane");
            changedWord(writeInt());
        }
        return null;
    }
}

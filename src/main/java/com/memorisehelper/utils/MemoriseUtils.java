package com.memorisehelper.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MemoriseUtils {

    private static final String GOOGLE_ASKING = "https://www.google.com/search?q=";
    private static final Scanner scan = new Scanner(System.in);

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

    public static List<Integer> parsingUserChoose(String userChoose) {
        List<Integer> result = new ArrayList<>();
        String[] buffer = userChoose.split("");
        for (int i = 0; i < buffer.length; i++) {
            try {
                result.add(Integer.parseInt(buffer[i]));
            } catch (Exception e) {
            }
        }
        return result;
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
}

package com.memorisehelper.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MemoriseUtils {

    private static final String GOOGLE_ASKING = "https://www.google.com/search?q=";

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

    public static int checkInt() {
        return 0;
    }
}

package com.memorisehelper.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemoriseUtil {
    
    public static boolean checkWord(String word) {
        Pattern pattern = Pattern.compile("^[A-z]*$");
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}

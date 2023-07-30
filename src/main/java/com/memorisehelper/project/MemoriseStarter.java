package com.memorisehelper.project;

import java.util.Scanner;

import com.memorisehelper.utils.MemoriseUtil;

/**
 * Hello world!
 *
 */
public class MemoriseStarter {

    private Scanner scan;

    public String writeWord() {
        System.out.println("Please wright word thet you would like to memorise ==> ");
        String word = "";
        while (true) {
            word = scan.nextLine();
            if (MemoriseUtil.checkWord(word))
                    break;
        }
        return word;
    }


}

package com.memorisehelper.project;

import static org.junit.Assert.*;

import org.junit.Test;

import com.memorisehelper.utils.MemoriseUtil;

public class MemoriseUtilTest {
    
    @Test
    public void checkWordTest() {
        String correctWord = "Hello";
        String nonCorrectWord = "He1llo";
        String symbol = "$";
        String number = "22151";
        String coorectWord2 = "afihsdfkadhflajh";
        
       assertTrue(MemoriseUtil.checkWord(correctWord));
       assertTrue(MemoriseUtil.checkWord(coorectWord2));
       assertFalse(MemoriseUtil.checkWord(nonCorrectWord));
       assertFalse(MemoriseUtil.checkWord(symbol));
       assertFalse(MemoriseUtil.checkWord(number));

    }
}

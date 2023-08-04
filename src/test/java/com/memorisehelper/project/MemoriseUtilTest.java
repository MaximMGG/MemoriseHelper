package com.memorisehelper.project;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

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

    @Test
    public void askInGoogleCorrectWordTest() throws IOException {
        String correct = "memories";
        String uncorrect = "memaries";
        
        assertNotEquals(uncorrect, MemoriseUtil.askInGoogleCorrectWord(uncorrect));
        assertEquals(correct, MemoriseUtil.askInGoogleCorrectWord(uncorrect));
    }
    @Test
    public void testParsingUserChoose() {
        String userChoose1 = "1,2,3,4";
        String userChoose2 = "1 2 3 4";
        List<Integer> result1 = MemoriseUtil.parsingUserChoose(userChoose1);
        List<Integer> result2 = MemoriseUtil.parsingUserChoose(userChoose2);
        String answer = "1234";
        String result1Answer = "";
        for(Integer i : result1) {
            result1Answer += i;
        }
        String result2Answer = "";
        for(Integer i : result2) {
            result2Answer += i;
        }
        assertEquals(answer, result1Answer);
        assertEquals(answer, result2Answer);
    }
}

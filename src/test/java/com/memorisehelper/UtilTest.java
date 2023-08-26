package com.memorisehelper;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.memorisehelper.utils.MemoriseUtils;

public class UtilTest {

	@Test
	public void testAskInGoogleCorrectWord() throws IOException {
		String correctWord = "implement";
		String uncorrectWord = "implament";
		String askingWord = MemoriseUtils.askInGoogleCorrectWord(uncorrectWord);
		assertEquals(correctWord, askingWord);
		assertNotEquals(askingWord, uncorrectWord);
	}

    @Test
    public void testWrightInt() {

    }



	 

}

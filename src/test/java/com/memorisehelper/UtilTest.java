package com.memorisehelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public void testParsingUserChoose() {
        List<Integer> userChose = new ArrayList<>();
        userChose.add(1);
        userChose.add(2);
        userChose.add(3);
        userChose.add(4);
        userChose.add(5);
        String chose = "1 2 3 4 5";
        String uncorrectChose = "1 5";
        List<Integer> utilsList = MemoriseUtils.parsingUserChoose(chose);
        List<Integer> uncorrectUtilsList = MemoriseUtils.parsingUserChoose(uncorrectChose);
        assertEquals(userChose, utilsList);
        assertNotEquals(userChose, uncorrectUtilsList);
	}



	 

}

package com.memorisehelper.libraryWorkerTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.memorisehelper.clientTouch.libraryWorker.ChangeLibraryWorker;
import com.memorisehelper.user.Library;

public class ChangeLibraryWorkerTest {
    
    private Library library = Library.getInstance();
    private ChangeLibraryWorker changeLibraryWorker = ChangeLibraryWorker.getInstance();

    @Test
    public void changeWordTest() {
        String word = "cat";
        String translations = "кот, котик";
        String changeTranslations = "кот, котик, кошак";

        library.setCurrentLibrary(new HashMap<String, String>());
        library.getCurrentLibrary().put(word, translations);

        assertTrue(library.getCurrentLibrary().containsKey(word));

        changeLibraryWorker.changeWordsTranlation(word, changeTranslations);

        assertTrue(library.getCurrentLibrary().containsValue(changeTranslations));
    }
}

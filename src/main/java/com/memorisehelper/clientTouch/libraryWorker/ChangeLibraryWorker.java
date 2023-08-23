package com.memorisehelper.clientTouch.libraryWorker;

import java.io.IOException;
import java.util.Map;

import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.user.Library;

public class ChangeLibraryWorker {

    private static final ChangeLibraryWorker INSTANCE = new ChangeLibraryWorker();
    private Library library = Library.getInstance();
    private static final DiskWorker DISKWORKER = DiskWorker.getInstance();

    private ChangeLibraryWorker() {}

    public static ChangeLibraryWorker getInstance() {
        return INSTANCE;
    }

    /**
     * @param position
     */
    public void changeWord(String word, String translations) {
        if (library.getCurrentLibrary().containsKey(word)) {
            library.put(word, translations);
        } else {
            library.remove(word);
            library.put(word, translations);
        }
    }

    public void saveLibrary() throws IOException {
        DISKWORKER.saveLibraryOnDisk();
    }
}

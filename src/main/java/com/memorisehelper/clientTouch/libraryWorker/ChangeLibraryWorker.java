package com.memorisehelper.clientTouch.libraryWorker;

import java.io.IOException;

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
     * @param word
     * @param translations
     */
    public void changeWordsTranlation(String word, String translations) {
        library.getCurrentLibrary().put(word, translations);
    }

    public void saveLibrary() throws IOException {
        DISKWORKER.saveLibraryOnDisk(LibraryStatus.CREATE);
    }
}

package com.memorisehelper.clientTouch.libraryWorker;

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

    public void changeWord(int position) {

    }
}

package com.memorisehelper.clientTouch.libraryWorker;

import java.util.Map;

import com.memorisehelper.filesystem.DiskWorker;

public class ChangeLibraryWorker {

    private static final ChangeLibraryWorker INSTANCE = new ChangeLibraryWorker();
    private Map<String, String> library;
    private String libraryName;
    private static final DiskWorker DISKWORKER = DiskWorker.getInstance();

    private ChangeLibraryWorker() {}

    public static ChangeLibraryWorker getInstance() {
        return INSTANCE;
    }

    public void setLibrary(Map<String, String> library, String libraryName) {
        this.library = library;
        this.libraryName = libraryName;
    }
    public void changeWord(int position) {

    }
}

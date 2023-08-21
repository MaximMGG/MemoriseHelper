package com.memorisehelper.clientTouch.libraryWorker;

import java.util.Map;

import com.memorisehelper.filesystem.DiskWorker;

public class ChangeLibraryWorker {

    private static final ChangeLibraryWorker INSTANCE = new ChangeLibraryWorker();
    private Map<String, String> library;
    private static final DiskWorker DISKWORKER = DiskWorker.getInstance();
    private static final CurrentLibrary CURRENT_LIBRARY = CurrentLibrary.getInstance();

    private ChangeLibraryWorker() {}

    public static ChangeLibraryWorker getInstance() {
        return INSTANCE;
    }

    public void changeLibrary() {
        
    }

}

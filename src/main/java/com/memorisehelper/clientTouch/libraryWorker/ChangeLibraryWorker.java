package com.memorisehelper.clientTouch.libraryWorker;

import java.util.Map;

public class ChangeLibraryWorker {

    private static final ChangeLibraryWorker INSTANCE = new ChangeLibraryWorker();
    private Map<String, String> library;

    private ChangeLibraryWorker() {}

    public static ChangeLibraryWorker getInstance() {
        return INSTANCE;

    }

}

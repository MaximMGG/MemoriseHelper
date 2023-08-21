package com.memorisehelper.clientTouch.libraryWorker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentLibrary {
    private Map<String, String> currentLibrary;
    private static String libraryName;

    public CurrentLibrary() {
    }

    public static Map<String, String> parseLibrary(List<String> listLibrary, String libraryName) {
        this.libraryName = libraryName;
        Map<String, String> library = new HashMap<>();
        for(String wordAndTraslations : listLibrary) {
            String[] temp = wordAndTraslations.split(" : ");
            library.put(temp[0], temp[1]);
        }
        return library;
    }
}

package com.memorisehelper.clientTouch.libraryWorker;

import java.io.IOException;
import java.util.Map;

import com.memorisehelper.filesystem.DiskWorker;

public class CreateLibraryWorker {

    private Map<String, String> library;
    private String libraryName;
    private DiskWorker DISKWORKER = DiskWorker.getInstance();

    private static final CreateLibraryWorker INSTANCE = new CreateLibraryWorker();

    private CreateLibraryWorker(){}

    public static CreateLibraryWorker getInstance() {
        return INSTANCE;
    }

    public void putWordInLibrary(String word, String translations) {
        library.put(word, translations);
    }

    public void printLibrary() {
        int count = 1;
        for(Map.Entry<String, String> entry : library.entrySet()) {
           System.out.println(count + ". " + entry.getKey() + " : " + entry.getValue()); 
           count++;
        }
    }

    public Map<String, String> getLibrary() {
        return library;
    }

    public void setLibrary(Map<String, String> library) {
        this.library = library;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void saveLibrary() throws IOException {
        if (library != null && libraryName != null) {
            DISKWORKER.saveLibraryOnDisk(library, libraryName);
        } else {
            System.out.println("Can't do it, library is empty or libraryName is blanc");
        }
    }
}

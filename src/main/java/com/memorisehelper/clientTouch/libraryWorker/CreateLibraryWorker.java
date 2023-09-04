package com.memorisehelper.clientTouch.libraryWorker;

import java.io.IOException;

import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.user.Library;

public class CreateLibraryWorker {

    private DiskWorker DISKWORKER = DiskWorker.getInstance();
    private Library library = Library.getInstance();

    private static final CreateLibraryWorker INSTANCE = new CreateLibraryWorker();

    private CreateLibraryWorker(){}

    public static CreateLibraryWorker getInstance() {
        return INSTANCE;
    }

    public void putWordInLibrary(String word, String translations) {
        library.getCurrentLibrary().put(word, translations);
    }

    public void saveLibrary() throws IOException {
        if (library != null) {
            // DISKWORKER.saveLibraryOnDisk(LibraryStatus.CREATE);
        } else {
            System.out.println("Can't do it, library is empty or libraryName is blanc");
        }
    }
}

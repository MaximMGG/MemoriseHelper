package com.memorisehelper.hardwirework;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DiskWorker {

    public DiskWorker() {}

/**
 * Actually i dont now work this or not:(
 * 
 */
    public boolean saveLibraryOnDisk(Map<String, String> library) throws IOException {
            File temp = new File(System.getProperty("/"));
            File filewitha = new File(temp.getAbsolutePath(), "/test.txt");

        return true;
    }
}

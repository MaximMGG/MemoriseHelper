package com.memorisehelper.hardwirework;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DiskWorker {

    public DiskWorker() {}

/**
 * Actually i dont now work this or not:(
 *
 */
    public static void main(String[] args)throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "world");
        DiskWorker dw = new DiskWorker();
        dw.saveLibraryOnDisk(map);
		String a = "aa ";
    }

    public boolean saveLibraryOnDisk(Map<String, String> library) throws IOException {
            File temp = new File("resources", "test.txt");
            System.out.println(temp.createNewFile());
            return true;
        // return true;
    }
}

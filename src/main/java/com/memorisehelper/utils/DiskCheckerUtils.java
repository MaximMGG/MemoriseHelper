package com.memorisehelper.utils;

import java.nio.file.Files;
import java.nio.file.Path;

public class DiskCheckerUtils {
    

    public static boolean checkExistLibrary(String userName, String libraryName) {
        return Files.exists(Path.of("resources/Libraries/" + userName + "Library/" + libraryName + ".txt"));
    }
}

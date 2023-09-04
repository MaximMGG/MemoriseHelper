package com.memorisehelper.filesystem;

import java.nio.file.Files;
import java.nio.file.Path;

import com.memorisehelper.user.Library;
import com.memorisehelper.user.User;

public class DiskWorker {

    private static final User user = User.getUser();
    private static final Library library = Library.getInstance();
    public static DiskWorker diskWorker;

    private DiskWorker() {}

    public static DiskWorker getInstance() {
        if (diskWorker == null) {
            if (userInfoExist()) {
                initialize();
                diskWorker = new DiskWorker();
            }
        }
        return diskWorker;
    }
    private static void initialize() {

    }

    private static boolean userInfoExist() {
        return Files.exists(Path.of(Pathes.PATH_TO_USERINFO));
    }

}

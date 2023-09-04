package com.memorisehelper.filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.memorisehelper.user.Library;
import com.memorisehelper.user.User;

public class DiskWorker {

    private static final User user = User.getUser();
    private static final Library library = Library.getInstance();
    private static DiskWorker diskWorker;
    private static DiskWorkerHelper diskWorkerHelper = DiskWorkerHelper.getInstance();

    private DiskWorker() {}

    public static DiskWorker getInstance() {
        if (diskWorker == null) {
            if (userInfoExist()) {
                try {
                    initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                diskWorker = new DiskWorker();
            }
        }
        return diskWorker;
    }
    private static void initialize() throws IOException {
        diskWorkerHelper.createUserInfo();
    }

    private static boolean userInfoExist() {
        return Files.exists(Path.of(Pathes.PATH_TO_USERINFO));
    }

}

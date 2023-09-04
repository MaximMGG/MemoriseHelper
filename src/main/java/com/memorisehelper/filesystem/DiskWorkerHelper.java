package com.memorisehelper.filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.memorisehelper.user.User;

public class DiskWorkerHelper {

    private static final User user = User.getUser();
    private static final DiskWorkerHelper diskWorkerHelper = new DiskWorkerHelper();

    private DiskWorkerHelper() {}

    public static DiskWorkerHelper getInstance() {
        return diskWorkerHelper;
    }

    public void createUserInfo() throws IOException {
        Files.createDirectories(Path.of(Pathes.PATH_TO_LIBRARIES));
        Files.createFile(Path.of(Pathes.PATH_TO_USERINFO));
        writeUserInfoCanfig();
    }

    private void writeUserInfoCanfig() throws IOException {
        String userInfoConfig = "user:\nuserLibraries:";
        Files.writeString(Path.of(Pathes.PATH_TO_USERINFO), userInfoConfig);
    }

    public void addUserInInfo() {

    }


}

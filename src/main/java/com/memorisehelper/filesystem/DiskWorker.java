package com.memorisehelper.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class DiskWorker {

    private static String userName;
    private List<String> userLibraries;
    private final String PATH_TO_USER_COFIG = "resources/userInfo.txt";

    public static void main(String[] args) throws IOException {
        DiskWorker dw = new DiskWorker();
        // Map<String, String> map = new HashMap<>();
        // map.put("Hello", "Word");
        // userName = "Petro";
        // dw.saveLibraryOnDisk(map, userName);
        dw.createResourcesDir();
        dw.createUserConfig();
        dw.wrightUserInfo();
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getUserLibraries() {
        return userLibraries;
    }

    public boolean saveLibraryOnDisk(Map<String, String> library, String libraryName) throws IOException {
        if (!userConfig()) {
            createUserConfig();
            wrightUserInfo();
        }
        return true;
    }

    private void wrightUserInfo() throws IOException {
        String userInfoWright = "username: " + userName;
        String userLibraries = "userLibraries: ";
        Files.writeString(Path.of(PATH_TO_USER_COFIG), (userInfoWright + "\n" + userLibraries),
        StandardOpenOption.CREATE);

    }

    private void createUserConfig() throws IOException {
        File file = new File("resources" , "userInfo.txt");
        file.createNewFile();
    }

    private boolean userConfig() {
        return Files.exists(Path.of(PATH_TO_USER_COFIG));
    }

    private void createResourcesDir() throws IOException {
        Path resources = Path.of("resources");
        Files.createDirectory(resources);
    }

}

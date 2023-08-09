package com.memorisehelper.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class DiskWorker {

    private String userName;
    private List<String> userLibraries;
    private final String PATH_TO_USER_COFIG = "resources/userInfo.txt";
    private final String PATH_TO_LIBRARY_DIR = "resources/libraries";

    public static void main(String[] args) throws IOException {
        DiskWorker dw = new DiskWorker();
        System.out.println(dw.getLibraryContent("RegularWords"));
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getUserLibraries() throws IOException {
        List<String> libraries = Files.readAllLines(Path.of(PATH_TO_USER_COFIG)).stream()
       .skip(2)
       .toList();
        return libraries;
    }

    public void initializeUser(String userName) throws IOException {
        this.userName = userName;
        if (!userConfig()) {
            createUserConfig();
            createResourcesDir();
        }

    }

    public boolean saveLibraryOnDisk(Map<String, String> library, String libraryName) throws IOException {
        Files.writeString(Path.of(PATH_TO_USER_COFIG), "\n" + libraryName, StandardOpenOption.APPEND);
        File libraryFile = new File("resources/libraries/" + libraryName + ".txt");
        libraryFile.createNewFile();
        for (Map.Entry<String, String> entry : library.entrySet()) {
            Files.writeString(Path.of(PATH_TO_LIBRARY_DIR + "/" + libraryName + ".txt"),
            (entry.getKey() + " : " + entry.getValue() + "\n"), StandardOpenOption.APPEND);
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
        Path libr = Path.of("resources/libraries");
        Files.createDirectory(libr);
    }

    private List<String> getLibraryContent(String libraryName) throws IOException {

        return Files.readAllLines(Path.of(PATH_TO_LIBRARY_DIR + "/" + libraryName + ".txt"));
    }
}

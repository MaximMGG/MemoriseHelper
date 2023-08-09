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
    private final String PATH_TO_USER_COFIG = "resources/userInfo.txt";
    private final String PATH_TO_LIBRARY_DIR = "resources/libraries";

    public DiskWorker(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args) throws IOException {
        DiskWorker dw = new DiskWorker("Petr");
        // dw.createUserLibraryDir();
        dw.correntUserConfig();
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
        } else {
            if (correntUserConfig()) {

            }
        }
    }

    private boolean correntUserConfig() throws IOException {
        Path of = Path.of(PATH_TO_LIBRARY_DIR + "/" + userName + "Library");
        List<Path> list = Files.walk(of).toList();
        System.out.println(list);
        return false;
    }

    public boolean saveLibraryOnDisk(Map<String, String> library, String libraryName) throws IOException {
        Files.writeString(Path.of(PATH_TO_USER_COFIG), "\n" + libraryName, StandardOpenOption.APPEND);
        File libraryFile = new File("resources/libraries/" + userName + "Library/" + libraryName + ".txt");
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
        StandardOpenOption.APPEND);
    }

    private void createUserConfig() throws IOException {
        File file = new File("resources" , "userInfo.txt");
        file.createNewFile();
    }

    private boolean userConfig() {
        return Files.exists(Path.of(PATH_TO_USER_COFIG));
    }

    private void createResourcesDir() throws IOException {
        Files.createDirectory(Path.of("resources"));
        Files.createDirectory(Path.of("resources/libraries"));
    }

    private List<String> getLibraryContent(String libraryName) throws IOException {
        String path = PATH_TO_LIBRARY_DIR + "/" + userName + "Library/" + libraryName + ".txt";
        return Files.readAllLines(Path.of(path));
    }

    private void createUserLibraryDir() throws IOException {
        Files.createDirectory(Path.of(PATH_TO_LIBRARY_DIR + "/" + userName + "Library"));
    }
}

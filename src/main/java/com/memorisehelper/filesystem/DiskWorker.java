package com.memorisehelper.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiskWorker {

    private String userName;
    private final String PATH_TO_USER_COFIG = "resources/userInfo.txt";
    private final String PATH_TO_LIBRARY_DIR = "resources/libraries";

    public DiskWorker(String userName) throws IOException {
        this.userName = userName;
        if (!userConfig()) {
            createResourcesDir();
            createUserConfig();
            createUserLibraryDir();
            wrightUserInfo();
        } else {
            if (!userExist()) {
                createUserLibraryDir();
                wrightUserInfo();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        DiskWorker dw = new DiskWorker("Petr");
        // dw.createUserLibraryDir();
        // dw.correntUserConfig();
        // dw.wrightUserInfo();
        // dw.addLibraryInUserConfig("Cats");
        // dw.createUserConfig();
        // System.out.println(dw.userExist());
       System.out.println(dw.getUserLibraries());
    }

    public String getUserName() {
        return userName;
    }

    private boolean userExist() throws IOException {
        List<String> usersConfig = Files.readAllLines(Path.of(PATH_TO_USER_COFIG));
        List<String> users = new ArrayList<>();
        for (String user : usersConfig) {
            Pattern p = Pattern.compile("username: ([A-z]*)");
            Matcher m = p.matcher(user);
            if (m.find()) {
                users.add(m.group(1).toLowerCase());
            }
        }
        return users.contains(userName.toLowerCase());
    }

    public List<String> getUserLibraries() throws IOException {
        List<String> libraries = Files.readAllLines(Path.of(PATH_TO_USER_COFIG));
        String username = "username: " + userName;
        List<String> lib = new ArrayList<>();
        for (int i = 0; i < libraries.size(); i++) {
           if (username.equals(libraries.get(i))) {
                String[] split = libraries.get(i + 1).split(" ");
                lib = List.of(split);
           }
        }
        return lib.stream().skip(1).toList();
    }

    public boolean saveLibraryOnDisk(Map<String, String> library, String libraryName) throws IOException {
        addLibraryInUserConfig(libraryName);
        File libraryFile = null;
        try {
            libraryFile = new File("resources/libraries/" + userName + "Library/" + libraryName + ".txt");
            libraryFile.createNewFile();
        } catch (Exception e) {}
        for (Map.Entry<String, String> entry : library.entrySet()) {
            Files.writeString(Path.of(PATH_TO_LIBRARY_DIR + "/"+ userName + "Library/" + libraryName + ".txt"),
                    (entry.getKey() + " : " + entry.getValue() + "\n"), StandardOpenOption.APPEND);
        }
        return true;
    }

    private void addLibraryInUserConfig(String libraryName) throws IOException {
        List<String> userConfig = Files.readAllLines(Path.of(PATH_TO_USER_COFIG));
        for (int i = 0; i < userConfig.size(); i++) {
            String[] parsString = userConfig.get(i).split(" ");
            if (parsString[0].equals("username:")) {
                if (parsString[1].equals(userName)) {
                    String a = userConfig.get(i + 1);
                    userConfig.remove(i + 1);
                    userConfig.add(a + " " + libraryName);
                }
                Files.writeString(Path.of(PATH_TO_USER_COFIG), "", StandardOpenOption.TRUNCATE_EXISTING);
                for (String s : userConfig) {
                    Files.writeString(Path.of(PATH_TO_USER_COFIG), s + "\n", StandardOpenOption.APPEND);
                }

            }
        }
    }

    private void wrightUserInfo() throws IOException {
        String userInfoWright = "username: " + userName;
        String userLibraries = "userLibraries: ";
        Files.writeString(Path.of(PATH_TO_USER_COFIG), (userInfoWright + "\n" + userLibraries),
                StandardOpenOption.APPEND);
    }

    private void createUserConfig() throws IOException {
        File file = new File("resources", "userInfo.txt");
        file.createNewFile();
    }

    private boolean userConfig() {
        return Files.exists(Path.of(PATH_TO_USER_COFIG));
    }

    private void createResourcesDir() throws IOException {
        Files.createDirectory(Path.of("resources"));
        Files.createDirectory(Path.of("resources/libraries"));
    }

    public List<String> getLibraryContent(String libraryName) throws IOException {
        String path = PATH_TO_LIBRARY_DIR + "/" + userName + "Library/" + libraryName + ".txt";
        return Files.readAllLines(Path.of(path));
    }

    private void createUserLibraryDir() throws IOException {
        Files.createDirectory(Path.of(PATH_TO_LIBRARY_DIR + "/" + userName + "Library"));
    }
}

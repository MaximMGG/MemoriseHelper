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

import com.memorisehelper.user.Library;
import com.memorisehelper.user.User;

public class DiskWorker {

    private final String PATH_TO_USER_COFIG = "resources/userInfo.txt";
    private final String PATH_TO_LIBRARY_DIR = "resources/libraries";
    private User user = User.getUser();
    private static final DiskWorker INSTACE = new DiskWorker();
    private Library library = Library.getInstance();

    public DiskWorker() {}

    public void firstInitialize() {
        try {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DiskWorker getInstance() {
        return INSTACE;
    }

    private boolean userExist() throws IOException {
        List<String> usersConfig = Files.readAllLines(Path.of(PATH_TO_USER_COFIG));
        List<String> users = new ArrayList<>();
        for (String user : usersConfig) {
            Pattern p = Pattern.compile("userName: ([A-z]*)");
            Matcher m = p.matcher(user);
            if (m.find()) {
                users.add(m.group(1).toLowerCase());
            }
        }
        return users.contains(user.getUserName().toLowerCase());
    }

    public List<String> getUserLibraries() throws IOException {
        List<String> libraries = Files.readAllLines(Path.of(PATH_TO_USER_COFIG));
        String username = "userName: " + user.getUserName();
        List<String> lib = new ArrayList<>();
        for (int i = 0; i < libraries.size(); i++) {
           if (username.equals(libraries.get(i))) {
                String[] split = libraries.get(i + 1).split(" ");
                lib = List.of(split);
           }
        }
        return lib.stream().skip(1).toList();
    }

    public boolean saveLibraryOnDisk() throws IOException {
        addLibraryInUserConfig();
        File libraryFile = null;
        libraryFile = new File("resources/libraries/" + user.getUserName() + "Library/" + library.getLibraryName() + ".txt");
        libraryFile.createNewFile();
        for (Map.Entry<String, String> entry : library.getCurrentLibrary().entrySet()) {
            Files.writeString(Path.of(PATH_TO_LIBRARY_DIR + "/"+ user.getUserName() + "Library/" + library.getLibraryName() + ".txt"),
                    (entry.getKey() + " : " + entry.getValue() + "\n"), StandardOpenOption.APPEND);
        }
        return true;
    }

    private void addLibraryInUserConfig() throws IOException {
        List<String> userConfig = Files.readAllLines(Path.of(PATH_TO_USER_COFIG));
        for (int i = 0; i < userConfig.size(); i++) {
            String[] parsString = userConfig.get(i).split(" ");
            if (parsString[0].equals("userName:")) {
                if (parsString[1].equals(user.getUserName())) {
                    String a = userConfig.get(i + 1);
                    userConfig.remove(i + 1);
                    userConfig.add(a + " " + library.getLibraryName());
                }
                Files.writeString(Path.of(PATH_TO_USER_COFIG), "", StandardOpenOption.TRUNCATE_EXISTING);
                for (String s : userConfig) {
                    Files.writeString(Path.of(PATH_TO_USER_COFIG), s + "\n", StandardOpenOption.APPEND);
                }

            }
        }
    }

    private void wrightUserInfo() throws IOException {
        String userInfoWright = "userName: " + user.getUserName();
        String userLibraries = "userLibraries:";
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
        String path = PATH_TO_LIBRARY_DIR + "/" + user.getUserName() + "Library/" + libraryName + ".txt";
        return Files.readAllLines(Path.of(path));
    }

    private void createUserLibraryDir() throws IOException {
        Files.createDirectory(Path.of(PATH_TO_LIBRARY_DIR + "/" + user.getUserName() + "Library"));
    }
}

package com.memorisehelper.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.memorisehelper.clientTouch.libraryWorker.LibraryStatus;
import com.memorisehelper.user.Library;
import com.memorisehelper.user.User;

public class DiskWorker {

    private static User user = User.getUser();
    private static Library library = Library.getInstance();
    private String PATH_TO_USER_COFIG = "resources/userInfo.txt";
    private String PATH_TO_LIBRARY_DIR = "resources/libraries";
    private String PATH_TO_CURRENT_LIBRARY = PATH_TO_LIBRARY_DIR + "/" + user.getUserName() + "Library/" + 
                library.getLibraryName() + ".txt";
    private static final DiskWorker INSTACE = new DiskWorker();

    private DiskWorker() {}
    public static void main(String[] args) throws IOException {
        DiskWorker dw = DiskWorker.getInstance();
        User u = User.getUser();
        Library l = Library.getInstance();
        u.setUserName("Viniamin");
        Map<String, String> lib = new HashMap<>();
        lib.put("cat", "кот, кошка");
        lib.put("dog", "собака, пес");
        lib.put("man", "мужчина");
        lib.put("woman", "жетщина");
        l.setCurrentLibrary(lib);
        l.setLibraryName("basics");
        dw.saveLibraryOnDisk(LibraryStatus.CREATE);
    }

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

    public boolean saveLibraryOnDisk(LibraryStatus status) throws IOException {
        System.out.println(test);
        Path path = Path.of(PATH_TO_CURRENT_LIBRARY);
        if (status == LibraryStatus.CREATE) {
            addLibraryInUserConfig();
            File libraryFile = null;
            libraryFile = new File(PATH_TO_CURRENT_LIBRARY);
            libraryFile.createNewFile();
            for (Map.Entry<String, String> entry : library.getCurrentLibrary().entrySet()) {
                Files.writeString(path,
                        (entry.getKey() + " : " + entry.getValue() + "\n"), StandardOpenOption.APPEND);
            }
        } else if (status == LibraryStatus.REWRITE) {
            for (Map.Entry<String, String> entry : library.getCurrentLibrary().entrySet()) {
                Files.writeString(Path.of(PATH_TO_CURRENT_LIBRARY),
                        (entry.getKey() + " : " + entry.getValue() + "\n"), StandardOpenOption.TRUNCATE_EXISTING);
            }
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

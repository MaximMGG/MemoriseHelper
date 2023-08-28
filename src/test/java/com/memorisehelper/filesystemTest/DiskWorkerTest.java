package com.memorisehelper.filesystemTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.user.Library;
import com.memorisehelper.user.User;
import com.memorisehelper.utils.MemoriseUtils;

public class DiskWorkerTest {
    
    private DiskWorker diskWorker = DiskWorker.getInstance();
    private User user = User.getUser();
    private Library currentLibrary = Library.getInstance();

    @Test
    public void firstInitializeTest() throws IOException {
        user.setUserName("Viniamin");
        diskWorker.firstInitialize();

        assertTrue(Files.exists(Path.of("resources/userInfo.txt")));
        assertTrue(Files.isDirectory(Path.of("resources/libraries/viniaminLibrary")));
        assertTrue(userInfoEqualse(""));
    }

    private boolean userInfoEqualse(String libr) throws IOException {
        List<String> userInfo = new ArrayList<>();
        userInfo.add("userName: Viniamin");
        userInfo.add("userLibraries: " + libr);
        boolean flag = true;

        List<String> userInfoRead = Files.readAllLines(Path.of("resources/userInfo.txt"));
        if (userInfo.size() == userInfoRead.size()) {
            for (int i = 0; i < userInfo.size(); i++) {
                if (userInfo.get(i).equals(userInfoRead.get(i))) {
                    flag = true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }

        return flag;
    }

    @Test
    public void saveLibraryOnDiskTest() throws IOException {

        user.setUserName("Viniamin");
        diskWorker.firstInitialize();
        Map<String, String> library = new HashMap<>();
        library.put("cat", "кот, кошка");
        library.put("dog", "собака, пес");
        library.put("man", "мужчина");
        library.put("woman", "жетщина");
        currentLibrary.setCurrentLibrary(library);
        currentLibrary.setLibraryName("basics");
        diskWorker.saveLibraryOnDisk();


        assertTrue(Files.exists(Path.of("resources/libraries/ViniaminLibrary/basics.txt")));
        assertTrue(userInfoEqualse("basics"));
        assertTrue(mapsEquals(library,
                MemoriseUtils.transformLibraryFromListToMap(
                        Files.readAllLines(Path.of("resources/libraries/ViniaminLibrary/basics.txt")))));
    }

    private boolean mapsEquals(Map<String, String> first, Map<String, String> second) {
        boolean flag = false;
        for (Map.Entry<String, String> entry : first.entrySet()) {
            if (second.containsKey(entry.getKey()) && second.containsValue(entry.getValue())) {
                flag = true;
            } else {
                return false;
            }
        }
        return flag;
    }


    // @After
    // public void deleteAllTestFiles() throws IOException {
    //     Files.delete(Path.of("resources/userInfo.txt"));
    //     Files.delete(Path.of("resources/libraries/ViniaminLibrary"));
    //     Files.delete(Path.of("resources/libraries"));
    //     Files.delete(Path.of("resources"));
    // }
}

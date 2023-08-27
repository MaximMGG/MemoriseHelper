package com.memorisehelper.filesystemTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.memorisehelper.filesystem.DiskWorker;
import com.memorisehelper.user.User;

public class DiskWorkerTest {
    
    private DiskWorker diskWorker = DiskWorker.getInstance();
    private User user = User.getUser();

    @Test
    public void firstInitializeTest() throws IOException {
        user.setUserName("Viniamin");
        diskWorker.firstInitialize();

        assertTrue(Files.exists(Path.of("resources/userInfo.txt")));
        assertTrue(Files.isDirectory(Path.of("resources/libraries/viniaminLibrary")));
        assertTrue(userInfoEqualse());
    }

    private boolean userInfoEqualse() throws IOException {
        List<String> userInfo = new ArrayList<>();
        userInfo.add("userName: Viniamin");
        userInfo.add("userLibraries: ");
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

    @After
    public void deleteAllTestFiles() throws IOException {
        Files.delete(Path.of("resources/userInfo.txt"));
    }
}

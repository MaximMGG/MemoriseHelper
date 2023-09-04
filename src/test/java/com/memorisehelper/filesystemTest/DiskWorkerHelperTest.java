package com.memorisehelper.filesystemTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.memorisehelper.filesystem.DiskWorkerHelper;
import com.memorisehelper.filesystem.Pathes;

public class DiskWorkerHelperTest {
    
    private static DiskWorkerHelper diskWorkerHelper = DiskWorkerHelper.getInstance();

    @Test
    public void userInfoCreateSuccessTest() throws IOException {
        diskWorkerHelper.createUserInfo();

        assertTrue(Files.exists(Path.of(Pathes.PATH_TO_USERINFO)));
        List<String> userInfo = Files.readAllLines(Path.of(Pathes.PATH_TO_USERINFO));
        assertEquals(userInfo.get(0), "user:");
        assertEquals(userInfo.get(1), "userLibraries:");
    }


    @After
    public void deleteAllFiles() throws IOException {
        Files.deleteIfExists(Path.of(Pathes.PATH_TO_USERINFO));
        Files.deleteIfExists(Path.of("resources/libraries"));
        Files.deleteIfExists(Path.of("resources"));
    }
}

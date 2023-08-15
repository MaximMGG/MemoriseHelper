package com.memorisehelper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.memorisehelper.filesystem.DiskWorker;

public class DiskWorkerTest {
    
    @Test
    public void userInitializeTest() throws IOException {
        DiskWorker dw = new DiskWorker("Petr");
        assertTrue(Files.exists(Path.of("resources/userInfo.txt")));
    }

    @Test
    public void saceLibraryOnDiskTest() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("Cat", "qqq");
        map.put("Cat", "qql");
        map.put("Cat", "qqj");
        DiskWorker dw = new DiskWorker("Petr");
        dw.saveLibraryOnDisk(map, "kitty");
        assertTrue(Files.exists(Path.of("resources/libraries/PetrLibrary/kitty.txt")));
    }
}

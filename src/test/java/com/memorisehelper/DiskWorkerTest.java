package com.memorisehelper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
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
        map.put("Caa", "qql");
        map.put("Caq", "qqj");
        DiskWorker dw = new DiskWorker("Petr");
        dw.saveLibraryOnDisk(map, "kitty");
        assertTrue(Files.exists(Path.of("resources/libraries/PetrLibrary/kitty.txt")));
    }

    @Test
    public void getLibraryContentTest() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("Cat", "qqq");
        map.put("Caa", "qql");
        map.put("Caq", "qqj");
        DiskWorker dw = new DiskWorker("Petr");
        dw.saveLibraryOnDisk(map, "kitty");
        List<String> list = new ArrayList<>();
        list.add("Caa : qql");
        list.add("Caq : qqj");
        list.add("Cat : qqq");
        assertEquals(list, dw.getLibraryContent("kitty"));
    }

    @After
    public void deleteFiles() throws IOException {
        Files.delete(Path.of("resources/libraries/PetrLibrary/kitty.txt"));
    }
}

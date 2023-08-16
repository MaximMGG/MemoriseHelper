package com.memorisehelper;

import static org.junit.Assert.*;

import org.junit.Test;

import com.memorisehelper.utils.DiskCheckerUtils;

public class DiskCheckerUtilTest {



    @Test
    public void checkExistLibraryTest() {
        assertTrue(DiskCheckerUtils.checkExistLibrary("Petr", "Hello"));
        assertTrue(DiskCheckerUtils.checkExistLibrary("petr", "hello"));
        assertFalse(DiskCheckerUtils.checkExistLibrary("Petr", "Hella"));
        assertFalse(DiskCheckerUtils.checkExistLibrary("qqqq", "wwww"));

    }
}

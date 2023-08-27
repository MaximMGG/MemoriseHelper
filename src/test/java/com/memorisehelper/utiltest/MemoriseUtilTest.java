package com.memorisehelper.utiltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.memorisehelper.utils.MemoriseUtils;

public class MemoriseUtilTest {

    private Map<String, String> mapLibrary;
    private List<String> listLibrary;

    @Before
    public void initialize() {
        mapLibrary = new HashMap<>();
        mapLibrary.put("cat", "кот, кошка");
        mapLibrary.put("dog", "собака, пес");
        mapLibrary.put("man", "мужчина");
        mapLibrary.put("woman", "жетщина");

        listLibrary = new ArrayList<>();
        listLibrary.add("cat : кот, кошка");
        listLibrary.add("dog : собака, пес");
        listLibrary.add("man : мужчина");
        listLibrary.add("woman : жетщина");
    }
    
    @Test
    public void transformLibraryFromListToMapTest() {
        Map<String, String> transformLibrary = MemoriseUtils.transformLibraryFromListToMap(listLibrary);
        assertTrue(mapsEquals(mapLibrary, transformLibrary));
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
}

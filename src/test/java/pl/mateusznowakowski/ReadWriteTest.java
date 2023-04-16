package pl.mateusznowakowski;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadWriteTest {

    @Test
    void createTypeList() {
        ReadWrite readWrite = new ReadWrite();
        ArrayList<String> types = new ArrayList<>();
        types.add("hydrogen");
        types.add("oxygen");
        types.add("nitrogen");
        String result = readWrite.createTypeList(types);
        assertEquals("(hydrogen oxygen )", result);

    }
    @Test
    void createTypeListEmpty() {
        ReadWrite readWrite = new ReadWrite();
        ArrayList<String> types = new ArrayList<>();
        types.add("hydrogen");
        String result = readWrite.createTypeList(types);
        assertEquals("()", result);

    }
}
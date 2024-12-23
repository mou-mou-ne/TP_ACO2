package test;

import org.junit.jupiter.api.Test;

import selection.Selection_implement;

import static org.junit.jupiter.api.Assertions.*;

class Selection_implementTest {

    @Test
    void testGetBeginIndex() {
        StringBuilder buffer = new StringBuilder("Hello World");
        Selection_implement selection = new Selection_implement(buffer);
        assertEquals(0, selection.getBeginIndex());
    }

    @Test
    void testGetEndIndex() {
        StringBuilder buffer = new StringBuilder("Hello World");
        Selection_implement selection = new Selection_implement(buffer);
        assertEquals(0, selection.getEndIndex());
    }

    @Test
    void testSetBeginIndexValid() {
        StringBuilder buffer = new StringBuilder("Hello World");
        Selection_implement selection = new Selection_implement(buffer);
        selection.setBeginIndex(5);
        assertEquals(5, selection.getBeginIndex());
    }

    @Test
    void testSetBeginIndexInvalid() {
        StringBuilder buffer = new StringBuilder("Hello World");
        Selection_implement selection = new Selection_implement(buffer);
        assertThrows(IllegalArgumentException.class, () -> {
            selection.setBeginIndex(-1);  // Invalid index
        });
    }

    @Test
    void testSetEndIndex() {
        StringBuilder buffer = new StringBuilder("Hello World");
        Selection_implement selection = new Selection_implement(buffer);
        selection.setEndIndex(5);
        assertEquals(5, selection.getEndIndex());
    }
}

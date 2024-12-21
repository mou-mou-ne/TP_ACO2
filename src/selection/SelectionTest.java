package selection;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SelectionTest {

    private Selection_implement selection;

    @BeforeEach
    void setUp() {
        // Initialize the Selection_implement with some text
        selection = new Selection_implement("Hello, World!");
    }

    @Test
    void testGetBeginIndexDefault() {
        assertEquals(0, selection.getBeginIndex(), "Default begin index should be 0");
    }

    @Test
    void testGetEndIndexDefault() {
        assertEquals(0, selection.getEndIndex(), "Default end index should be 0");
    }

    @Test
    void testSetBeginIndexValid() {
        selection.setBeginIndex(5);
        assertEquals(5, selection.getBeginIndex(), "Begin index should be updated to 5");
    }

    @Test
    void testSetEndIndexValid() {
        selection.setEndIndex(7);
        assertEquals(7, selection.getEndIndex(), "End index should be updated to 7");
    }

    @Test
    void testSetBeginIndexOutOfBounds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            selection.setBeginIndex(-1);
        });
        assertEquals("Begin index out of bounds", exception.getMessage());
    }

    @Test
    void testSetEndIndexOutOfBounds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            selection.setEndIndex(100);
        });
        assertEquals("End index out of bounds or less than begin index", exception.getMessage());
    }

    @Test
    void testSetEndIndexLessThanBeginIndex() {
        selection.setBeginIndex(5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            selection.setEndIndex(3);
        });
        assertEquals("End index out of bounds or less than begin index", exception.getMessage());
    }

    @Test
    void testGetSelectedText() {
        selection.setBeginIndex(0);
        selection.setEndIndex(5);
        assertEquals("Hello", selection.getSelectedText(), "Selected text should be 'Hello'");
    }

    @Test
    void testReplaceSelection() {
        selection.setBeginIndex(7);
        selection.setEndIndex(12);
        selection.replaceSelection("Universe");
        assertEquals("Hello, Universe!", selection.getBufferContent(), "Buffer content should be updated to 'Hello, Universe!'");
    }

    @Test
    void testReplaceSelectionAdjustIndices() {
        selection.setBeginIndex(7);
        selection.setEndIndex(12);
        selection.replaceSelection("Galaxy");
        assertEquals(13, selection.getEndIndex(), "End index should be updated after replacement");
    }

    @Test
    void testGetBufferContent() {
        assertEquals("Hello, World!", selection.getBufferContent(), "Buffer content should be initialized correctly");
    }

}


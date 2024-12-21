package selection;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Selection_implement.
 */
public class SelectionImplementTest {

    private Selection_implement selection;

    /**
     * Set up a fresh instance of Selection_implement before each test.
     */
    @BeforeEach
    public void setUp() {
        selection = new Selection_implement("This is a test buffer.");
    }

    @Test
    public void testInitialBufferContent() {
        assertEquals("This is a test buffer.", selection.getBufferContent());
    }

    @Test
    public void testSetBeginIndexValid() {
        selection.setBeginIndex(5);
        assertEquals(5, selection.getBeginIndex());
    }

    @Test
    public void testSetBeginIndexInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> selection.setBeginIndex(-1));
        assertEquals("Begin index out of bounds", exception.getMessage());
    }

    @Test
    public void testSetEndIndexValid() {
        selection.setEndIndex(10);
        assertEquals(10, selection.getEndIndex());
    }

    @Test
    public void testSetEndIndexExceedsBufferLength() {
        selection.setEndIndex(50); // Larger than buffer length
        assertEquals(selection.getBufferContent().length(), selection.getEndIndex());
    }

    @Test
    public void testGetSelectedText() {
        selection.setBeginIndex(0);
        selection.setEndIndex(4);
        assertEquals("This", selection.getSelectedText());
    }

    @Test
    public void testReplaceSelection() {
        selection.setBeginIndex(10);
        selection.setEndIndex(14);
        selection.replaceSelection("demo");
        assertEquals("This is a demo buffer.", selection.getBufferContent());
    }

    @Test
    public void testReplaceSelectionNull() {
        selection.setBeginIndex(5);
        selection.setEndIndex(7);
        selection.replaceSelection(null); // No change should occur
        assertEquals("This is a test buffer.", selection.getBufferContent());
    }

    @Test
    public void testSetClipboard() {
        selection.setClipboard("Copied text");
        assertEquals("Copied text", selection.getClipboard());
    }

    @Test
    public void testClipboardIntegration() {
        selection.setBeginIndex(0);
        selection.setEndIndex(4);
        selection.setClipboard(selection.getSelectedText());
        assertEquals("This", selection.getClipboard());
    }
}

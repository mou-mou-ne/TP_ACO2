package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import engine.Engine_implementation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Engine_implementation class.
 */
public class EngineImplementationTest {

    private Engine_implementation engine;

    @BeforeEach
    public void setUp() {
        // Initialise un Engine_implementation avec un texte initial
        engine = new Engine_implementation("This is a simple test buffer.");
    }

    /**
     * Test to verify that the buffer contents are correctly retrieved.
     */
    @Test
    public void testGetBufferContents() {
        assertEquals("This is a simple test buffer.", engine.getBufferContents(),
            "The buffer contents should match the initial text.");
    }

    /**
     * Test to verify that the clipboard contents are correctly retrieved.
     */
    @Test
    public void testGetClipboardContents() {
        assertEquals("", engine.getClipboardContents(), 
            "Initially, the clipboard should be empty.");
    }

    /**
     * Test to verify the copy operation.
     */
    @Test
    public void testCopySelectedText() {
        engine.copySelectedText(0, 4); // Copie "This"
        assertEquals("This", engine.getClipboardContents(), 
            "The clipboard should contain the copied text.");
    }

    /**
     * Test to verify the cut operation.
     */
    @Test
    public void testCutSelectedText() {
        engine.cutSelectedText(0, 4); // Coupe "This"
        assertEquals("This", engine.getClipboardContents(), 
            "The clipboard should contain the cut text.");
        assertEquals(" is a simple test buffer.", engine.getBufferContents(), 
            "The buffer should reflect the text after cutting.");
    }

    /**
     * Test to verify the paste operation.
     */
    @Test
    public void testPasteClipboard() {
        engine.copySelectedText(0, 4); // Copie "This"
        engine.pasteClipboard(10); // Colle à la position 10
        assertEquals("This is a simple test buffer.This", engine.getBufferContents(),
            "The buffer should include the pasted text.");
    }

    /**
     * Test to verify setting the buffer content.
     */
    @Test
    public void testSetBufferContents() {
        engine.setBufferContents("New content for the buffer.");
        assertEquals("New content for the buffer.", engine.getBufferContents(), 
            "The buffer content should be updated.");
    }

    /**
     * Test to verify setting the clipboard contents.
     */
    @Test
    public void testSetClipboardContents() {
        engine.setClipboardContents("Copied Text");
        assertEquals("Copied Text", engine.getClipboardContents(), 
            "The clipboard content should be updated.");
    }

    /**
     * Test to verify an invalid copy operation with out-of-bounds indices.
     */
    @Test
    public void testCopySelectedTextOutOfBounds() {
        assertThrows(StringIndexOutOfBoundsException.class, 
            () -> engine.copySelectedText(0, 50), 
            "Copy operation should throw an exception for out-of-bounds indices.");
    }

    /**
     * Test to verify an invalid cut operation with out-of-bounds indices.
     */
    @Test
    public void testCutSelectedTextOutOfBounds() {
        assertThrows(StringIndexOutOfBoundsException.class, 
            () -> engine.cutSelectedText(0, 50), 
            "Cut operation should throw an exception for out-of-bounds indices.");
    }

    /**
     * Test to verify that paste does not insert anything when the clipboard is empty.
     */
    @Test
    public void testPasteEmptyClipboard() {
        engine.pasteClipboard(10); // Essayez de coller un presse-papiers vide
        assertEquals("This is a simple test buffer.", engine.getBufferContents(), 
            "The buffer should not change when the clipboard is empty.");
    }
}
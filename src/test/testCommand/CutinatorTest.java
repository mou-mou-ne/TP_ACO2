package test.testCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Command.Cutinator;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

import static org.junit.jupiter.api.Assertions.*;

class CutinatorTest {

    private Engine_implementation engine;
    private recorderImpl recorder;
    private UndoManagerinatorImpl undo;
    private Cutinator cutinator;

    @BeforeEach
    void setUp() {
        engine = new Engine_implementation();
        recorder = new recorderImpl();
        undo = new UndoManagerinatorImpl(engine);
        cutinator = new Cutinator(engine, recorder, undo);
    }

    @Test
    void testExecuteWithValidSelection() {
        // Set up the engine with some text and selection
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5); // Select "Hello"

        // Perform the cut operation
        cutinator.execute();

        // Assert that the clipboard contains the cut text ("Hello")
        assertEquals("Hello", engine.getClipboardContents());

        // Assert that the text was removed from the buffer (the buffer should now be " World")
        assertEquals(" World", engine.getBufferContents());

        // Assert that the selection is cleared after cutting
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    void testExecuteWithEmptySelection() {
        // Set up the engine with some text and an empty selection
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0); // Empty selection

        // Perform the cut operation
        cutinator.execute();

        // Assert that the clipboard is empty since no text was selected
        assertEquals("", engine.getClipboardContents());

        // Assert that the buffer remains unchanged
        assertEquals("Hello World", engine.getBufferContents());
    }

    @Test
    void testUndoAfterCut() {
        // Set up the engine with some text and selection
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5); // Select "Hello"

        // Perform the cut operation
        cutinator.execute();

        // Assert that the clipboard contains the cut text ("Hello")
        assertEquals("Hello", engine.getClipboardContents());

        // Perform undo
        undo.undo();

        // Assert that the buffer and clipboard are restored
        assertEquals("Hello World", engine.getBufferContents()); // The buffer should be restored to its previous state
        assertEquals("", engine.getClipboardContents()); // The clipboard should be empty after undo
    }

    @Test
    void testRedoAfterCut() {
        // Set up the engine with some text and selection
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5); // Select "Hello"

        // Perform the cut operation
        cutinator.execute();

        // Perform undo
        undo.undo();

        // Perform redo
        undo.redo();

        // Assert that the buffer and clipboard reflect the redo operation
        assertEquals(" World", engine.getBufferContents()); // The buffer should reflect the cut operation
        assertEquals("Hello", engine.getClipboardContents()); // The clipboard should contain the cut text again
    }

    @Test
    void testInvalidSelectionThrowsException() {
        // Set up the engine with some text and an invalid selection
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(10); // Selection out of bounds
        engine.getSelection().setEndIndex(15); // Selection out of bounds

        // Try to cut text and assert that an exception is thrown
        assertThrows(IllegalArgumentException.class, () -> cutinator.execute());
    }

    @Test
    void testCutAfterTextInsert() {
        // Insert text and set selection
        engine.insert("Some text to cut");
        engine.getSelection().setBeginIndex(5); // Select the first "text"
        engine.getSelection().setEndIndex(9); // Select "text"

        // Perform the cut operation
        cutinator.execute();

        // Assert the clipboard contains the selected text ("text")
        assertEquals("text", engine.getClipboardContents());

        // Assert the buffer is updated (the selected text is removed)
        assertEquals("Some  to cut", engine.getBufferContents());
    }
}

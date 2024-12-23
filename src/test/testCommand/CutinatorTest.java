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
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);

        cutinator.execute();

        assertEquals("Hello", engine.getClipboardContents());
        assertEquals(" World", engine.getBufferContents());
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    void testExecuteWithEmptySelection() {
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        cutinator.execute();

        assertEquals("", engine.getClipboardContents());
        assertEquals("Hello World", engine.getBufferContents());
    }

    @Test
    void testUndoAfterCut() {
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);

        cutinator.execute();

        undo.undo();

        assertEquals("Hello World", engine.getBufferContents());
        assertEquals("", engine.getClipboardContents());
    }

    @Test
    void testRedoAfterCut() {
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);

        cutinator.execute();
        undo.undo();
        undo.redo();

        assertEquals(" World", engine.getBufferContents());
        assertEquals("Hello", engine.getClipboardContents());
    }

    @Test
    void testInvalidSelectionThrowsException() {
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(10);
        engine.getSelection().setEndIndex(15);

        assertThrows(IllegalArgumentException.class, () -> cutinator.execute());
    }

    @Test
    void testCutAfterTextInsert() {
        engine.insert("Some text to cut");
        engine.getSelection().setBeginIndex(5);
        engine.getSelection().setEndIndex(9);

        cutinator.execute();

        assertEquals("text", engine.getClipboardContents());
        assertEquals("Some  to cut", engine.getBufferContents());
    }

    @Test
    void testCutWithBoundarySelection() {
        engine.insert("Boundary Test");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(8); 

        cutinator.execute();

        assertEquals("Boundary", engine.getClipboardContents());
        assertEquals(" Test", engine.getBufferContents());
    }
}

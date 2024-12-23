package test;

import org.junit.jupiter.api.Test;

import engine.Engine_implementation;

import undoManagerinator.UndoManagerinatorImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class Engine_implementationTest {

	private Engine_implementation engine;
    private UndoManagerinatorImpl undoManager;

    @BeforeEach
    void setUp() {
        engine = new Engine_implementation();
        undoManager = new UndoManagerinatorImpl(engine);
    }

    @Test
    void testInsertText() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        engine.insert("Hello");
        assertEquals("Hello", engine.getBufferContents());
    }

    @Test
    void testCopyText() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        engine.insert("Test");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(4);

        engine.copySelectedText();
        assertEquals("Test", engine.getClipboardContents());
    }

    @Test
    void testPasteClipboard() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        engine.insert("Hello");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);

        engine.copySelectedText();
        engine.getSelection().setBeginIndex(5);
        engine.getSelection().setEndIndex(5);
        engine.pasteClipboard();

        assertEquals("HelloHello", engine.getBufferContents());
    }

    @Test
    void testCutText() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        engine.insert("CutTest");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(7);

        engine.cutSelectedText();
        assertEquals("", engine.getBufferContents());
        assertEquals("CutTest", engine.getClipboardContents());
    }

    @Test
    void testDeleteText() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        engine.insert("DeleteTest");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(6);

        engine.delete();
        assertEquals("Test", engine.getBufferContents());
    }

    @Test
    void testUndo() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        undoManager.store();             

        engine.insert("potato");
        undoManager.store();             
        
        engine.insert(" World");
        undoManager.undo();
        
        assertEquals("potato", engine.getBufferContents());
    }

    @Test
    void testRedo() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        engine.insert("Hello");
        undoManager.store();

        engine.insert(" World");
        undoManager.undo();
        undoManager.redo();
        
        assertEquals("Hello World", engine.getBufferContents());
    }

}
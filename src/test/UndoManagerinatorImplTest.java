package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

class UndoManagerinatorImplTest {

    private Engine_implementation engine;
    private UndoManagerinatorImpl undoManager;

    @BeforeEach
    void setUp() {
        engine = new Engine_implementation();
        undoManager = new UndoManagerinatorImpl(engine);
    }

    @Test
    void testStoreMemento() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        engine.insert("Hello");

        undoManager.store();
        assertEquals(2, undoManager.getList().size()); // Initial state + current state
    }

    @Test
    void testUndoFunctionality() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        engine.insert("potato");
        undoManager.store();
        
        assertEquals("potato", undoManager.getList().get(1).getBufferContent().toString()); // After storing the state
        
        engine.insert(" World");
        undoManager.undo();

        assertEquals("potato", engine.getBufferContents());
        assertEquals(1, undoManager.getList().size()); // Ensure only 1 state is in the history after undo
    }

    @Test
    void testRedoFunctionality() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        engine.insert("Hello");
        undoManager.store();

        engine.insert(" World");
        undoManager.store();
        undoManager.undo();

        undoManager.undo();
        assertEquals("Hello", engine.getBufferContents());

        undoManager.redo();

        undoManager.redo();
        assertEquals("Hello World", engine.getBufferContents());
    }

    @Test
    void testUndoAndRedoMultipleTimes() {
        // Multiple undo and redo
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        engine.insert("Hello");
        undoManager.store();

        engine.insert(" World");
        undoManager.store();

        engine.insert("!");
        undoManager.store();

        undoManager.undo(); 
        undoManager.undo(); 

        assertEquals("Hello World", engine.getBufferContents());

        undoManager.undo();  // Should undo "World"
        assertEquals("Hello", engine.getBufferContents());

        undoManager.redo();  // Should redo "World"
        assertEquals("Hello World", engine.getBufferContents());

        undoManager.redo();  // Should redo "!"
        assertEquals("Hello World!", engine.getBufferContents());
    }

    @Test
    void testUndoWithoutStore() {
        // Test undo without storing any states
        try {
            undoManager.undo();
            undoManager.undo();

            fail("Exception should be thrown when attempting to undo with no stored states.");
        } catch (IllegalStateException e) {
            assertEquals("No state to undo", e.getMessage());
        }
    }

    @Test
    void testRedoWithoutUndo() {
        // Test redo without performing an undo
        engine.insert("Hello");
        undoManager.store();

        try {
            undoManager.redo();
            fail("Exception should be thrown when attempting to redo with no undone state.");
        } catch (IllegalStateException e) {
            assertEquals("No state to redo", e.getMessage());
        }
    }


    @Test
    void testMultipleRedos() {
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);
        engine.insert("First");
        undoManager.store();

        engine.insert("Second");
        undoManager.store();

        engine.insert("Third");
        undoManager.store();

        undoManager.undo();
        undoManager.undo();
        undoManager.undo();
        undoManager.undo();

        // Ensure we are back to the initial state
        assertEquals("", engine.getBufferContents());

        // Try to redo more times than available
        try {
            undoManager.redo();
            undoManager.redo();
            System.out.println(engine.getBufferContents());

            undoManager.redo();
            System.out.println(engine.getBufferContents());
            undoManager.redo();
            undoManager.redo();


            fail("Exception should be thrown when attempting to redo past the number of undone actions.");
        } catch (IllegalStateException e) {
            assertEquals("No state to redo", e.getMessage());
        }
    }
}

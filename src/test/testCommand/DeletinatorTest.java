package test.testCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Command.Deletinator;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

import static org.junit.jupiter.api.Assertions.*;

class DeletinatorTest {

    private Engine_implementation engine;
    private recorderImpl recorder;
    private UndoManagerinatorImpl undo;
    private Deletinator deletinator;

    @BeforeEach
    void setUp() {
        engine = new Engine_implementation();
        recorder = new recorderImpl();
        undo = new UndoManagerinatorImpl(engine);
        deletinator = new Deletinator(engine, recorder, undo);
        
    }

    @Test
    void testExecuteWithValidSelection() {
        // Set up the engine with some text and selection
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5); // Select "Hello"

        deletinator.execute();

        assertEquals(" World", engine.getBufferContents());

        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    void testExecuteWithEmptySelection() {
        // Set up the engine with some text
        engine.insert("Hello World");

        // No selection: Begin and end indices are the same (empty selection)
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        // Perform the delete operation
        deletinator.execute();

        // Assert that no changes have been made to the buffer
        assertEquals("Hello World", engine.getBufferContents());

        // Assert that the selection remains unchanged
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    void testExecuteWithOutOfBoundsSelection() {
        // Set up the engine with some text
        engine.insert("Hello World");

        // Invalid selection: End index is out of bounds
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(20); // Beyond the buffer length

        // Perform the delete operation and assert that an exception is thrown
        assertThrows(IllegalArgumentException.class, () -> deletinator.execute());
    }

    
    

}

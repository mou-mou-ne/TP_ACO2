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
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5); 

        deletinator.execute();

        assertEquals(" World", engine.getBufferContents()); 
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    void testExecuteWithEmptySelection() {
        engine.insert("Hello World");

        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        deletinator.execute();

        assertEquals("Hello World", engine.getBufferContents());
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(0, engine.getSelection().getEndIndex());
    }

    @Test
    void testExecuteWithOutOfBoundsSelection() {
        engine.insert("Hello World");

        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(20); 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deletinator.execute());
        assertEquals("Invalid selection: out of bounds or empty selection.", exception.getMessage());
    }

    @Test
    void testUndoAfterExecute() {
        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5); 

        deletinator.execute(); 

 
        assertEquals(" World", engine.getBufferContents());

        undo.undo();
        assertEquals("Hello World", engine.getBufferContents()); 
    }

    @Test
    void testDeleteEmptyText() {
        engine.insert("");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0); 

        deletinator.execute(); 

        assertEquals("", engine.getBufferContents());
    }
}

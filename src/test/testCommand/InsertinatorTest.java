package test.testCommand;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Command.Insertinator;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import mementomoris.InsertMemento;
import mementomoris.Memento;
import undoManagerinator.UndoManagerinatorImpl;
import Invoker.Invoker;

public class InsertinatorTest {
    private Engine_implementation engine;
    private Invoker invoker;
    private recorderImpl recorder;
    private Insertinator insertCommand;
    private UndoManagerinatorImpl undoer;

    @Before
    public void setUp() {
        engine = new Engine_implementation();
        invoker = new Invoker();
        undoer = new UndoManagerinatorImpl(engine);
        recorder = new recorderImpl();
        insertCommand = new Insertinator(engine, invoker, recorder, undoer);
    }

    @Test
    public void testExecute() {
        invoker.setTextToInsert("Box Box");
        insertCommand.execute();
        
        assertTrue(engine.getBufferContents().contains("Box Box"));
    }

    @Test
    public void testUndoAfterExecute() {
        invoker.setTextToInsert("Hamilton chez Ferrari");
        insertCommand.execute();
        
        String bufferBeforeUndo = engine.getBufferContents();
        assertTrue(bufferBeforeUndo.contains("Hamilton chez Ferrari"));

        undoer.undo();
        undoer.undo();

        assertFalse(engine.getBufferContents().contains("Hamilton chez Ferrari"));
    }

    @Test
    public void testGetMemento() {
        invoker.setTextToInsert("doofenshmirtz");
        Memento memento = insertCommand.getMemento();
        
        assertNotNull(memento);
        assertEquals("doofenshmirtz", ((InsertMemento) memento).getTextToInsert());
    }

    @Test
    public void testSetMemento() {
        invoker.setTextToInsert("Un phantome ornitorinx ?");
        Memento memento = insertCommand.getMemento();
        
        insertCommand.setMemento(memento);
        
        assertEquals("Un phantome ornitorinx ?", invoker.getTextToInsert());
    }

    @Test
    public void testRecorderInteraction() {
        invoker.setTextToInsert("Peery le phantome ornitorinx ?");
        insertCommand.execute();
        
        assertTrue(recorder.getList().size() > 0);
    }

    @Test
    public void testEmptyTextInsertion() {
        invoker.setTextToInsert("");
        insertCommand.execute();
        
        assertEquals( "", engine.getBufferContents());
    }

    @Test
    public void testBufferContentsAfterMultipleInserts() {
        invoker.setTextToInsert("Perryyyy");
        insertCommand.execute();
        
        String bufferAfterFirstInsert = engine.getBufferContents();
        assertTrue(bufferAfterFirstInsert.contains("Perryyyy"));
        
        invoker.setTextToInsert("l'ornithorinx");
        insertCommand.execute();
        
        String bufferAfterSecondInsert = engine.getBufferContents();
        assertTrue( bufferAfterSecondInsert.contains("l'ornithorinx"));
        assertTrue( bufferAfterSecondInsert.contains("Perryyyy") && bufferAfterSecondInsert.contains("l'ornithorinx"));
    }
}

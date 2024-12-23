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
        insertCommand = new Insertinator(engine, invoker, recorder,undoer);
        
    }

    @Test
    public void testExecute() {
        invoker.setTextToInsert("Test Text");
        insertCommand.execute();
        // Verify the text is inserted correctly in the engine
        assertTrue(engine.getBufferContents().contains("Test Text"));
    }

    @Test
    public void testGetMemento() {
        invoker.setTextToInsert("Test Text");
        Memento memento = insertCommand.getMemento();
        assertNotNull(memento);
        // Verify memento contains correct text to insert
        assertEquals("Test Text", ((InsertMemento) memento).getTextToInsert());
    }

    @Test
    public void testSetMemento() {
        invoker.setTextToInsert("Test Text");
        Memento memento = insertCommand.getMemento();
        insertCommand.setMemento(memento);
        assertEquals("Test Text", invoker.getTextToInsert());
    }
}

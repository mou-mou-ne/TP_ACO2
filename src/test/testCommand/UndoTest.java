package test.testCommand;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Command.Undo;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

public class UndoTest {

    private UndoManagerinatorImpl undo;
    private Undo undoCommand;
    private Engine_implementation engine;

    @Before
    public void setUp() {
    	engine = new Engine_implementation();
        undo = new UndoManagerinatorImpl(engine);
        undoCommand = new Undo(undo);
    }

    @Test
    public void testUndoCommand() {


        assertTrue(undo.getList().size() > 0);

        undoCommand.execute();

        assertEquals(0, undo.getList().size());  
    }
}

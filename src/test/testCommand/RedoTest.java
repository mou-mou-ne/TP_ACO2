package test.testCommand;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Command.Redo;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

public class RedoTest {

    private UndoManagerinatorImpl undo;
    private Redo redoCommand;
    private Engine_implementation engine;


    @Before
    public void setUp() {
    	engine = new Engine_implementation();

        undo = new UndoManagerinatorImpl(engine);
        redoCommand = new Redo(undo);

    }

    @Test
    public void testRedoCommand() {
    	 engine.getSelection().setBeginIndex(0);
         engine.getSelection().setEndIndex(0);
         engine.insert("Hello");
         undo.store();

         engine.insert(" World");
         undo.store();
         undo.undo();

         undo.undo();

        redoCommand.execute();

        assertEquals(1, undo.getList().size());  
    }
}

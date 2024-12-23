package test.testCommand;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Command.Select;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;
import Invoker.Invoker;

public class SelectTest {
    private Engine_implementation engine;
    private Invoker invoker;
    private recorderImpl recorder;
    private Select selectCommand;
    private UndoManagerinatorImpl undoer;

    @Before
    public void setUp() {
        engine = new Engine_implementation();
        invoker = new Invoker();
        recorder = new recorderImpl();
        undoer = new UndoManagerinatorImpl(engine);
        selectCommand = new Select(engine, invoker, recorder, undoer);  // Now undoer is properly initialized
    }

    @Test
    public void testExecuteValidSelection() {
        StringBuilder s = new StringBuilder("un ornythorinx ?");
        engine.setBufferContent(s);
        invoker.setBeg(0);
        invoker.setEnd(5);
        
        // Debugging output
        int bufferLength = engine.getBufferContents().length();
        System.out.println("buffer length: " + bufferLength);  
        System.out.println("invoker end: " + invoker.getEnd()); 

        selectCommand.execute();        
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(5, engine.getSelection().getEndIndex()); 
    }

    @Test
    public void testExecuteWithNegativeBeginIndex() {
        try {
            invoker.setBeg(-1);
            selectCommand.execute();
            fail("Exception should be thrown for negative begin index");
        } catch (IllegalArgumentException e) {
            assertEquals("Begin index out of bounds", e.getMessage());
        }
    }

    @Test
    public void testExecuteWithBeginIndexGreaterThanBufferLength() {
        invoker.setBeg(100);  // Assuming buffer length is less than 100
        try {
            selectCommand.execute();
            fail("Exception should be thrown for begin index greater than buffer length");
        } catch (IllegalArgumentException e) {
            assertEquals("Begin index out of bounds", e.getMessage());
        }
    }

    @Test
    public void testExecuteWithNegativeEndIndex() {
        try {
            invoker.setEnd(-5);
            selectCommand.execute();
            fail("Exception should be thrown for negative end index");
        } catch (IllegalArgumentException e) {
            assertEquals("End index out of bounds", e.getMessage());
        }
    }

 

    @Test
    public void testExecuteWithBeginIndexGreaterThanEndIndex() {
    	StringBuilder s = new StringBuilder("Perry l'ornythorinx !");
    	engine.setBufferContent(s);
        invoker.setEnd(1);
        invoker.setBeg(5);

        try {
            selectCommand.execute();
            fail("Exception should be thrown for begin index greater than end index");
        } catch (IllegalArgumentException e) {
            assertEquals("Begin index out of bounds", e.getMessage());
        }
    }
}

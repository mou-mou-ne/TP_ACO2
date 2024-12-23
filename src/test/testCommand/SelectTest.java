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
    public void testExecute() {
        invoker.setBeg(0);
        invoker.setEnd(5);
        selectCommand.execute();
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(5, engine.getSelection().getEndIndex());
    }
}


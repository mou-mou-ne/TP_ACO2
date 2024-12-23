package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Command.CommandOriginator;
import Command.Deletinator;
import Command.Insertinator;
import Invoker.Invoker;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import mementomoris.Memento;
import undoManagerinator.UndoManagerinatorImpl;

public class RecorderImplTest {
    private recorderImpl recorder;
    private Invoker invoker;
    private Engine_implementation engine;
    private UndoManagerinatorImpl undoer;

    @Before
    public void setUp() {
        recorder = new recorderImpl();
        invoker = new Invoker();
        engine = new Engine_implementation();
        undoer = new UndoManagerinatorImpl(engine);
        
    
    }

    @Test
    public void testInitialState() {
        assertTrue("Command list should initially be empty.", recorder.getList().isEmpty());
    }

    @Test
    public void testStartRecording() {
        recorder.start();
        assertTrue("Recorder should be started after calling start.", recorder.getStarted());
    }

    @Test
    public void testStopRecording() {
        recorder.start();
        recorder.stop();
        assertFalse("Recorder should be stopped after calling stop.", recorder.getStarted());
    }

    @Test
    public void testRecordCommand() {
        Insertinator insert = new Insertinator(engine,invoker,recorder,undoer);
        Deletinator deletine = new Deletinator(engine,recorder,undoer);

        recorder.start();
        recorder.save(insert);
        recorder.save(deletine);
        
        List<Map<CommandOriginator, Memento>> l = recorder.getList();
        

        assertEquals("Recorder should have recorded 2 commands.", 2, recorder.getList().size());
        assertTrue("Command list should contain 'command1'.", l.getFirst().containsKey(insert));
        assertTrue("Command list should contain 'command2'.",l.getLast().containsKey(deletine));
    }



    @Test
    public void testReplay() {
    	invoker.setTextToInsert("Hello");
        Insertinator insert = new Insertinator(engine,invoker,recorder,undoer);
        recorder.save(insert);
    	
        invoker.setTextToInsert("World");

        Insertinator insert2 = new Insertinator(engine,invoker,recorder,undoer);

        recorder.save(insert2);

        assertEquals(2, recorder.getList().size());

        recorder.replay();

        assertEquals("HelloWorld", engine.getBufferContents());

        assertFalse("Recorder should not be replaying after replay ends.", recorder.getReplaying());
        assertFalse("Recorder should remain stopped after replay.", recorder.getStarted());
    }



    @Test
    public void testStartMultipleTimes() {
        recorder.start();
        recorder.start(); // Should remain started.
        assertTrue("Recorder should remain started after calling start multiple times.", recorder.getStarted());
    }

    @Test
    public void testStopMultipleTimes() {
        recorder.start();
        recorder.stop();
        recorder.stop(); // Should remain stopped.
        assertFalse("Recorder should remain stopped after calling stop multiple times.", recorder.getStarted());
    }
}

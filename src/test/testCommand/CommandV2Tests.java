package test.testCommand;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Command.Start;
import Command.Stop;
import Command.CommandOriginator;
import Command.Copinator;
import Command.Replay;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

public class CommandV2Tests {
    private recorderImpl recorder;
    private Start startCommand;
    private Stop stopCommand;
    private Replay replayCommand;
    private UndoManagerinatorImpl undoer;
    private Engine_implementation engine;

    @Before
    public void setUp() {
        engine = new Engine_implementation();
        recorder = new recorderImpl();
        startCommand = new Start(recorder);
        stopCommand = new Stop(recorder);
        replayCommand = new Replay(recorder);
        undoer = new UndoManagerinatorImpl(engine);
    }

    @Test
    public void testStartCommand() {
        recorder.stop();
        assertFalse(recorder.getStarted());
        startCommand.execute();
        assertTrue(recorder.getStarted());
    }

    @Test
    public void testStopCommand() {
        recorder.start();
        assertTrue(recorder.getStarted());
        stopCommand.execute();
        assertFalse(recorder.getStarted());
    }

    @Test
    public void testReplayCommand() {
        recorder.start();
        CommandOriginator copyCommand = new Copinator(engine, recorder, undoer);
        recorder.save(copyCommand); 
        replayCommand.execute();
        assertFalse(recorder.getStarted());
    }
}

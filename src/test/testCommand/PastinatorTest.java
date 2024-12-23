package test.testCommand;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Command.Pastinator;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

public class PastinatorTest {
    private Engine_implementation engine;
    private recorderImpl recorder;
    private UndoManagerinatorImpl undoer;
    private Pastinator pasteCommand;

    @Before
    public void setUp() {
        engine = new Engine_implementation();
        undoer = new UndoManagerinatorImpl(engine);

        recorder = new recorderImpl();
        pasteCommand = new Pastinator(engine, recorder,undoer);
        engine.setClipboardContent("pasted text");
    }

    @Test
    public void testExecute() {
    	
        pasteCommand.execute();
        assertTrue(engine.getBufferContents().contains("pasted text"));
    }
}


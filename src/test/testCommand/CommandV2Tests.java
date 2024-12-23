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

public class CommandTests {
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
        recorder.stop(); // Assurez-vous que l'enregistreur est arrêté avant de tester
        assertFalse(recorder.getStarted()); // Vérifie l'état initial
        startCommand.execute();
        assertTrue(recorder.getStarted()); // Vérifie que l'état est passé à démarré
    }

    @Test
    public void testStopCommand() {
        recorder.start(); // Assurez-vous que l'enregistreur est démarré avant de tester
        assertTrue(recorder.getStarted()); // Vérifie l'état initial
        stopCommand.execute();
        assertFalse(recorder.getStarted()); // Vérifie que l'état est passé à arrêté
    }

    @Test
    public void testReplayCommand() {
        Engine_implementation engine = new Engine_implementation();
        recorder.start();
        CommandOriginator copyCommand = new Copinator(engine, recorder,undoer);;
		recorder.save(copyCommand); // Simule une commande enregistrée
        replayCommand.execute();
        assertFalse(recorder.getStarted()); // Vérifie que le mode started est désactivé
    }
}

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Command.Command_interface;
import Command.Insertinator;
import Invoker.Invoker;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

class InvokerTest {

    private Invoker invoker;
    private Engine_implementation engine;
    private UndoManagerinatorImpl undoer;
    private recorderImpl recorder;

    @BeforeEach
    void setUp() {
        invoker = new Invoker();
        engine = new Engine_implementation();
        undoer = new UndoManagerinatorImpl(engine);
        recorder = new recorderImpl();
    }

    @Test
    void testAddCommand() {
        Command_interface command = () -> System.out.println("Command executed");

        invoker.addCommand("testCommand", command);

        Map<String, Command_interface> commandList = invoker.getCommandList();
        assertTrue(commandList.containsKey("testCommand"));
        assertEquals(command, commandList.get("testCommand"));
    }

    @Test
    void testPlayCommand() {
        invoker.setTextToInsert("Executed");
        
        Insertinator insert = new Insertinator(engine,invoker,recorder,undoer);
        invoker.addCommand("testCommand", insert);
        invoker.playCommand("testCommand");

        assertEquals("Executed", engine.getBufferContents());
    }

    @Test
    void testPlayCommandWithInvalidKey() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invoker.playCommand("nonExistentCommand");
        });
        assertEquals("No command found with ID: nonExistentCommand", exception.getMessage());
    }


    @Test
    void testGetBegin() {
        invoker.setBeg(10);
        assertEquals(10, invoker.getBegin());
    }

    @Test
    void testSetBegin() {
        invoker.setBeg(15);
        assertEquals(15, invoker.getBegin());
    }

    @Test
    void testGetEnd() {
        invoker.setEnd(20);
        assertEquals(20, invoker.getEnd());
    }

    @Test
    void testSetEnd() {
        invoker.setEnd(25);
        assertEquals(25, invoker.getEnd());
    }

    @Test
    void testGetTextToInsert() {
        invoker.setTextToInsert("Hello, World!");
        assertEquals("Hello, World!", invoker.getTextToInsert());
    }

    @Test
    void testSetTextToInsert() {
        invoker.setTextToInsert("Test String");
        assertEquals("Test String", invoker.getTextToInsert());
    }

    @Test
    void testCommandListInitiallyEmpty() {
        Map<String, Command_interface> commandList = invoker.getCommandList();
        assertTrue(commandList.isEmpty());
    }

    @Test
    void testAddMultipleCommands() {
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();

        invoker.addCommand("command1", () -> result1.append("Executed1"));
        invoker.addCommand("command2", () -> result2.append("Executed2"));

        Map<String, Command_interface> commandList = invoker.getCommandList();
        assertEquals(2, commandList.size());
        assertTrue(commandList.containsKey("command1"));
        assertTrue(commandList.containsKey("command2"));
    }

    @Test
    void testExecuteMultipleCommands() {
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();

        invoker.addCommand("command1", () -> result1.append("Executed1"));
        invoker.addCommand("command2", () -> result2.append("Executed2"));

        invoker.playCommand("command1");
        assertEquals("Executed1", result1.toString());
        assertEquals("", result2.toString());

        invoker.playCommand("command2");
        assertEquals("Executed2", result2.toString());
    }
}

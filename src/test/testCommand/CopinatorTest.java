package test.testCommand;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Command.Copinator;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

class CopinatorTest {

    @Test
    void testCopinatorConstructorValid() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);

        assertDoesNotThrow(() -> new Copinator(engine, recorder, undo));
    }

    @Test
    void testCopinatorConstructorInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Copinator(null, null, null));
    }

    @Test
    void testExecuteWithValidSelection() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);

        copinator.execute();

        assertEquals("Hello", engine.getClipboardContents());
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(5, engine.getSelection().getEndIndex());
    }

    @Test
    void testExecuteWithNoSelection() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0);

        copinator.execute();

        assertEquals("", engine.getClipboardContents());
    }

    @Test
    void testUndoStoreAfterExecute() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        engine.insert("Hello World");
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);

        copinator.execute();

        assertTrue(undo.getList().size() > 0);
    }

    @Test
    void testGetMemento() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        assertNull(copinator.getMemento());
    }

    @Test
    void testSetMemento() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        assertDoesNotThrow(() -> copinator.setMemento(null));
    }
}

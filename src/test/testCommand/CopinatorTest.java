package test.testCommand;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Command.Copinator;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

class CopinatorTest {

    // Test 1: Vérification de la construction valide de Copinator
    @Test
    void testCopinatorConstructorValid() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);

        assertDoesNotThrow(() -> new Copinator(engine, recorder, undo));
    }

    // Test 2: Vérification de la construction invalide de Copinator (argument null)
    @Test
    void testCopinatorConstructorInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Copinator(null, null, null));
    }

    // Test 3: Vérification de la méthode execute avec une sélection valide
    @Test
    void testExecuteWithValidSelection() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        // Set up the buffer and selection properly
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);
        
        // Insert text and ensure the selection is correct
        engine.insert("Hello World");
        
        // Now the selection should span the first 5 characters, "Hello"
        assertEquals("Hello", engine.getBufferContents().substring(0, 5)); // Ensure the buffer has the text we expect
        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);
        copinator.execute();

        // Assert that the clipboard contains the correct text (the first 5 characters of the buffer)
        assertEquals("Hello", engine.getClipboardContents());

        // Assert that the selection range remains unchanged
        assertEquals(0, engine.getSelection().getBeginIndex());
        assertEquals(5, engine.getSelection().getEndIndex());
    }


    // Test 4: Vérification de la méthode execute avec une sélection invalide
    @Test
    void testExecuteWithNoSelection() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(0); // Pas de texte sélectionné
        copinator.execute();

        assertEquals("", engine.getClipboardContents());
    }

  

 

    // Test 7: Vérification que la méthode store est appelée après execute
    @Test
    void testUndoStoreAfterExecute() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        engine.getSelection().setBeginIndex(0);
        engine.getSelection().setEndIndex(5);
        engine.insert("Hello World");

        copinator.execute();

        assertTrue(undo.getList().size() > 0);  // Vérifie que l'état a été sauvegardé dans l'historique de l'undo
    }

    // Test 8: Vérification de la méthode getMemento qui doit renvoyer null
    @Test
    void testGetMemento() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        assertNull(copinator.getMemento());
    }

    // Test 9: Vérification de la méthode setMemento qui ne doit rien faire
    @Test
    void testSetMemento() {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undo = new UndoManagerinatorImpl(engine);
        Copinator copinator = new Copinator(engine, recorder, undo);

        assertDoesNotThrow(() -> copinator.setMemento(null));
    }
}

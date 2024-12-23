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
        pasteCommand = new Pastinator(engine, recorder, undoer);
        engine.setClipboardContent("pasted text");
    }

    @Test
    public void testExecute() {
        pasteCommand.execute();
        
        // Vérification si le texte a été collé dans le buffer
        assertTrue("The buffer should contain 'pasted text'", engine.getBufferContents().contains("pasted text"));
    }

    @Test
    public void testExecuteWithEmptyClipboard() {
        engine.setClipboardContent("");  // Set empty clipboard content
        pasteCommand.execute();
        
        // Buffer should still remain empty after pasting
        assertEquals("The buffer should not contain any text when the clipboard is empty", "", engine.getBufferContents());
    }

    @Test
    public void testRecorderInteraction() {
        pasteCommand.execute();
        recorder.getList().getFirst().containsKey(pasteCommand);
        
        // Verify that the recorder saved the paste command
        assertTrue("Recorder should have saved the paste command",recorder.getList().getFirst().containsKey(pasteCommand));
    }

    @Test
    public void testUndoManagerInteraction() {
        // Store the current state before executing paste
        undoer.store();
        
        // Execute paste command
        pasteCommand.execute();
        
        // Verify that the undo manager stored the state
        assertTrue("Undo manager should have stored the state", undoer.getList().size() > 0);
    }

    @Test
    public void testUndoManagerWhenClipboardIsEmpty() {
        engine.setClipboardContent("");  // Set empty clipboard content
        undoer.store();  // Save the current state
        pasteCommand.execute();
        
        // Check if the state is stored correctly, even with empty clipboard
        assertTrue("Undo manager should store the state even with empty clipboard", undoer.getList().size() > 0);
    }

    @Test
    public void testBufferContentsAfterPaste() {
        String originalContent = engine.getBufferContents();
        pasteCommand.execute();
        
        // Ensure pasted content is at the right position
        String expectedContent = originalContent + "pasted text";
        assertEquals("The buffer content should contain the original content followed by 'pasted text'", expectedContent, engine.getBufferContents());
    }
}

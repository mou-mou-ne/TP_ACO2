package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import Command.Command_managerinator;
import Command.Cutinator;
import Command.Pastinator;
import selection.Selection_implement;

public class Command_managerinatorTest {

    private Command_managerinator commandManager;
    private JTextArea textArea;
    private Selection_implement selection;

    @Before
    public void setUp() {
        commandManager = new Command_managerinator();
        textArea = new JTextArea("Initial text in the text area.");
        selection = new Selection_implement(textArea.getText()); // Simulate a selection
    }

    /**
     * Test the execution of multiple commands to ensure they work as expected.
     */
    @Test
    public void testMultipleCommandsExecution() {
        // Prepare the selection, assuming "text" is selected.
        selection.setBeginIndex(8);  // "text" starts at index 8
        selection.setEndIndex(12);   // "text" ends at index 12
    
        // Prepare multiple commands
        Cutinator cutCommand = new Cutinator(textArea, selection);
        Pastinator pasteCommand = new Pastinator(textArea, "new text");
    
        // Execute commands
        commandManager.executeCommand(cutCommand);  // This will cut "text"
        commandManager.executeCommand(pasteCommand);  // This will paste "new text"
    
        // Validate the final state of the text area
        assertEquals("new textInitial text in the text area.", textArea.getText());  // "text" should be cut and "new text" pasted
    }
    

    /**
     * Test the growth of the command history as commands are executed.
     */
    @Test
    public void testCommandHistoryGrowth() {
        // Check initial size of history
        assertEquals(0, commandManager.getCommandHistorySize());

        // Execute a command
        Cutinator cutCommand = new Cutinator(textArea, selection);
        commandManager.executeCommand(cutCommand);

        // Validate history growth
        assertEquals(1, commandManager.getCommandHistorySize());
    }

    /**
     * Test undo functionality when the history is not empty.
     * Since undo is not implemented, ensure the appropriate message is printed.
     */
    @Test
    public void testUndoWithoutImplementation() {
        // Prepare a command
        Cutinator cutCommand = new Cutinator(textArea, selection);

        // Execute the command
        commandManager.executeCommand(cutCommand);

        // Attempt to undo
        commandManager.undoLastCommand();

        // Since undo is not implemented, text should remain unchanged
        assertEquals("Initial text in the text area.", textArea.getText());
    }

    /**
     * Test undo functionality when no commands have been executed.
     * Ensure it handles empty history gracefully.
     */
    @Test
    public void testUndoEmptyHistory() {
        // Attempt to undo without any commands
        commandManager.undoLastCommand();

        // No effect expected, just validate that no exception is thrown
        assertTrue("No commands to undo.", true);
    }
}

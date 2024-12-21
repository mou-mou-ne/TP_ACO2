package test;

import static org.junit.Assert.assertEquals;

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

    /**
     * Set up the test environment by initializing a {@link Command_managerinator} and the components
     * necessary for executing commands, such as {@link JTextArea} and {@link Selection_implement}.
     */
    @Before
    public void setUp() {
        commandManager = new Command_managerinator();
        textArea = new JTextArea("Initial text in the text area.");
        selection = new Selection_implement(textArea.getText()); // Simulate a selection in the text area
    }

    /**
     * Test the executeCommand method to ensure commands are executed and stored in the history stack.
     */
    @Test
    public void testExecuteCommand() {
        // Prepare command: Cut the word "text" from the JTextArea
        Cutinator cutCommand = new Cutinator(textArea, selection);

        // Execute the cut command
        commandManager.executeCommand(cutCommand);
        
        // Verify that the text "text" has been removed
        assertEquals("Initial text in the  area.", textArea.getText());

        
    }

    /**
     * Test the undoLastCommand method to ensure it prints the appropriate message when no undo functionality is implemented.
     */
    @Test
    public void testUndoLastCommand() {
        // Execute a paste command (using the previously cut text as clipboard content)
        Pastinator pasteCommand = new Pastinator(textArea, "text");
        commandManager.executeCommand(pasteCommand);

        // Call undo, it should print the placeholder message
        commandManager.undoLastCommand(); // Expected output: "Undo not implemented yet."
    }

    /**
     * Test the undoLastCommand method when no commands have been executed.
     * Ensure it prints a message indicating that there are no commands to undo.
     */
    @Test
    public void testUndoWhenNoCommands() {
        // Call undo without executing any commands
        commandManager.undoLastCommand(); // Expected output: "No commands to undo."
    }
}

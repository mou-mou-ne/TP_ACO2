package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import Command.Cutinator;

import javax.swing.JTextArea;
import selection.Selection_implement;

public class CutinatorTest {

    private JTextArea textArea;
    private Selection_implement selection;
    private Cutinator cutinator;

    /**
     * Set up the test environment by initializing a {@link JTextArea}, {@link Selection_implement}, and the {@link Cutinator} object.
     */
    @Before
    public void setUp() {
        textArea = new JTextArea();
        selection = new Selection_implement(textArea.getText());
        cutinator = new Cutinator(textArea, selection);
    }

    /**
     * Test the execute method of the Cutinator class.
     * This test ensures that the selected text is removed from the JTextArea and saved to the clipboard.
     */
    @Test
    public void testExecute() {
        // Set initial content in the text area
        textArea.setText("This is some text to cut.");

        // Select a portion of the text
        textArea.setSelectionStart(10);
        textArea.setSelectionEnd(14);

        // Execute the cut command
        cutinator.execute();

        // Check if the selected text is removed from the text area
        assertEquals("This is some  to cut.", textArea.getText());

        // Check if the selected text was stored in the clipboard
        assertEquals("text", selection.getClipboard());
    }

    /**
     * Test the execute method when there is no valid selection.
     * This checks that no text is removed and an exception is handled properly.
     */
    @Test
    public void testExecuteWithNoSelection() {
        // Set initial content in the text area
        textArea.setText("This is some text.");

        // Set the caret position without selecting any text
        textArea.setCaretPosition(5);

        // Execute the cut command
        cutinator.execute();

        // Check that the text area content remains unchanged
        assertEquals("This is some text.", textArea.getText());

        // Check that the clipboard is empty since no text was selected
        assertEquals("", selection.getClipboard());
    }

    /**
     * Test the execute method with invalid selection.
     * This ensures that an IllegalArgumentException is thrown when the selection is invalid.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExecuteWithInvalidSelection() {
        // Set initial content in the text area
        textArea.setText("Some text to cut.");

        // Set an invalid selection (start is greater than end)
        textArea.setSelectionStart(10);
        textArea.setSelectionEnd(5);

        // Execute the cut command, which should throw an IllegalArgumentException
        cutinator.execute();
    }

    /**
     * Test the execute method with an empty selection.
     * This ensures that no action is performed when the selection is empty.
     */
    @Test
    public void testExecuteWithEmptySelection() {
        // Set initial content in the text area
        textArea.setText("Some text to cut.");

        // Set the selection to be the same start and end position (empty selection)
        textArea.setSelectionStart(5);
        textArea.setSelectionEnd(5);

        // Execute the cut command
        cutinator.execute();

        // Check that the text area content remains unchanged
        assertEquals("Some text to cut.", textArea.getText());

        // Check that the clipboard is still empty
        assertEquals("", selection.getClipboard());
    }
}

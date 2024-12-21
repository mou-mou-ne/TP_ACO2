package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import Command.Pastinator;

import javax.swing.JTextArea;

public class PastinatorTest {

    private JTextArea textArea;
    private String clipboardContent;
    private Pastinator pastinator;

    /**
     * Set up the test environment by initializing a {@link JTextArea} and the {@link Pastinator} object.
     */
    @Before
    public void setUp() {
        textArea = new JTextArea();
        clipboardContent = "Hello, World!";
        pastinator = new Pastinator(textArea, clipboardContent);
    }

    /**
     * Test the execute method of the Pastinator class.
     * This test ensures that the text is inserted at the current caret position.
     */
    @Test
    public void testExecute() {
        // Set the caret position at the beginning of the text area
        textArea.setCaretPosition(0);

        // Execute the paste command
        pastinator.execute();

        // Check if the text was correctly inserted at the caret position
        assertEquals("Hello, World!", textArea.getText());
    }

    /**
     * Test the execute method when the caret position is not at the start.
     * This checks that the text is inserted correctly at any caret position.
     */
    @Test
    public void testExecuteAtDifferentCaretPosition() {
        // Set initial content in the text area
        textArea.setText("This is a test. ");
        
        // Set the caret position after the initial text
        textArea.setCaretPosition(16);

        // Execute the paste command
        pastinator.execute();

        // Check if the text was correctly inserted at the new caret position
        assertEquals("This is a test. Hello, World!", textArea.getText());
    }

    /**
     * Test the execute method when the clipboard content is empty.
     * This ensures that no text is inserted if the clipboard content is empty.
     */
    @Test
    public void testExecuteWithEmptyClipboard() {
        // Set the clipboard content to an empty string
        clipboardContent = "";
        pastinator = new Pastinator(textArea, clipboardContent);

        // Set initial content in the text area
        textArea.setText("Existing content. ");

        // Set the caret position at the end of the text area
        textArea.setCaretPosition(18);

        // Execute the paste command
        pastinator.execute();

        // Check if the text was not changed
        assertEquals("Existing content. ", textArea.getText());
    }
}
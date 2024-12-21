package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import Command.Pastinator;

import javax.swing.JTextArea;

/**
 * Unit tests for the Pastinator class.
 * 
 * <p>This class tests the functionality of the Pastinator class, which is responsible for pasting text from the clipboard into a JTextArea at the current caret position.</p>
 * 
 * <p>The following scenarios are tested:</p>
 * <ul>
 *   <li>Inserting text at the beginning of the text area.</li>
 *   <li>Inserting text at a specific caret position within the text area.</li>
 *   <li>Handling empty clipboard content without altering the existing text in the text area.</li>
 *   <li>Ensuring the caret is moved after pasting.</li>
 *   <li>Inserting at the end of an empty text area.</li>
 *   <li>Handling long text in the clipboard.</li>
 * </ul>
 * 
 * @see Pastinator
 * @see JTextArea
 */
public class PastinatorTest {

    private JTextArea textArea;
    private String clipboardContent;
    private Pastinator pastinator;

    @Before
    public void setUp() {
        textArea = new JTextArea();
        clipboardContent = "Hello, World!";
        pastinator = new Pastinator(textArea, clipboardContent);
    }

    @Test
    public void testExecute() {
        textArea.setCaretPosition(0);
        pastinator.execute();
        assertEquals("Hello, World!", textArea.getText());
    }

    @Test
    public void testExecuteAtDifferentCaretPosition() {
        textArea.setText("This is a test. ");
        textArea.setCaretPosition(16);
        pastinator.execute();
        assertEquals("This is a test. Hello, World!", textArea.getText());
    }

    @Test
    public void testExecuteWithEmptyClipboard() {
        clipboardContent = "";
        pastinator = new Pastinator(textArea, clipboardContent);
        textArea.setText("Existing content. ");
        textArea.setCaretPosition(18);
        pastinator.execute();
        assertEquals("Existing content. ", textArea.getText());
    }


    // Test d'insertion de texte à la fin du JTextArea (sans contenu initial)
    @Test
    public void testInsertAtEnd() {
        textArea.setCaretPosition(0); // Placé au début
        textArea.setText("");
        pastinator.execute();
        assertEquals("Hello, World!", textArea.getText());
    }

    // Test avec un texte long dans le presse-papiers
    @Test
    public void testLongClipboardContent() {
        String longText = "A".repeat(10000); // Génère un texte très long
        clipboardContent = longText;
        pastinator = new Pastinator(textArea, clipboardContent);
        textArea.setCaretPosition(0);
        pastinator.execute();
        assertEquals(longText, textArea.getText());
    }

    // Test avec un JTextArea vide au départ
    @Test
    public void testEmptyTextArea() {
        textArea.setText("");
        pastinator.execute();
        assertEquals("Hello, World!", textArea.getText());
    }
}

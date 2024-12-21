package Command;

import javax.swing.JTextArea;

/**
 * The Pastinator class implements the Command_interface and defines the action of pasting text
 * into a {@link JTextArea} at the current caret position.
 * This command is typically used to insert clipboard content into a text area.

 */
public class Pastinator implements Command_interface {

    private JTextArea textArea;
    private String clipboardContent;

    /**
     * Constructs a new Pastinator with the specified {@link JTextArea} and clipboard content.
     * 
     * @param textArea the {@link JTextArea} in which the text will be pasted
     * @param clipboardContent the content to be pasted into the text area
     */
    public Pastinator(JTextArea textArea, String clipboardContent) {
        this.textArea = textArea;
        this.clipboardContent = clipboardContent;
    }

    /**
     * Executes the paste command by inserting the clipboard content at the current caret position
     * in the {@link JTextArea}.

     */
    @Override
    public void execute() {
        int caretPosition = textArea.getCaretPosition(); // Retrieve the caret (fancy word to speak about the blinking cursor quand vous allez écrire ) position
        textArea.insert(clipboardContent, caretPosition); // Insert the clipboard content at the caret position
        System.out.println("Text pasted: " + clipboardContent); // Print confirmation to the console
    }
}

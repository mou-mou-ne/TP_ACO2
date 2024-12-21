package Command;

import javax.swing.JTextArea;
import selection.Selection_implement;

/**
 * The Cutinator class implements the Command_interface and defines the action of cutting selected text
 * from a JTextArea and storing it in the clipboard.
 * This command is typically used to remove selected text from a text area and save it to the clipboard for potential pasting.
 */
public class Cutinator implements Command_interface {

    private JTextArea textArea;
    private Selection_implement selection;

    /**
     * Constructs a new Cutinator with the specified text area and selection object.
     * @param textArea the JTextArea from which the selected text will be cut
     * @param selection the Selection_implement object used to manage the clipboard
     */
    public Cutinator(JTextArea textArea, Selection_implement selection) {
        this.textArea = textArea;
        this.selection = selection;
    }

    /**
     * This method retrieves the selection start and end positions from the JTextArea,
     * validates the selection, removes the selected text, and then stores the removed text in the clipboard.
     * 
     * @throws IllegalArgumentException if the selection is invalid or out of bounds
     */
    @Override
    public void execute() {
        int start = textArea.getSelectionStart(); // Retrieve the start position of the selection
        int end = textArea.getSelectionEnd(); // Retrieve the end position of the selection

        // If the selection is empty, do nothing and return
        if (start == end) {
            return;  // No action for empty selection
        }

        // Check if the selection is valid
        if (start >= 0 && end <= textArea.getText().length() && start < end) {
            String selectedText = textArea.getText().substring(start, end); // Get the selected text
            textArea.replaceRange("", start, end); // Remove the selected text from the text area
            selection.setClipboard(selectedText); // Save the selected text to the clipboard
        } else {
            throw new IllegalArgumentException("Sélection invalide."); // Invalid selection exception
        }
    }
}

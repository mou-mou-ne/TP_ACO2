package Command;

import javax.swing.JTextArea;
import selection.Selection_implement;

/**
 * The Cutinator class implements the Command_interface and defines the action of cutting selected text
 * from a {@link JTextArea} and storing it in the clipboard.
 * This command is typically used to remove selected text from a text area and save it to the clipboard for potential pasting.
 * 

 */
public class Cutinator implements Command_interface {

    private JTextArea textArea;
    private Selection_implement selection;

    /**
     * Constructs a new Cutinator with the specified text area and selection object.
     * @param textArea the {@link JTextArea} from which the selected text will be cut
     * @param selection the {@link Selection_implement} object used to manage the clipboard
     */
    public Cutinator(JTextArea textArea, Selection_implement selection) {
        this.textArea = textArea;
        this.selection = selection;
    }

    /**
     * <p>This method retrieves the selection start and end positions from the {@link JTextArea},
     * validates the selection, removes the selected text, and then stores the removed text in the clipboard.</p>
     * 
     * @throws IllegalArgumentException if the selection is invalid or out of bounds
     */
    @Override
    public void execute() {
        try {
            int start = textArea.getSelectionStart(); // Retrieve the start position of the selection
            int end = textArea.getSelectionEnd(); // Retrieve the end position of the selection
            if (start >= 0 && end <= textArea.getText().length() && start < end) {
                String selectedText = textArea.getText().substring(start, end); // Get the selected text
                textArea.replaceRange("", start, end); // Remove the selected text from the text area
                selection.setClipboard(selectedText); // Save the selected text to the clipboard
            } else {
                throw new IllegalArgumentException("Sélection invalide."); // Invalid selection exception
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la commande couper : " + e.getMessage()); // Error handling
        }
    }
}

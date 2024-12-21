package Command;

import javax.swing.JTextArea;
import selection.Selection;
import engine.Engine_implementation;

/**
 * The Copinator class implements the Command_interface and defines the action of copying selected text
 * from a {@link JTextArea} to the clipboard and updating the selection indices.
 * This command is typically used to copy text from a text area and save it to the clipboard for future pasting.
 */
public class Copinator implements Command_interface {

    private Engine_implementation engine;
    private JTextArea textArea;
    private Selection selection;

    /**
     * Constructs a new Copinator with the specified engine, text area, and selection object.
     * 
     * @param engine the {@link Engine_implementation} used to manage clipboard content // 
     * @param textArea the {@link JTextArea} from which the selected text will be copied
     * @param selection the {@link Selection} object used to manage the selection indices
     */
    public Copinator(Engine_implementation engine, JTextArea textArea, Selection selection) {
        this.engine = engine;
        this.textArea = textArea;
        this.selection = selection;
    }

    /**
     * <p>This method checks whether there is a valid text selection , copies the selected text to the clipboard,
     * and updates the start and end indices of the selection in the {@link Selection} object.</p>
     * 
     * <p>If the selection is invalid (for exemple, no text is selected), the method does nothing.</p>
     */
    @Override
    public void execute() {
        // Check if the selection is valid 
        if (textArea.getSelectionStart() != textArea.getSelectionEnd()) {
            // Copy the selected text to the clipboard
            String selectedText = textArea.getSelectedText();
            engine.setClipboardContents(selectedText);

            // Update the selection indices in the Selection object
            selection.setBeginIndex(textArea.getSelectionStart());
            selection.setEndIndex(textArea.getSelectionEnd());
        }
    }
}

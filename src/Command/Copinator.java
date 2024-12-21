package Command;

import javax.swing.JTextArea;
import selection.Selection;
import engine.Engine_implementation;

/**
 * The Copinator class implements the Command_interface and defines the action of copying selected text
 * from a {@link JTextArea} to the clipboard and updating the selection indices.
 * This command is typically used to copy text from a text area and save it to the clipboard for future pasting.
 * 
 * <p>This class is part of the Command pattern, encapsulating the copy action as an object that can be executed
 * when needed. The copy operation involves selecting text, copying it to the clipboard, and updating the selection
 * range indices in the {@link Selection} object.</p>
 * 
 * <p>Usage:</p>
 * <pre>
 * JTextArea textArea = new JTextArea();
 * Selection selection = new Selection_implement(textArea.getText());
 * Engine_implementation engine = new Engine_implementation(textArea.getText());
 * Copinator copinator = new Copinator(engine, textArea, selection);
 * copinator.execute();
 * </pre>
 */
public class Copinator implements Command_interface {

    private Engine_implementation engine;
    private JTextArea textArea;
    private Selection selection;

    /**
     * Constructs a new Copinator with the specified {@link Engine_implementation}, {@link JTextArea},
     * and {@link Selection} objects.
     * 
     * @param engine the {@link Engine_implementation} used to manage clipboard content
     * @param textArea the {@link JTextArea} from which the selected text will be copied
     * @param selection the {@link Selection} object used to manage the selection indices
     */
    public Copinator(Engine_implementation engine, JTextArea textArea, Selection selection) {
        this.engine = engine;
        this.textArea = textArea;
        this.selection = selection;
    }

    /**
     * Executes the copy command by copying the selected text from the {@link JTextArea} to the clipboard,
     * and updating the selection indices.
     * 
     * <p>This method checks whether there is a valid text selection, copies the selected text to the clipboard,
     * and updates the start and end indices of the selection in the {@link Selection} object.</p>
     * 
     * <p>If the selection is invalid (i.e., no text is selected), the method does nothing.</p>
     */
    @Override
    public void execute() {
        // Check if the selection is valid (i.e., the start and end positions of the selection are different)
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

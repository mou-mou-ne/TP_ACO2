package selection;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Implementation of the Selection interface, 
 */
public class Selection_implement implements Selection {

    private int beginIndex; // Starting index of the selection.
    private int endIndex;   // Ending index of the selection.
    private int bufferBeginIndex; // Starting index of the buffer.
    private int bufferEndIndex;   // Ending index of the buffer.
    private final StringBuilder buffer; // Text buffer for managing content.
    private String clipboard; // Internal clipboard to store copied text.

    /**
     * Constructs a Selection_implement 
     *
     * @param initialContent the initial content (like the name indique)
     */
    public Selection_implement(String initialContent) {
        this.beginIndex = 0;
        this.endIndex = 0;
        this.bufferBeginIndex = 0;
        this.bufferEndIndex = 0;
        this.buffer = new StringBuilder(initialContent);
    }

    /**
     * Gets the starting index of the current selection.
     *
     * @return the beginning index of the selection
     */
    @Override
    public int getBeginIndex() {
        return this.beginIndex;
    }

    /**
     * Gets the ending index of the current selection.
     *
     * @return the ending index of the selection
     */
    @Override
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * Gets the starting index of the buffer.
     *
     * @return the beginning index of the buffer
     */
    @Override
    public int getBufferBeginIndex() {
        return this.bufferBeginIndex;
    }

    /**
     * Gets the ending index of the buffer.
     *
     * @return the ending index of the buffer
     */
    @Override
    public int getBufferEndIndex() {
        return this.bufferEndIndex;
    }

    /**
     * Sets the starting index of the selection.
     *
     * @param beginIndex the starting index of the selection
     * @throws IllegalArgumentException if the index is out of bounds ( so if the beginIndex is negative or bigger than the buffer length)
     */
    public void setBeginIndex(int beginIndex) {
        if (beginIndex < 0 || beginIndex > buffer.length()) {
            throw new IllegalArgumentException("Begin index out of bounds");
        }
        this.beginIndex = beginIndex;
    }

    /**
     * Sets the ending index of the selection.
     *
     * @param endIndex the ending index of the selection
     */
    public void setEndIndex(int endIndex) {
        if (endIndex > buffer.length()) {
            endIndex = buffer.length(); // Adjusts the index to fit buffer size
        }
        this.endIndex = endIndex;
    }

    /**
     * Gets the text currently selected in the buffer.
     *
     * @return the selected text
     */
    public String getSelectedText() {
        return buffer.substring(beginIndex, endIndex);
    }

    /**
     * Replaces the selected text in the buffer with the given replacement.
     *
     * @param replacement the text to replace the selection
     */
    public void replaceSelection(String replacement) {
        if (replacement != null) {
            buffer.replace(beginIndex, endIndex, replacement);
            this.endIndex = this.beginIndex + replacement.length();
        }
    }

    /**
     * Gets the entire content of the buffer.
     *
     * @return the buffer content as a string
     */
    public String getBufferContent() {
        return buffer.toString();
    }

    /**
     * Sets the given text to the clipboard and updates the system clipboard.
     *
     * @param text the text to copy to the clipboard
     */
    public void setClipboard(String text) {
        this.clipboard = text; // Updates the internal clipboard

        // Updates the system clipboard
        StringSelection selection = new StringSelection(text);
        Clipboard clipboardSystem = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboardSystem.setContents(selection, null);
    }

    /**
     * Gets the content currently stored in the internal clipboard.
     *
     * @return the clipboard content
     */
    public String getClipboard() {
        return clipboard;
    }
}

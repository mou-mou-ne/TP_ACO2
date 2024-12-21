package engine;

/**
 * Provides a text editing engine with functionalities to manipulate a buffer and clipboard.
 * This class allows copying, cutting, and pasting text, as well as setting and retrieving
 * buffer and clipboard contents.
 */
public class Engine_implementation {
    private StringBuilder buffer; // Stores the text content of the engine.
    private String clipboard;    // Stores the clipboard content.

    /**
     * Constructs an Engine_implementation with initial content in the buffer.
     *
     * @param initialContent the initial content of the buffer
     */
    public Engine_implementation(String initialContent) {
        this.buffer = new StringBuilder(initialContent);
        this.clipboard = "";
    }

    /**
     * Retrieves the current contents of the buffer.
     *
     * @return the contents of the buffer as a string
     */
    public String getBufferContents() {
        return buffer.toString();
    }

    /**
     * Retrieves the current contents of the clipboard.
     *
     * @return the contents of the clipboard as a string
     */
    public String getClipboardContents() {
        return clipboard;
    }

    /**
     * Sets the contents of the clipboard to the given string.
     *
     * @param clipboard the new content for the clipboard
     */
    public void setClipboardContents(String clipboard) {
        this.clipboard = clipboard; // Updates the clipboard content
    }

    /**
     * Copies the selected text from the buffer to the clipboard.
     *
     * @param start the starting index of the selection (inclusive)
     * @param end   the ending index of the selection (exclusive)
     * @throws StringIndexOutOfBoundsException if the indices are invalid
     */
    public void copySelectedText(int start, int end) {
        clipboard = buffer.substring(start, end); // Copies selected text to the clipboard
    }

    /**
     * Cuts the selected text from the buffer to the clipboard.
     * The selected text is removed from the buffer.
     *
     * @param start the starting index of the selection (inclusive)
     * @param end   the ending index of the selection (exclusive)
     * @throws StringIndexOutOfBoundsException if the indices are invalid
     */
    public void cutSelectedText(int start, int end) {
        clipboard = buffer.substring(start, end); // Copies selected text to the clipboard
        buffer.delete(start, end); // Removes the selected text from the buffer
    }

    /**
     * Pastes the current clipboard contents into the buffer at the specified position.
     *
     * @param position the index where the clipboard content should be inserted
     * @throws StringIndexOutOfBoundsException if the position is invalid
     */
    public void pasteClipboard(int position) {
        if (!clipboard.isEmpty()) {
            buffer.insert(position, clipboard); // Inserts clipboard content at the given position
        }
    }

    /**
     * Sets the contents of the buffer to the specified string.
     *
     * @param newContent the new content to set in the buffer
     */
    public void setBufferContents(String newContent) {
        if (newContent != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(newContent);
            this.buffer = sb;
        }
    }
}

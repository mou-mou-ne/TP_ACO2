package engine;

import mementomoris.EditorMemento;
import mementomoris.Memento;
import selection.Selection;
import selection.Selection_implement;

/**
 * A simple text editing engine that manages a buffer and clipboard.
 * Supports actions like copy, cut, paste, insert, delete, and undo/redo.
 */
public class Engine_implementation implements Engine_inter {
    private StringBuilder buffer;
    private String clipboard;
    private Selection selection;

    /**
     * Initializes the editor with an empty buffer and clipboard.
     */
    public Engine_implementation() {
        this.buffer = new StringBuilder("");
        this.clipboard = "";
        this.selection = new Selection_implement(buffer);
    }

    /**
     * Inserts text at the current selection or position.
     * If there's a selection, it replaces it with the new text.
     * 
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
        int begin = this.getSelection().getBeginIndex();
        int end = this.getSelection().getEndIndex();

        if (begin == end) {
            this.buffer.insert(begin, s);
            this.getSelection().setBeginIndex(begin + s.length());
            this.getSelection().setEndIndex(this.getSelection().getBeginIndex());
        } else {
            this.buffer.replace(begin, end, s);
            this.getSelection().setBeginIndex(begin + s.length());
            this.getSelection().setEndIndex(this.getSelection().getBeginIndex());
        }
    }

    /**
     * Copies the selected text to the clipboard.
     */
    @Override
    public void copySelectedText() {
        this.clipboard = (String) buffer.subSequence(this.getSelection().getBeginIndex(), this.getSelection().getEndIndex());
    }

    /**
     * Pastes the clipboard content at the current selection.
     */
    @Override
    public void pasteClipboard() {
        int beginIndex = this.getSelection().getBeginIndex();
        if (beginIndex >= 0 && beginIndex <= buffer.length()) {
            insert(clipboard);
        } else {
            System.out.println("Invalid start index!");
        }
    }

    /**
     * Cuts the selected text and copies it to the clipboard.
     */
    @Override
    public void cutSelectedText() {
        this.copySelectedText();
        this.delete();
    }

    /**
     * Deletes the selected text from the buffer.
     */
    @Override
    public void delete() {
        this.buffer.delete(this.getSelection().getBeginIndex(), this.getSelection().getEndIndex());
        this.getSelection().setEndIndex(this.getSelection().getBeginIndex());
    }

    /**
     * Returns the current buffer contents as a string.
     * 
     * @return the buffer content
     */
    @Override
    public String getBufferContents() {
        return this.buffer.toString();
    }

    /**
     * Returns the current clipboard contents.
     * 
     * @return the clipboard content
     */
    @Override
    public String getClipboardContents() {
        return this.clipboard;
    }

    /**
     * Returns the current selection object.
     * 
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        return this.selection;
    }

    /**
     * Sets the buffer content to a new value.
     * 
     * @param s the new buffer content
     */
    @Override
    public void setBufferContent(StringBuilder s) {
        this.buffer = s;
    }

    /**
     * Sets the clipboard content to a new value.
     * 
     * @param s the new clipboard content
     */
    @Override
    public void setClipboardContent(String s) {
        this.clipboard = s;
    }

    /**
     * Returns a Memento representing the current state of the engine.
     * 
     * @return a Memento object with the current state
     */
    @Override
    public Memento getMemento() {
        return new EditorMemento(new StringBuilder(this.buffer), this.selection.getBeginIndex(), this.selection.getEndIndex(), this.clipboard);
    }

    /**
     * Restores the engine state from a given Memento.
     * 
     * @param m the Memento to restore the state from
     */
    @Override
    public void setMemento(Memento m) {
        EditorMemento memento = (EditorMemento) m;
        this.buffer = memento.getBufferContent();
        this.clipboard = memento.getClipboard();
        this.selection.setBeginIndex(memento.getBeginIndex());
        this.selection.setEndIndex(memento.getEndIndex());
    }
}

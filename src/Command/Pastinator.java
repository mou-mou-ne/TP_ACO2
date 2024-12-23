package Command;

import javax.swing.JTextArea;

import Recorder.recorderImpl;
import engine.Engine_implementation;
import mementomoris.Memento;
import undoManagerinator.UndoManagerinatorImpl;


/**
 * The Pastinator class implements the Command_interface and defines the action of pasting text
 * into a {@link JTextArea} at the current caret position.
 * This command is typically used to insert clipboard content into a text area.
 */
public class Pastinator implements CommandOriginator {
    private Engine_implementation engine;
    private recorderImpl recorder;
    private UndoManagerinatorImpl undo;


    /**
     * Constructs a new Pastinator with the specified {@link JTextArea} and clipboard content.
     * 
     * @param textArea the {@link JTextArea} in which the text will be pasted
     * @param clipboardContent the content to be pasted into the text area
     */
    public Pastinator(Engine_implementation engine,recorderImpl recorder,UndoManagerinatorImpl undo ) {
     this.engine = engine;
     this.recorder = recorder;
     this.undo = undo;

    }

    /**
     * Executes the paste command by inserting the clipboard content at the current caret position
     * in the {@link JTextArea}.
     */
    @Override
    public void execute() {
        this.undo.store();

       this.engine.pasteClipboard();

       if (this.recorder.getStarted()) {
     	   
     	   recorder.save(this);
        }
    }

	@Override
	public Memento getMemento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMemento(Memento m) {
		// TODO Auto-generated method stub
		
	}

    // Méthode pour annuler l'action (Undo)
    
}

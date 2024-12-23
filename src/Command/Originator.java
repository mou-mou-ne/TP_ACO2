package Command;

import mementomoris.Memento;

/**
 * The Originator interface defines the methods required for creating and restoring
 * Memento objects. Classes implementing this interface can save their state to a 
 * Memento and restore their state from a Memento.
 */
public interface Originator {
	
	/**
	 * Creates a new Memento object that holds the current state of the Originator.
	 * This method is used to save the state of the Originator so that it can be restored later.
	 *
	 * @return a Memento object containing the current state of the Originator.
	 */
	public Memento getMemento();
	
	/**
	 * Restores the state of the Originator from the given Memento object.
	 *
	 * @param m the Memento object containing the state to be restored
	 */
	public void setMemento(Memento m);
}

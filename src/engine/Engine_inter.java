package engine;

import mementomoris.Memento;
import selection.Selection;

/**
 * Main API for the text editing engine
 * @author plouzeau (et un peu Salma)
 * @version 1.0
 */
public interface Engine_inter {
	
	    /**
	     * Provides access to the selection control object
	     * @return the selection object
	     */
	    Selection getSelection();

	    /**
	     * Provides the whole contents of the buffer, as a string
	     * @return a copy of the buffer's contents
	     */
	    String getBufferContents();

	    /**
	     * Provides the clipboard contents
	     * @return a copy of the clipboard's contents
	     */
	    String getClipboardContents();

	    /**
	     * Removes the text within the interval
	     * specified by the selection control object,
	     * from the buffer.
	     */
	    void cutSelectedText();

	    /**
	     * Copies the text within the interval
	     * specified by the selection control object
	     * into the clipboard.
	     */
	    void copySelectedText();

	    /**
	     * Replaces the text within the interval specified by the selection object with
	     * the contents of the clipboard.
	     */
	    void pasteClipboard();

	    /**
	     * Inserts a string in the buffer, which replaces the contents of the selection
	     * @param s the text to insert
	     */
	    void insert(String s);

	    /**
	     * Removes the contents of the selection in the buffer
	     */
	    void delete();
	    
	    /**
	     * Définit le contenu du tampon avec une nouvelle valeur.
	     * Cette méthode permet de remplacer le texte actuel du tampon par un texte donné.
	     * 
	     * @param s le nouveau contenu du tampon sous forme de StringBuilder
	     */
	    void setBufferContent(StringBuilder s);

	    /**
	     * Définit le contenu du presse-papiers avec un nouveau texte.
	     * Cette méthode remplace le contenu actuel du presse-papiers.
	     * 
	     * @param s le texte à mettre dans le presse-papiers
	     */
	    void setClipboardContent(String s);

	    /**
	     * Fournit un Memento représentant l'état actuel de l'éditeur.
	     * Cela permet de sauvegarder l'état actuel du moteur (par exemple, le contenu du tampon, 
	     * la sélection, le presse-papiers) pour pouvoir revenir à cet état plus tard (par exemple, lors d'une opération d'annulation).
	     * 
	     * @return le Memento représentant l'état actuel
	     */
	    Memento getMemento();

	    /**
	     * Définit l'état actuel du moteur d'édition à partir d'un Memento.
	     * Cela permet de restaurer un état précédemment sauvegardé, par exemple, lors d'une opération de réinitialisation ou d'annulation.
	     * 
	     * @param m le Memento à restaurer
	     */
	    void setMemento(Memento m);
	
	}

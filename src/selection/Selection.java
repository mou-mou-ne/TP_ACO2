package selection;

/**
 * Provides access to selection control operations
 *
 * @author doofenshmirtz
 * @version 1.0
 */
public interface Selection {

    /**
     * Provides the index of the first character designated
     * by the selection.
     *
     * @return the begin index
     */
    int getBeginIndex();

    /**
     * Provides the index of the first character
     * after the last character designated
     * by the selection.
     *
     * @return the end index
     */
    int getEndIndex();

    /**
     * Provides the index of the first character in the buffer
     *
     * @return the buffer's begin index
     */
    int getBufferBeginIndex();

    /**
     * Provides the index of the first "virtual" character
     * after the end of the buffer
     *
     * @return the post end buffer index
     */
    int getBufferEndIndex();

    /**
     * Changes the value of the begin index of the selection
     *
     * @param beginIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    void setBeginIndex(int beginIndex);

    /**
     * Changes the value of the end index of the selection
     *
     * @param endIndex, must be within the buffer index range
     * @throws IndexOutOfBoundsException if the beginIndex is out of bounds
     */
    void setEndIndex(int endIndex);

    
    // Tout ce qu'est en dessous je l'ai ajouter parce que Eclipse me le demandait quand je codait ma clasee Engine_implmentation
	
    /**
     *  
     * @return  the text in the buffer
     */
    String getSelectedText();
    
    /**
     * 
     * @param clipboard
     * remplace la selection par ce qui est copier dans le clipboard
     */
	void replaceSelection(String clipboard);


}
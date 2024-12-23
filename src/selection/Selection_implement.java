package selection;

/**
 * Implementation of the Selection interface, 
 */
public class Selection_implement implements Selection {

    private int beginIndex; 
    private int endIndex;  
    private int bufferBeginIndex;
    private int bufferEndIndex;   
    private final StringBuilder buffer; 

    /**
     * Constructs a Selection_implement 
     *
     * @param initialContent the initial content (like the name indique)
     */
    public Selection_implement(StringBuilder buffer) {
        this.beginIndex = 0;
        this.endIndex = 0;
        this.bufferBeginIndex = 0;
        this.bufferEndIndex = 0;
        this.buffer = buffer;
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
            System.out.println("taille" + buffer.length());
            System.out.println("max" + beginIndex);
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
    	if (endIndex < 0 ) {
            throw new IllegalArgumentException("Begin index out of bounds");
        }
        this.endIndex = endIndex;
    }

    
}

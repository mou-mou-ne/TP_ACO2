package mementomoris;

/**
 * La classe editorMemento implémente l'interface {@link Memento}.
 * Elle est utilisée pour capturer l'état complet de l'éditeur, 
 * y compris le contenu du tampon, les indices de la sélection et le contenu du presse-papiers.
 * Cette classe permet de sauvegarder et restaurer l'état de l'éditeur dans un mécanisme d'undo/redo.
 */
public class EditorMemento implements Memento {
    
    private StringBuilder bufferContent;
    private int beginIndex;
    private int endIndex;
    private String clipboard;

    /**
     * Constructeur pour créer un memento représentant l'état complet de l'éditeur.
     * 
     * @param bufferContent le contenu du tampon de l'éditeur
     * @param beginIndex l'indice de début de la sélection
     * @param endIndex l'indice de fin de la sélection
     * @param clipboard le contenu actuel du presse-papiers
     */
    public EditorMemento(StringBuilder bufferContent, int beginIndex, int endIndex, String clipboard) {
        this.bufferContent = bufferContent;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.clipboard = clipboard;
    }

    /**
     * Retourne le contenu du tampon capturé dans ce memento.
     * 
     * @return le contenu du tampon
     */
    public StringBuilder getBufferContent() {
        return this.bufferContent;
    }

    /**
     * Retourne le contenu du presse-papiers capturé dans ce memento.
     * 
     * @return le contenu du presse-papiers
     */
    public String getClipboard() {
        return this.clipboard;
    }

    /**
     * Retourne l'indice de début de la sélection capturé dans ce memento.
     * 
     * @return l'indice de début de la sélection
     */
    public int getBeginIndex() {
        return this.beginIndex;
    }

    /**
     * Retourne l'indice de fin de la sélection capturé dans ce memento.
     * 
     * @return l'indice de fin de la sélection
     */
    public int getEndIndex() {
        return this.endIndex;
    }
}

package mementomoris;

/**
 * La classe selecto implémente l'interface {@link Memento}.
 * Elle est utilisée pour capturer l'état d'une opération de sélection, 
 * stockant les indices de début et de fin de la sélection à un moment donné.
 * Cette classe est utilisée pour permettre la fonctionnalité d'undo/redo 
 * dans des systèmes de traitement de texte ou de commandes similaires.
 */
public class SelectMemento implements Memento {
    
    private int begin;
    private int end;

    /**
     * Constructeur pour créer un memento de sélection avec les indices de début et de fin.
     * 
     * @param begin l'indice de début de la sélection
     * @param end l'indice de fin de la sélection
     */
    public SelectMemento(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * Retourne l'indice de début de la sélection capturée dans ce memento.
     * 
     * @return l'indice de début de la sélection
     */
    public int getBegin() {
        return this.begin;
    }

    /**
     * Retourne l'indice de fin de la sélection capturée dans ce memento.
     * 
     * @return l'indice de fin de la sélection
     */
    public int getEnd() {
        return this.end;
    }
}

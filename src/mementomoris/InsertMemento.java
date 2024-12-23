package mementomoris;

/**
 * La classe  implémente l'interface {@link Memento}.
 * Elle est utilisée pour capturer l'état d'une opération d'insertion de texte, 
 * stockant le texte à insérer à un moment donné.
 * Cette classe est utilisée pour permettre la fonctionnalité d'undo/redo 
 * dans des systèmes de traitement de texte ou de commandes similaires.
 */
public class InsertMemento implements Memento {

    private String textToInsert;

    /**
     * Constructeur pour créer un memento d'insertion de texte.
     * 
     * @param textToInsert le texte à insérer que ce memento doit capturer
     */
    public InsertMemento(String textToInsert) {
        this.textToInsert = textToInsert;
    }

    /**
     * Retourne le texte à insérer qui a été sauvegardé dans ce memento.
     * 
     * @return le texte à insérer capturé par ce memento
     */
    public String getTextToInsert() {
        return this.textToInsert;
    }
}

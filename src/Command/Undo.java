package Command;

import undoManagerinator.UndoManagerinatorImpl;

/**
 * Commande permettant de déclencher une action d'annulation (Undo) 
 * via un gestionnaire d'annulations.
 */
public class Undo implements Command_interface {

    private UndoManagerinatorImpl undo;

    /**
     * Constructeur de la commande Undo.
     *
     * @param undo le gestionnaire des annulations
     */
    public Undo(UndoManagerinatorImpl undo) {
        this.undo = undo;
    }

    /**
     * Exécute la commande d'annulation en utilisant le gestionnaire spécifié.
     */
    @Override
    public void execute() {
        this.undo.undo();
    }
}

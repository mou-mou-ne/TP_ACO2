package Command;

import undoManagerinator.UndoManagerinatorImpl;

/**
 * Commande permettant de déclencher une action de rétablissement (Redo) 
 * via un gestionnaire d'annulations.
 */
public class Redo implements Command_interface {

    private UndoManagerinatorImpl undo;

    /**
     * Constructeur de la commande Redo.
     *
     * @param undo le gestionnaire des annulations pour effectuer le rétablissement
     */
    public Redo(UndoManagerinatorImpl undo) {
        this.undo = undo;
    }

    /**
     * Exécute la commande de rétablissement en utilisant le gestionnaire spécifié.
     */
    @Override
    public void execute() {
        this.undo.redo();
    }
}

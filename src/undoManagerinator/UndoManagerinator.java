package undoManagerinator;

/**
 * L'interface undoManagerinator définit les opérations nécessaires pour gérer l'annulation (undo) 
 * et la réinitialisation (redo) des actions dans un gestionnaire d'undo/redo.
 * Elle permet de stocker l'état actuel des actions et d'inverser ou répéter ces actions selon les besoins.
 */
public interface UndoManagerinator {

    /**
     * Sauvegarde l'état actuel pour permettre une future opération d'annulation ou de réinitialisation.
     */
    void store();

    /**
     * Annule la dernière action effectuée, si possible.
     */
    void undo();

    /**
     * Réeffectue la dernière action annulée, si possible.
     */
    void redo();
}

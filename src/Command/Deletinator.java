package Command;

import Recorder.recorderImpl;
import engine.Engine_implementation;
import mementomoris.Memento;
import undoManagerinator.UndoManagerinatorImpl;

/**
 * La classe Deletinator implémente l'interface Command_interface et définit l'action de suppression
 * du texte sélectionné et de stockage dans le presse-papiers.
 * Cette commande est généralement utilisée pour supprimer le texte sélectionné d'un éditeur de texte.
 */
public class Deletinator implements CommandOriginator {

    private Engine_implementation engine;
    private recorderImpl recorder;
    private UndoManagerinatorImpl undo;

    /**
     * Constructeur de la commande Deletinator.
     *
     * @param engine   l'implémentation du moteur à partir duquel le texte sera supprimé
     * @param recorder l'enregistreur utilisé pour enregistrer les actions
     * @param undo     l'instance de UndoManagerinatorImpl pour gérer les opérations d'annulation
     */
    public Deletinator(Engine_implementation engine, recorderImpl recorder, UndoManagerinatorImpl undo) {
        this.engine = engine;
        this.recorder = recorder;
        this.undo = undo;
    }

    /**
     * Exécute la commande de suppression en enlevant le texte sélectionné.
     * 
     * <p>Avant de procéder à la suppression, la méthode vérifie la validité de la sélection (si elle est dans les limites et non vide).</p>
     *
     * @throws IllegalArgumentException si la sélection est invalide (par exemple, hors des limites)
     */
    @Override
    public void execute() {
        // Vérification de la validité de la sélection
        int beginIndex = engine.getSelection().getBeginIndex();
        int endIndex = engine.getSelection().getEndIndex();

        if (beginIndex < 0 || endIndex < 0 || beginIndex > endIndex || endIndex > engine.getBufferContents().length()) {
            throw new IllegalArgumentException("Invalid selection: out of bounds or empty selection.");
        }

        this.undo.store(); // Enregistrer l'état actuel avant d'exécuter la commande
        this.engine.delete(); // Effectuer l'opération de suppression

        // Si l'enregistreur est démarré, sauvegarder l'action
        if (this.recorder.getStarted()) {
            recorder.save(this);
        }
    }

    /**
     * Retourne un memento représentant l'état actuel.
     * 
     * @return toujours null, car un memento n'est pas nécessaire dans ce cas
     */
    @Override
    public Memento getMemento() {
        return null;
    }

    /**
     * Restaure l'état de la commande à partir d'un memento.
     * 
     * <p>Non implémenté dans cette classe.</p>
     *
     * @param m le memento à restaurer
     */
    @Override
    public void setMemento(Memento m) {
        // Non implémenté
    }
}

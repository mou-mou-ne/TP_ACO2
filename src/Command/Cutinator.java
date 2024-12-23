package Command;

import Recorder.recorderImpl;
import engine.Engine_implementation;
import mementomoris.Memento;
import undoManagerinator.UndoManagerinatorImpl;

/**
 * La classe Cutinator implémente l'interface Command_interface et définit l'action de couper
 * du texte sélectionné et de le stocker dans le presse-papiers.
 * Cette commande est généralement utilisée pour supprimer le texte sélectionné et le sauvegarder
 * dans le presse-papiers pour un collage ultérieur.
 */
public class Cutinator implements CommandOriginator {

    private Engine_implementation engine;
    private recorderImpl recorder;
    private UndoManagerinatorImpl undo;

    /**
     * Constructeur de la commande Cutinator.
     *
     * @param engine   l'implémentation du moteur pour effectuer la coupe
     * @param recorder l'enregistreur pour sauvegarder la commande
     * @param undo     le gestionnaire des annulations
     */
    public Cutinator(Engine_implementation engine, recorderImpl recorder, UndoManagerinatorImpl undo) {
        this.engine = engine;
        this.recorder = recorder;
        this.undo = undo;
    }

    /**
     * Cette méthode récupère les positions de début et de fin de la sélection, valide la sélection,
     * supprime le texte sélectionné, puis stocke le texte supprimé dans le presse-papiers.
     * 
     * @throws IllegalArgumentException si la sélection est invalide ou hors des limites du texte
     */
    @Override
    public void execute() {
        if (engine.getSelection().getBeginIndex() < 0 || engine.getSelection().getEndIndex() > engine.getBufferContents().length() || engine.getSelection().getBeginIndex() > engine.getSelection().getEndIndex()) {
            throw new IllegalArgumentException("Invalid selection range");
        }
        this.undo.store();

        this.engine.cutSelectedText();

        if (this.recorder.getStarted()) {
            recorder.save(this);
        }
    }

    /**
     * Retourne un memento représentant l'état actuel.
     * 
     * @return toujours null, car cette commande ne nécessite pas de memento
     */
    @Override
    public Memento getMemento() {
        return null;
    }

    /**
     * Restaure l'état de la commande à partir d'un memento.
     * 
     * <p>Non implémenté pour cette commande.</p>
     *
     * @param m le memento à restaurer
     */
    @Override
    public void setMemento(Memento m) {
        // Non implémenté
    }
}

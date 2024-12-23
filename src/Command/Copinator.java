package Command;

import Recorder.recorder;
import engine.Engine_implementation;
import mementomoris.Memento;
import undoManagerinator.UndoManagerinatorImpl;

/**
 * Commande permettant de copier du texte sélectionné à l'aide du moteur.
 * Cette commande prend en charge la gestion de l'annulation (Undo) 
 * et peut être enregistrée pour une utilisation ultérieure.
 */
public class Copinator implements CommandOriginator {

    private Engine_implementation engine;
    private recorder recorder;
    private UndoManagerinatorImpl undo;

    /**
     * Constructeur de la commande Copinator.
     *
     * @param engine   l'implémentation du moteur pour effectuer la copie
     * @param recorder l'enregistreur pour sauvegarder la commande
     * @param undo     le gestionnaire des annulations
     * @throws IllegalArgumentException si l'un des arguments est null
     */
    public Copinator(Engine_implementation engine, recorder recorder, UndoManagerinatorImpl undo) {
        if (engine == null || recorder == null || undo == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.engine = engine;
        this.recorder = recorder;
        this.undo = undo;
    }

    /**
     * Exécute la commande de copie.
     * 
     * <p>Cette méthode effectue les actions suivantes :
     * <ul>
     *   <li>Sauvegarde l'état actuel dans le gestionnaire d'annulations.</li>
     *   <li>Copie le texte sélectionné dans le moteur.</li>
     *   <li>Enregistre la commande si l'enregistreur est actif.</li>
     * </ul>
     * </p>
     */
    @Override
    public void execute() {
        this.undo.store();
        this.engine.copySelectedText();

        if (this.recorder.getStarted()) {
            recorder.save(this);
        }
    }

    /**
     * Retourne un memento représentant l'état actuel.
     * 
     * @return toujours null, car cette commande ne nécessite pas de memento.
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

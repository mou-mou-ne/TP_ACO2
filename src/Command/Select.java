package Command;

import Invoker.Invoker;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import mementomoris.Memento;
import mementomoris.SelectMemento;
import undoManagerinator.UndoManagerinatorImpl;

/**
 * La classe Select implémente l'interface CommandOriginator et définit l'action de sélectionner 
 * un texte dans un éditeur en utilisant des indices de début et de fin.
 * Cette commande permet de définir une plage de texte sélectionnée dans un éditeur.
 */
public class Select implements CommandOriginator {

    private Engine_implementation engine;
    private Invoker invoker;
    private recorderImpl recorder;
    private UndoManagerinatorImpl undo;

    /**
     * Constructeur de la commande Select.
     *
     * @param engine   l'implémentation du moteur qui gère l'éditeur et la sélection
     * @param invoker  l'instance qui fournit les indices de début et de fin de la sélection
     * @param recorder l'enregistreur pour sauvegarder les actions
     * @param undo     le gestionnaire des annulations pour stocker l'état avant l'exécution de la commande
     */
    public Select(Engine_implementation engine, Invoker invoker, recorderImpl recorder, UndoManagerinatorImpl undo) {
        this.engine = engine;
        this.invoker = invoker;
        this.recorder = recorder;
        this.undo = undo;
    }

    /**
     * Exécute la commande de sélection en définissant les indices de début et de fin dans l'éditeur.
     * Elle enregistre l'état actuel pour permettre une annulation et sauvegarde l'action si nécessaire.
     */
    @Override
    public void execute() {
        this.undo.store(); // Sauvegarde de l'état actuel avant d'effectuer la sélection

        // Mise à jour des indices de sélection
        this.engine.getSelection().setBeginIndex(this.invoker.getBegin());
        this.engine.getSelection().setEndIndex(this.invoker.getEnd());

        // Si l'enregistreur est activé, sauvegarde de la commande
        if (this.recorder.getStarted()) {
            recorder.save(this);
        }
    }

    /**
     * Retourne un memento représentant l'état de la sélection actuelle.
     * 
     * @return un memento représentant la sélection (les indices de début et de fin)
     */
    @Override
    public Memento getMemento() {
        return new SelectMemento(invoker.getBegin(), invoker.getEnd());
    }

    /**
     * Restaure l'état de la commande à partir d'un memento.
     * 
     * @param m le memento qui contient l'état à restaurer
     */
    @Override
    public void setMemento(Memento m) {
        SelectMemento s = (SelectMemento) m;
        this.invoker.setBeg(s.getBegin());
        this.invoker.setEnd(s.getEnd());
    }
}

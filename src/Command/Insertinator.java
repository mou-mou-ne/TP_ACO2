package Command;


import Invoker.Invoker;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import mementomoris.InsertMemento;
import mementomoris.Memento;
import undoManagerinator.UndoManagerinatorImpl;

/**
 * Commande permettant d'insérer du texte dans un moteur, avec gestion des fonctionnalités 
 * d'enregistrement, d'annulation (Undo), et de memento pour l'historique.
 * Le nom est a lire avec la voix de doofenshmirtz
 */
public class Insertinator implements CommandOriginator {

    private Engine_implementation engine;

    private Invoker invoker;

    private recorderImpl recorder;

    private UndoManagerinatorImpl undo;

    /**
     * Constructeur de la commande Insertinator.
     *
     * @param engine l'implémentation du moteur pour effectuer l'insertion
     * @param invoker l'Invoker contenant les données à insérer
     * @param recorder l'enregistreur pour sauvegarder les commandes
     * @param undo le gestionnaire pour les annulations
     */
    public Insertinator(Engine_implementation engine, Invoker invoker, recorderImpl recorder, UndoManagerinatorImpl undo) {
        this.engine = engine;
        this.invoker = invoker;
        this.recorder = recorder;
        this.undo = undo;
    }

    /**
     * Exécute la commande pour insérer du texte, sauvegarde l'état pour Undo, 
     * et enregistre la commande si l'enregistreur est actif.
     */
    @Override
    public void execute() {
        this.engine.insert(this.invoker.getTextToInsert());
        this.undo.store();

        if (this.recorder.getStarted()) {
            recorder.save(this);
        }
    }

    /**
     * Crée un memento contenant l'état actuel de l'insertion.
     *
     * @return un memento représentant l'état actuel
     */
    @Override
    public Memento getMemento() {
        return new InsertMemento(this.invoker.getTextToInsert());
    }

    /**
     * Restaure l'état à partir d'un memento donné.
     *
     * @param m le memento contenant l'état à restaurer
     */
    @Override
    public void setMemento(Memento m) {
        InsertMemento i = (InsertMemento) m;
        this.invoker.setTextToInsert(i.getTextToInsert());
    }

}

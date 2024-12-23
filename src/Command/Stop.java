package Command;

import Recorder.recorderImpl;

/**
 * Implémentation de la commande Stop pour arrêter une action en cours
 * dans un enregistreur.
 */
public class Stop implements Command_interface {

    private recorderImpl recorder;

    /**
     * Constructeur de la commande Stop.
     * 
     * @param recorder l'implémentation de l'enregistreur à arrêter
     */
    public Stop(recorderImpl recorder) {
        this.recorder = recorder;
    }

    /**
     * Exécute la commande pour arrêter l'enregistreur.
     */
    @Override
    public void execute() {
        this.recorder.stop();
    }

}

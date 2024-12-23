package Command;

import Recorder.recorderImpl;

/**
 * Implémentation de la commande Replay pour rejouer une action
 * enregistrée dans un enregistreur.
 */
public class Replay implements Command_interface {

  
    private recorderImpl recorder;

    /**
     * Constructeur de la commande Replay.
     * 
     * @param recorder l'implémentation de l'enregistreur à utiliser pour rejouer
     */
    public Replay(recorderImpl recorder) {
        this.recorder = recorder;
    }

    /**
     * Exécute la commande pour rejouer les actions enregistrées.
     */
    @Override
    public void execute() {
        this.recorder.replay();
    }

}

package Recorder;

import Command.CommandOriginator;
import mementomoris.Memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * L'implémentation de l'interface {@link recorder} qui gère l'enregistrement des commandes.
 * Cette classe permet de démarrer et d'arrêter l'enregistrement des commandes, de les rejouer, et de les sauvegarder sous forme de mementos.
 */
public class recorderImpl implements recorder {

    private List<Map<CommandOriginator, Memento>> l;
    private boolean started;
    private boolean replaying;

    /**
     * Constructeur par défaut de {@link recorderImpl}.
     * Initialise l'enregistrement à l'état "démarré" et la liste de commandes vide.
     */
    public recorderImpl() {
        this.replaying = false;
        this.started = true;
        this.l = new ArrayList<Map<CommandOriginator, Memento>>();
    }

    /**
     * Retourne l'état actuel de l'enregistrement (si l'enregistrement est en cours).
     * 
     * @return true si l'enregistrement est démarré, sinon false
     */
    @Override
    public boolean getStarted() {
        return this.started;
    }

    /**
     * Retourne l'état actuel de la lecture des commandes enregistrées.
     * 
     * @return true si l'enregistrement est en train de rejouer des commandes, sinon false
     */
    @Override
    public boolean getReplaying() {
        return this.replaying;
    }

    /**
     * Démarre l'enregistrement des commandes.
     */
    @Override
    public void start() {
        this.started = true;
    }

    /**
     * Arrête l'enregistrement des commandes.
     */
    @Override
    public void stop() {
        this.started = false;
    }

    /**
     * Rejoue toutes les commandes enregistrées.
     * L'ordre d'exécution est le même que l'ordre dans lequel les commandes ont été enregistrées.
     */
    @Override
    public void replay() {
        this.replaying = true;
        this.started = false;

        l.forEach(comMemMap -> comMemMap.forEach((commandOriginator, memento) -> {
            commandOriginator.setMemento(memento);
            commandOriginator.execute();
        }));

        this.replaying = false;
    }

    /**
     * Sauvegarde une commande dans l'enregistreur, sous forme de memento, si l'enregistrement est actif.
     * 
     * @param c la commande à sauvegarder
     */
    @Override
    public void save(CommandOriginator c) {
    	if (!this.started) {
            throw new IllegalStateException("L'enregistrement doit être démarré avant de pouvoir sauvegarder une commande.");
        }
            Map<CommandOriginator, Memento> com = new HashMap<>();
            com.put(c, c.getMemento());
            l.add(com);
        
    }
    
    /**
     *  Cette fonction est juste la pour m'aider a tester
     * @return l la list des commande
     */
    public List<Map<CommandOriginator, Memento>> getList() {
    	return this.l;
    	
    }
}

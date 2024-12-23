package Recorder;

import Command.CommandOriginator;

/**
 * L'interface recorder définit les actions de base pour un enregistreur de commandes.
 * Elle permet de démarrer, d'arrêter l'enregistrement, de rejouer les commandes enregistrées,
 * et de sauvegarder des commandes spécifiques pour une exécution ultérieure.
 */
public interface recorder {

    /**
     * Démarre l'enregistrement des commandes.
     */
    void start();

    /**
     * Arrête l'enregistrement des commandes.
     */
    void stop();

    /**
     * Rejoue les commandes enregistrées.
     */
    void replay();

    /**
     * Sauvegarde une commande dans l'enregistreur.
     *
     * @param c la commande à sauvegarder
     */
    void save(CommandOriginator c);

    /**
     * Vérifie si l'enregistrement est en cours.
     *
     * @return true si l'enregistrement est actif, false sinon
     */
    boolean getStarted();

    /**
     * Vérifie si l'enregistrement est en train de rejouer des commandes.
     *
     * @return true si les commandes sont en cours de replay, false sinon
     */
    boolean getReplaying();
}

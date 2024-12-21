/**
 * Package Main
 * @author Salma BOUCHRA 
 */
package main;

import Command.Command_managerinator;
import engine.Engine_implementation;
import selection.Selection;
import selection.Selection_implement;
import userinterface.User_interfacinator;

/**
 * Classe main
 * 
 * <p>
 * This is a text editor, probably the best one you have ever seen if you are blind
 * Here is a list of the different classes
 * <ul>
 *   <li>the engine {@link engine.Engine_implementation}</li>
 *   <li>the selection {@link selection.Selection_implement}</li>
 *   <li>the command managerinator * a lire avec la voix de Doofernshmitz* {@link Command.Command_managerinator}</li>
 *   <li>the user interface {@link userinterface.User_interfacinator}</li>
 * </ul>
 * </p>
 */
public class Main {

    /**
     * 
     * 
     * <p>
     * The laucher of the best text editor
     * </p>
     *
     * @param args I'm not sure what is this param but without it, the code doesn't work
     */
    public static void main(String[] args) {
        // Contenu initial du buffer
        String initialContent = "Ceci est un exemple de contenu dans le buffer. Sélectionnez du texte pour interagir.";

        // Créer les instances des classes principales
        Engine_implementation engine = new Engine_implementation(initialContent);
        Selection selection = new Selection_implement(initialContent);
        Command_managerinator commandManager = new Command_managerinator();

        // Créer et lancer l'interface utilisateur
        User_interfacinator ui = new User_interfacinator(engine, selection, commandManager);
        ui.launchUI();
    }
}

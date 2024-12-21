package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Command.Copinator;
import engine.Engine_implementation;
import selection.Selection_implement;
import javax.swing.JTextArea;
import static org.junit.jupiter.api.Assertions.*;

class testCopinator {

    private Engine_implementation engine;
    private JTextArea textArea;
    private Selection_implement selection;
    private Copinator copinator;

    @BeforeEach
    void setUp() {
        // Initialisation des objets nécessaires pour le test
        engine = new Engine_implementation("initial content");  // Instance réelle de Engine_implementation
        textArea = new JTextArea();  // Instance réelle de JTextArea
        selection = new Selection_implement("initial content");  // Instance réelle de Selection_implement

        // Création de l'instance de la classe à tester
        copinator = new Copinator(engine, textArea, selection);
    }

    @Test
    void testExecute_validSelection() {
        // Scénario de sélection valide
        String text = "Hello, this is a test text.";
        textArea.setText(text);
        textArea.setSelectionStart(7);  // Début de la sélection
        textArea.setSelectionEnd(11);  // Fin de la sélection

        // Exécution de la commande
        copinator.execute();

        // Vérifications des effets de l'exécution
        assertEquals(7, selection.getBeginIndex());  // Vérifie que le début de la sélection est correct
        assertEquals(11, selection.getEndIndex());  // Vérifie que la fin de la sélection est correcte
        assertEquals("this", engine.getClipboardContents());  // Vérifie que le contenu du presse-papiers est correct
    }

    @Test
    void testExecute_noSelection() {
        // Scénario de sélection invalide (fin avant début)
        String text = "Hello, this is a test text.";
        textArea.setText(text);
        textArea.setSelectionStart(5);  // Début de la sélection
        textArea.setSelectionEnd(3);  // Fin avant le début

        // Exécution de la commande
        copinator.execute();

        // Vérifications que rien n'a été copié
        assertEquals("", engine.getClipboardContents());  // Le presse-papiers doit être vide car la sélection est invalide
    }

    @Test
    void testExecute_emptySelection() {
        // Scénario où aucune sélection n'est faite
        String text = "Hello, this is a test text.";
        textArea.setText(text);
        textArea.setSelectionStart(-1);  // Pas de sélection
        textArea.setSelectionEnd(-1);  // Pas de sélection

        // Exécution de la commande
        copinator.execute();

        // Vérifications que rien n'a été copié
        assertEquals("", engine.getClipboardContents());  // Le presse-papiers doit être vide
    }
}

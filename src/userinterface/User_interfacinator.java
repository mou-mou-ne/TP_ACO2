/**
 * Package userinterface
 *  @author Salma BOUCHRA 
 * <p>
 * 
 * This is the userinterfacinator, which is used to represent the user interface

 * </p>
 
 */
package userinterface;

import engine.Engine_implementation;
import selection.Selection;

import javax.swing.*;

import Command.Command_managerinator;
import Command.Copinator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe User_interfacinator
 * 
 * <p>
 * This class manage the interface affichage (?) and the different interaction the user wil have
 * with the editor. 
 * It has 3 buttons : cut copy, paste and a place to write 
 * </p>
 */
public class User_interfacinator {
    private Engine_implementation engine;
    private Selection selection;
    private Command_managerinator commandManager;

    /**
     * Contructor de User_interfacinator
     * 
     * @param engine managing the operation on the selection
     * @param selection to manage the text selection
     * @param commandManager to execture the commands
     */
    public User_interfacinator(Engine_implementation engine, Selection selection, Command_managerinator commandManager) {
        this.engine = engine;
        this.selection = selection;
        this.commandManager = commandManager;
    }

    /**
     * 
     * 
     * <p>
     * Launch the user interface 
     * </p>
     */
    public void launchUI() {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Application de gestion du texte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Zone de texte pour afficher/modifier le contenu
        JTextArea textArea = new JTextArea(engine.getBufferContents());
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Boutons pour copier, couper, et coller
        JButton copyButton = new JButton("Copier");
        JButton cutButton = new JButton("Couper");
        JButton pasteButton = new JButton("Coller");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(copyButton);
        buttonPanel.add(cutButton);
        buttonPanel.add(pasteButton);

        // Étiquette pour afficher le contenu du presse-papiers
        JLabel clipboardLabel = new JLabel("Contenu du presse-papiers : (aucun)");

        // Commande pour la gestion des actions de copie
        Copinator copinator = new Copinator(engine, textArea, selection);

        // Action du bouton "Copier"
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                if (start != end && start >= 0 && end <= textArea.getText().length()) {
                    commandManager.executeCommand(copinator);
                    clipboardLabel.setText("Contenu du presse-papiers : " + engine.getClipboardContents());
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez sélectionner du texte à copier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton "Couper"
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int start = textArea.getSelectionStart();
                    int end = textArea.getSelectionEnd();
                    if (start != end && start >= 0 && end <= textArea.getText().length()) {
                        commandManager.executeCommand(copinator);
                        clipboardLabel.setText("Contenu du presse-papiers : " + engine.getClipboardContents());

                        if (start >= 0 && end <= textArea.getText().length() && start < end) {
                            String selectedText = textArea.getText().substring(start, end);
                            textArea.replaceRange("", start, end);
                            clipboardLabel.setText("Contenu du presse-papiers : " + selectedText);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Sélection invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erreur interne : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton "Coller"
        pasteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clipboardContents = engine.getClipboardContents();
                if (!clipboardContents.isEmpty()) {
                    int caretPosition = textArea.getCaretPosition();
                    textArea.insert(clipboardContents, caretPosition);
                    engine.setBufferContents(textArea.getText());
                } else {
                    JOptionPane.showMessageDialog(frame, "Le presse-papiers est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Organisation des composants dans le panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(clipboardLabel, BorderLayout.SOUTH);

        // Affichage de la fenêtre
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

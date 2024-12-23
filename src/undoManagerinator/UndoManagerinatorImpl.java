package undoManagerinator;

import java.util.ArrayList;
import java.util.List;

import mementomoris.EditorMemento;
import engine.Engine_implementation;

/**
 * La classe  UndoManagerinatorImpl implémente l'interface {@link UndoManagerinator} 
 * pour gérer l'annulation et la réinitialisation des actions dans l'éditeur. 
 * Elle utilise le concept de Memento pour stocker les états précédents et futurs, 
 * permettant ainsi de revenir en arrière ou d'annuler une annulation.
 */
public class UndoManagerinatorImpl implements UndoManagerinator {

    private List<EditorMemento> pastStates;  
    private List<EditorMemento> futurStates; 
    private Engine_implementation engine;    
    private boolean undoIs;                  

    /**
     * Constructeur qui initialise l'état de l'éditeur avec un contenu vide et un curseur à la position 0.
     * 
     * @param engine l'implémentation du moteur d'édition qui sera utilisée pour stocker et récupérer l'état
     */
    public UndoManagerinatorImpl(Engine_implementation engine) {
        StringBuilder start = new StringBuilder("");
        EditorMemento etatInital = new EditorMemento(start, 0, 0, "");

        this.undoIs = false;
        this.pastStates = new ArrayList<EditorMemento>();
        this.futurStates = new ArrayList<EditorMemento>();
        this.engine = engine;

        this.pastStates.add(etatInital);  
    }

    /**
     * Sauvegarde l'état actuel de l'éditeur. Si une action d'annulation est en cours,
     * elle stocke l'état dans la liste des états futurs. Sinon, elle l'ajoute à la liste des états passés.
     */
    @Override
    public void store() {
        if (undoIs) {
            this.futurStates.add((EditorMemento) engine.getMemento());
        } else {
            EditorMemento gi = (EditorMemento) engine.getMemento();
            this.pastStates.add(gi);  
        }
    }

    /**
     * Annule la dernière action effectuée. Restaure l'état précédent et le charge dans l'éditeur.
     */
    @Override
    public void undo() {
    	if (this.pastStates.isEmpty()) {
            throw new IllegalStateException("No state to undo");
        }
        EditorMemento first = this.pastStates.get(pastStates.size() - 1);

        this.undoIs = true;
        this.store();

        // Restaure l'état précédent dans l'éditeur
        this.engine.getSelection().setBeginIndex(first.getBeginIndex());
        this.engine.getSelection().setEndIndex(first.getEndIndex());
        this.engine.setBufferContent(first.getBufferContent());
        this.engine.setClipboardContent(first.getClipboard());

        this.pastStates.remove(pastStates.size() - 1);
        this.undoIs = false;
    }

    /**
     * Réeffectue la dernière action annulée. Restaure l'état dans l'éditeur à partir des futurs états.
     */
    @Override
    public void redo() {
    	if (this.futurStates.isEmpty()) {
            throw new IllegalStateException("No state to redo");
        }
        EditorMemento first = this.futurStates.get(futurStates.size() - 1);

        // Restaure l'état futur dans l'éditeur
        this.engine.getSelection().setBeginIndex(first.getBeginIndex());
        this.engine.getSelection().setEndIndex(first.getEndIndex());
        this.engine.setBufferContent(first.getBufferContent());
        this.engine.setClipboardContent(first.getClipboard());

        this.futurStates.remove(futurStates.size() - 1); 
    }

    /**
     * Méthode d'accès pour récupérer la liste des états passés. Utile pour les tests.
     * 
     * @return la liste des états passés
     */
    public List<EditorMemento> getList() {
        return this.pastStates;
    }
}

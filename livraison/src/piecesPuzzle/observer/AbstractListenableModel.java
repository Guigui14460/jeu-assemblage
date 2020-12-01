package piecesPuzzle.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite donnant la base d'un modèle écoutable.
 */
public abstract class AbstractListenableModel implements ListenableModel {
    /**
     * Stocke tous les écouteurs du modèle.
     */
    private List<ModelListener> listeners;

    /**
     * Constructeur par défaut. Initialise la liste des écouteurs de modèle.
     */
    public AbstractListenableModel() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addModelListener(ModelListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeModelListener(ModelListener l) {
        this.listeners.remove(l);
    }

    /**
     * Méthode à utiliser lorsque l'on met à jour notre modèle. Elle appelle
     * automatiquement les méthodes {@code somethingHasChanged} de tous les
     * écouteurs de notre modèle.
     */
    protected void fireChange() {
        for (ModelListener l : this.listeners) {
            l.somethingHasChanged(this); // on prévient les écouteurs
        }
    }
}

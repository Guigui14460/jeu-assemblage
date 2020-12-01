package piecesPuzzle.observer;

/**
 * Interface décrivant un modèle général écoutable.
 */
public interface ListenableModel {
    /**
     * Ajoute un écouteur au modèle écoutable.
     * 
     * @param l écouteur à ajouter
     */
    public void addModelListener(ModelListener l);

    /**
     * Retire un écouteur du modèle écoutable.
     * 
     * @param l écouteur à retirer
     */
    public void removeModelListener(ModelListener l);
}

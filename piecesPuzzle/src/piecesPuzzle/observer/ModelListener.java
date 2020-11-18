package piecesPuzzle.observer;

/**
 * Interface décrivant un écouteur de modèle.
 */
public interface ModelListener {
    /**
     * Exécute une certaine action lorsque le modèle qui est écouté a été modifié.
     * 
     * @param source source de la modification
     */
    public void somethingHasChanged(Object source);
}

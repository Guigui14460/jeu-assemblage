package jeuAssemblage.model.arrangements;

/**
 * Représente un type d'arrangement de pièces.
 */
public interface PieceArrangement {
    /**
     * Génère un certain arrangement de pièces.
     * 
     * @return arrangement de pièces
     */
    public Arrangement generateArrangement();
}

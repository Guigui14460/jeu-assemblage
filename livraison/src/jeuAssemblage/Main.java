package jeuAssemblage;

import jeuAssemblage.model.arrangements.DefaultPieceArrangement;
import jeuAssemblage.view.GUI;

public class Main {
    public static void main(String[] args) throws Exception {
        new GUI(new DefaultPieceArrangement());
    }
}

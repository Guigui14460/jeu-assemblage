import model.arrangements.DefaultPieceArrangement;
import view.GUI;

public class Main {
    public static void main(String[] args) throws Exception {
        new GUI(new DefaultPieceArrangement());
    }
}

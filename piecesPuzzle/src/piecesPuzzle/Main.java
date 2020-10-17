package piecesPuzzle;

public class Main {
    public static void main(String[] args){
        Piece p, p2, p3, p4;

        p = new RectanglePiece(0, 0);
        System.out.println(p);
        p.showBoard();
        System.out.println("-----------");
        p.rotate(Piece.Rotate.PLUS_90_DEGREES);
        p.showBoard();

        System.out.println("===================================");
        
        p2 = new LPiece(0, 2);
        System.out.println(p2);
        p2.showBoard();
        System.out.println("-----------");
        p2.rotate(Piece.Rotate.PLUS_90_DEGREES);
        p2.showBoard();
        System.out.println("-----------");
        p2.rotate(Piece.Rotate.MINUS_90_DEGREES);
        p2.showBoard();

        System.out.println("===================================");
        
        p3 = new InvertedLPiece(10, 5);
        System.out.println(p3);
        p3.showBoard();
        System.out.println("-----------");
        p3.rotate(Piece.Rotate.PLUS_90_DEGREES);
        p3.showBoard();
        System.out.println("-----------");
        p3.rotate(Piece.Rotate.MINUS_90_DEGREES);
        p3.showBoard();

        System.out.println("===================================");
        
        p4 = new TPiece(20, 5);
        System.out.println(p4);
        p4.showBoard();
        System.out.println("-----------");
        p4.rotate(Piece.Rotate.PLUS_90_DEGREES);
        p4.showBoard();
        System.out.println("-----------");
        p4.rotate(Piece.Rotate.MINUS_90_DEGREES);
        p4.showBoard();

        System.out.println("===================================");
        
        int x = 20, y = 5, width = 5, height = 7;
        p4 = new TPiece(x, y, width, height);
        System.out.println(p4);
        p4.showBoard();
        System.out.println("-----------");
        p4.rotate(Piece.Rotate.PLUS_90_DEGREES);
        p4.showBoard();
        System.out.println("-----------");
        p4.rotate(Piece.Rotate.MINUS_90_DEGREES);
        p4.showBoard();
        
        // tester l'accès aux valeurs de la pièce
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                p4.occupies(j, i);
            }
        }
        // tester l'accès aux valeurs de la pièce en fonction du placement de celle-ci
        for(int i = x; i < x + width; i++){
            for(int j = y; j < y + height; j++){
                // System.out.println(i + "," + j);
                p4.occupiesInBoard(i, j);
            }
        }
    }
}

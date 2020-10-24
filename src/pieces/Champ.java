package pieces;


public class Champ extends pieces.AbstractPiece {

    public Champ(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (isWhite){
            System.out.print("Б");
        }
        else{
            System.out.print("Ч");
        }
    }

    private static Boolean lShapedPath(int srcRow, int srcCol,
                                       int destRow, int destCol) {
        // returns true if the path is L-shaped
        // arguments are initial and final coordinates of move in chessboard
        // array
        // good for checking if a move is valid
        return (((srcRow==destRow)&&(Math.abs(srcCol-destCol)==1))
                || ((Math.abs(srcRow - destRow)) == 1 && (srcCol==destCol))
                ||((srcRow==destRow)&&(Math.abs(srcCol-destCol)==2))
                || ((Math.abs(srcRow - destRow)) == 2 && (srcCol==destCol))
                || ((Math.abs(srcRow - destRow) == Math.abs(srcCol
                - destCol))&&((Math.abs(srcRow - destRow)==2))));
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        return lShapedPath(srcRow, srcCol, destRow, destCol);
    }

    @Override
    public int relativeValue() {
        // TODO Auto-generated method stub
        return 5;
    }

}

package Pieces;

public class Champ implements Figure{
    private boolean isWhite;

    public Champ(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public String toString() {
        return !isWhite ? "Б":"Ч";
    }
}
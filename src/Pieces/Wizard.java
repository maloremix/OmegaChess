package Pieces;

public class Wizard implements Figure{
    private boolean isWhite;

    public Wizard(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public String toString() {
        return !isWhite ? "К":"М";
    }
}
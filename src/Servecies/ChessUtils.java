package Servecies;

import Graph.AdjListsGraph;
import Pieces.*;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class ChessUtils {
    public static boolean moveValid(int srcRow, int srcCol, int destRow, int destCol, AdjListsGraph chessboard, List<Integer> list, List<Integer> list2, Boolean []whitesTurnToMove) {

        // invalid if the move origin or destination is outside the board

        if (srcRow < 0 || srcRow > 11 || srcCol < 0 || srcCol > 11 || destRow < 0
                || destRow > 11 || destCol < 0 || destCol > 11) {
            System.out.println("Move isutside the board");
            return false;
        }

        // Invalid if origin is null
        if (chessboard.graphWeight.get(12 * srcRow + srcCol) == null) {
            System.err.println("Origin is empty");
            return false;
        }

        // Invalid if player moves when it's not their turn
        if ((list2.contains(12 * srcRow + srcCol) && !whitesTurnToMove[0])
                || (list.contains(12 * srcRow + srcCol) && whitesTurnToMove[0])) {
            System.err.println("It's not your turn");
            return false;
        }

        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof Knight){
            if (!KnightPath(srcRow,srcCol,destRow,destCol,chessboard)){
                return false;
            }
        }
        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof Pawn){
            if (!PawnPath(srcRow,srcCol,destRow,destCol,chessboard)){
                return false;
            }
        }

        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof Bishop){
            if (!bishopPath(srcRow,srcCol,destRow,destCol,chessboard)){
                return false;
            }
        }

        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof Rook){
            if (!RookPath(srcRow,srcCol,destRow,destCol,chessboard)){
                return false;
            }
        }

        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof King){
            if (!kingPath(srcRow,srcCol,destRow,destCol,chessboard)){
                return false;
            }
        }

        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof Queen) {
            if (!RookPath(srcRow, srcCol, destRow, destCol, chessboard) || !bishopPath(srcRow,srcCol,destRow,destCol,chessboard)) {
                return false;
            }
        }

        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof Champ) {
            if (!ChampPath(srcRow, srcCol, destRow, destCol, chessboard)) {
                return false;
            }
        }

        if (chessboard.graphWeight.get(12*srcRow+srcCol) instanceof Wizard) {
            if (!WizardPath(srcRow, srcCol, destRow, destCol, chessboard)) {
                return false;
            }
        }





        // this statement stops the statement for checking if white lands on
        // white from performing isWhite() on a null space
        if (chessboard.graphWeight.get(12 * destRow + destCol) == null) {
            return true;
        }

        // invalid if the white lands on white
        if (list2.contains(12 * srcRow + srcCol)
                && list2.contains(12 * destRow + destCol)) {
            System.err.println("White cannot land on white");
            return false;
        }

        // invalid if the black lands on black
        if (list.contains(12 * srcRow + srcCol)
                && list.contains(12 * destRow + destCol)) {
            System.err.println("Black cannot land on black");
            return false;
        }

        return true;

    }
    public static void graphAdd(AdjListsGraph chessboard){
        int cunt;
        int cunt2;
        for (int k = 0; k < 10; k++) {
            cunt = 13+12*k;
            cunt2 = 14 + 12*k;
            for (int i = 0; i < 9; i++) {
                chessboard.addAdge(cunt, cunt2, 0); // вправо
                cunt++;
                cunt2++;
            }
            cunt = 22+12*k;
            cunt2 = 21+12*k;
            for (int i = 0; i < 9; i++) {
                chessboard.addAdge(cunt, cunt2, 1); // влево
                cunt--;
                cunt2--;
            }
            if (k!=9) {
                cunt = 13 + 12 * k;
                cunt2 = 25 + 12 * k;
                for (int i = 0; i < 9; i++) {
                    chessboard.addAdge(cunt, cunt2, 2); // вниз
                    cunt++;
                    cunt2++;
                }
                cunt = 13 + 12 * k;
                cunt2 = 26 + 12 * k;
                for (int i = 0; i < 9; i++) {
                    chessboard.addAdge(cunt, cunt2, 3); // вправо-вниз
                    cunt++;
                    cunt2++;
                }
                cunt = 22 + 12 * k;
                cunt2 = 33 + 12 * k;
                for (int i = 0; i < 9; i++) {
                    chessboard.addAdge(cunt, cunt2, 4); // влево-вниз
                    cunt--;
                    cunt2--;
                }
            }
            if (k != 0) {
                cunt = 25+12*(k-1);
                cunt2 = 14+12*(k-1);
                for (int i = 0; i < 9; i++) {
                    chessboard.addAdge(cunt, cunt2, 5); // вправо-вверх
                    cunt++;
                    cunt2++;
                }
                cunt = 34+12*(k-1);
                cunt2 = 21+12*(k-1);
                for (int i = 0; i < 9; i++) {
                    chessboard.addAdge(cunt, cunt2, 6); // влево-вверх
                    cunt--;
                    cunt2--;
                }
                cunt = 25+12*(k-1);
                cunt2 = 13+12*(k-1);
                for (int i = 0; i < 9; i++) {
                    chessboard.addAdge(cunt, cunt2, 7); // вверх
                    cunt++;
                    cunt2++;
                }
            }
        }

        chessboard.addAdge(0,13,3);
        chessboard.addAdge(13,0,6);
        chessboard.addAdge(11,22,4);
        chessboard.addAdge(23,11,5);
        chessboard.addAdge(132,121,5);
        chessboard.addAdge(121,132,4);
        chessboard.addAdge(143,130,6);
        chessboard.addAdge(131,143,3);

        chessboard.addAdge(132,133,0);
        chessboard.addAdge(133,134,0);
        chessboard.addAdge(134,123,5);
        chessboard.addAdge(132,120,7);
        chessboard.addAdge(120,108,7);
        chessboard.addAdge(108,97,5);

        chessboard.addAdge(0,1,0);
        chessboard.addAdge(1,2,0);
        chessboard.addAdge(2,15,3);
        chessboard.addAdge(0,12,2);
        chessboard.addAdge(12,24,2);
        chessboard.addAdge(24,37,3);

        chessboard.addAdge(11,23,2);
        chessboard.addAdge(23,35,2);
        chessboard.addAdge(35,46,4);
        chessboard.addAdge(11,10,1);
        chessboard.addAdge(10,9,1);
        chessboard.addAdge(9,20,4);

        chessboard.addAdge(143,142,1);
        chessboard.addAdge(142,141,1);
        chessboard.addAdge(141,128,6);
        chessboard.addAdge(143,131,7);
        chessboard.addAdge(131,119,7);
        chessboard.addAdge(119,106,6);



    }

    public static void initialiseBoard(AdjListsGraph chessboard, List<Integer> list, List <Integer>list2, Boolean[] whitesTurnToMove) {
        // a chessboard with 8x8 matrix of pieces
        // rows [0] and [1] are black
        // rows [6] and [7] are white

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (row == 0) {
                    switch (col) {
                        case 0:
                            chessboard.graphWeight.set(13+12*row+col, new Champ(false));
                            list.add(13+12*row+col);
                            break;
                        case 1:
                            chessboard.graphWeight.set(13+12*row+col, new Rook(false));
                            list.add(13+12*row+col);
                            break;
                        case 2:
                            chessboard.graphWeight.set(13+12*row+col, new Knight(false));
                            list.add(13+12*row+col);
                            break;
                        case 3:
                            chessboard.graphWeight.set(13+12*row+col, new Bishop(false));
                            list.add(13+12*row+col);
                            break;
                        case 4:
                            chessboard.graphWeight.set(13+12*row+col, new Queen(false));
                            list.add(13+12*row+col);
                            break;
                        case 5:
                            chessboard.graphWeight.set(13+12*row+col, new King(false));
                            list.add(13+12*row+col);
                            break;
                        case 6:
                            chessboard.graphWeight.set(13+12*row+col, new Bishop(false));
                            list.add(13+12*row+col);
                            break;
                        case 7:
                            chessboard.graphWeight.set(13+12*row+col, new Knight(false));
                            list.add(13+12*row+col);
                            break;
                        case 8:
                            chessboard.graphWeight.set(13+12*row+col, new Rook(false));
                            list.add(13+12*row+col);
                            break;
                        case 9:
                            chessboard.graphWeight.set(13+12*row+col, new Champ(false));
                            list.add(13+12*row+col);
                            break;
                    }
                } else if (row == 1) {
                    chessboard.graphWeight.set(13+12*row+col,new Pawn(false));
                    list.add(13+12*row+col);
                } else if (row == 8) {
                    chessboard.graphWeight.set(13+12*row+col,new Pawn(true));
                    list2.add(13+12*row+col);
                } else if (row == 9) {
                    switch (col) {
                        case 0:
                            chessboard.graphWeight.set(13+12*row+col, new Champ(true));
                            list2.add(13+12*row+col);
                            break;
                        case 1:
                            chessboard.graphWeight.set(13+12*row+col, new Rook(true));
                            list2.add(13+12*row+col);
                            break;
                        case 2:
                            chessboard.graphWeight.set(13+12*row+col, new Knight(true));
                            list2.add(13+12*row+col);
                            break;
                        case 3:
                            chessboard.graphWeight.set(13+12*row+col, new Bishop(true));
                            list2.add(13+12*row+col);
                            break;
                        case 4:
                            chessboard.graphWeight.set(13+12*row+col, new Queen(true));
                            list2.add(13+12*row+col);
                            break;
                        case 5:
                            chessboard.graphWeight.set(13+12*row+col, new King(true));
                            list2.add(13+12*row+col);
                            break;
                        case 6:
                            chessboard.graphWeight.set(13+12*row+col, new Bishop(true));
                            list2.add(13+12*row+col);
                            break;
                        case 7:
                            chessboard.graphWeight.set(13+12*row+col, new Knight(true));
                            list2.add(13+12*row+col);
                            break;
                        case 8:
                            chessboard.graphWeight.set(13+12*row+col, new Rook(true));
                            list2.add(13+12*row+col);
                            break;
                        case 9:
                            chessboard.graphWeight.set(13+12*row+col, new Champ(true));
                            list2.add(13+12*row+col);
                            break;
                    }
                } else {
                    chessboard.graphWeight.set(13+12*row+col, null);
                }
            }
        }

        chessboard.graphWeight.set(0, new Wizard(false));
        list.add(0);
        chessboard.graphWeight.set(11, new Wizard(false));
        list.add(0);
        chessboard.graphWeight.set(143, new Wizard(true));
        list2.add(143);
        chessboard.graphWeight.set(132, new Wizard(true));
        list2.add(143);


        // Randomly assign who starts first (black or white)
        Random rand = new Random();
        whitesTurnToMove[0] = rand.nextBoolean();

    }

    public static void printBoard(AdjListsGraph chessboard) {

        System.out.print("\ta\tb\tc\td\te\tf\tg\th\ti\tj\tk\tl");
        System.out.println();
        for (int row = 0; row < 12; row++) {
            System.out.print(12 - row + ".\t");
            for (int col = 0; col < 12; col++) {
                if (chessboard.graphWeight.get(12*row+col) != null) {
                    System.out.print(chessboard.graphWeight.get(12*row+col));
                    System.out.print("\t");

                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static void move(Boolean invalidMove, Boolean [] whitesTurnToMove, String move, Scanner user_input, AdjListsGraph chessboard) {
        if (invalidMove) {
            System.err.println("Move is invalid. Please try again:");
            // System.out.println("Move is invalid. Please try again:");
            invalidMove = false;
        }

        else if (whitesTurnToMove[0]) {
            System.out
                    .println("___________________________________________________\n"
                            + "White's turn to move\n"
                            + "___________________________________________________\n");
        } else {
            System.out
                    .println("___________________________________________________\n"
                            + "Black's turn to move\n"
                            + "___________________________________________________\n");
        }

        move = user_input.nextLine();

        // convert to lower case
        String lowerCase = move.toLowerCase();
        // parse move string into components
        String[] components = lowerCase.split(" ");

        // if you assume that move is "e1 to e5" then
        // components[0].chartAt(0) = 'e'
        // components [0].charAt (1) = '1'

        // use chars in components to set the array coordinates of the
        // move origin and move destination

        int srcRow, srcCol, destRow, destCol;

        if (components[0].length()!=3) {
            srcRow = 11 - (components[0].charAt(1) - '1');
        } else {
            srcRow = '2' - (components[0].charAt(2));
        }
        srcCol = components[0].charAt(0) - 'a';
        if (components[2].length()!=3) {
            destRow = 11 - (components[2].charAt(1) - '1');
        } else {
            destRow = '2' - (components[0].charAt(2));
        }
        destCol = components[2].charAt(0) - 'a';

        if (moveValid(srcRow,srcCol,destRow,destCol,chessboard,ChessBoard.getList(),ChessBoard.getList2(),whitesTurnToMove)) {
            // put piece in destination
            if (chessboard.graphWeight.get(12*destRow+destCol)!=null){
                if (ChessBoard.getList().contains(12*destRow+destCol)){
                    ChessBoard.getList().remove(12*destRow+destCol);
                } else {
                    ChessBoard.getList2().remove(12*destRow+destCol);
                }
            }
            chessboard.graphWeight.set(12*destRow+destCol,chessboard.graphWeight.get(12*srcRow+srcCol));
            // empty the origin of the move
            chessboard.graphWeight.set(12*srcRow+srcCol,null);
            whitesTurnToMove[0] = !whitesTurnToMove[0];
        } else {
            invalidMove = true;
            move(invalidMove,whitesTurnToMove,move,user_input,chessboard);

        }

    }

    private static Boolean helpKnight (int srcRow, int srcCol,
                                 int destRow, int destCol, AdjListsGraph gr, int nap, int nap2){
        List <Integer> listAdj = AdjListsGraph.copyIterator(gr.adjacencies(12*srcRow+srcCol).iterator());

        int help = 0;

        if (listAdj.get(nap)!=null){
            listAdj = AdjListsGraph.copyIterator(gr.adjacencies(listAdj.get(nap)).iterator());
            if (listAdj.get(nap2)!=null && listAdj.get(nap2)==12*destRow+destCol){
                return true;
            }
        }
        return false;
    }

    private static Boolean KnightPath(int srcRow, int srcCol,
                                       int destRow, int destCol, AdjListsGraph gr) {
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,7,5)){
            return true;
        }
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,0,5)){
            return true;
        }
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,0,3)){
            return true;
        }
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,2,3)){
            return true;
        }
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,1,6)){
            return true;
        }
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,6,7)){
            return true;
        }
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,1,4)){
            return true;
        }
        if (helpKnight(srcRow,srcCol,destRow,destCol,gr,2,4)){
            return true;
        }
        return false;
    }

    private static Boolean PawnHelp (int srcRow, int srcCol,
                                 int destRow, int destCol, AdjListsGraph gr, int nap,int count){
        List <Integer> listAdj = AdjListsGraph.copyIterator(gr.adjacencies(12*srcRow+srcCol).iterator());

        int help = 0;

        while(listAdj.get(nap)!=null){
            if (help==count){
                return false;
            } else
            if (listAdj.get(nap) == null){
                break;
            } else
            if (listAdj.get(nap)==(12*destRow+destCol)){
                return true;
            } else {
                listAdj = AdjListsGraph.copyIterator(gr.adjacencies(listAdj.get(nap)).iterator());
                help++;
            }
        }
        return false;
    }

    public static boolean PawnPath(int srcRow, int srcCol, int destRow, int destCol, AdjListsGraph gr) {
        List<Integer> listAdj = AdjListsGraph.copyIterator(gr.adjacencies(12*srcRow+srcCol).iterator());
        if (ChessBoard.getList2().contains(12*srcRow+srcCol)){
            if (13+12*srcRow+srcCol >=109 && 12*srcRow+srcCol <= 118) {
                if (PawnHelp(srcRow, srcCol, destRow, destCol, gr, 7, 2)){
                    return true;
                }
            } else {
                if (PawnHelp(srcRow, srcCol, destRow, destCol, gr, 5, 1)){
                    if (gr.graphWeight.get(listAdj.get(5))!=null){
                        return true;
                    }
                } else {
                    if (PawnHelp(srcRow, srcCol, destRow, destCol, gr, 6, 1)){
                        if (gr.graphWeight.get(listAdj.get(6))!=null){
                            return true;
                        }
                    }
                }
            }
        } else {
            if (12*srcRow+srcCol >=25 && 12*srcRow+srcCol <= 34) {
                if (PawnHelp(srcRow, srcCol, destRow, destCol, gr, 2, 2)){
                    return true;
                }
            } else {
                if (PawnHelp(srcRow, srcCol, destRow, destCol, gr, 3, 1)){
                    if (gr.graphWeight.get(listAdj.get(3))!=null){
                        return true;
                    }
                } else {
                    if (PawnHelp(srcRow, srcCol, destRow, destCol, gr, 4, 1)){
                        if (gr.graphWeight.get(listAdj.get(4))!=null){
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    private static Boolean helpBishopRook (int srcRow, int srcCol,
                                 int destRow, int destCol, AdjListsGraph gr, int nap){
        List <Integer> listAdj = AdjListsGraph.copyIterator(gr.adjacencies(12*srcRow+srcCol).iterator());

        while(listAdj.get(nap)!=null){
            if (listAdj.get(nap) == null){
                break;
            } else
            if (listAdj.get(nap)==(12*destRow+destCol)){
                return true;
            } else {
                listAdj = AdjListsGraph.copyIterator(gr.adjacencies(listAdj.get(nap)).iterator());
            }
        }
        return false;
    }

    private static Boolean bishopPath(int srcRow, int srcCol,
                                        int destRow, int destCol, AdjListsGraph gr) {
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,3)){
            return true;
        }
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,4)){
            return true;
        }
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,5)){
            return true;
        }
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,6)){
            return true;
        }
        return false;
    }

    private static Boolean RookPath(int srcRow, int srcCol,
                                        int destRow, int destCol, AdjListsGraph gr) {
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,0)){
            return true;
        }
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,1)){
            return true;
        }
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,2)){
            return true;
        }
        if (helpBishopRook(srcRow,srcCol,destRow,destCol,gr,7)){
            return true;
        }
        return false;
    }

    public static boolean kingPath(int srcRow, int srcCol, int destRow, int destCol, AdjListsGraph gr) {
        Iterable<Integer> it = gr.adjacencies(srcRow * 12 + srcCol);
        for (Integer i : it){
            if (i==destRow*12+destCol){
                return true;
            }
        }
        return false;
    }

    private static Boolean champHelp (int srcRow, int srcCol,
                                 int destRow, int destCol, AdjListsGraph gr, int nap,int count){
        List<Integer> listAdj = AdjListsGraph.copyIterator(gr.adjacencies(12*srcRow+srcCol).iterator());

        int help = 0;

        while(listAdj.get(nap)!=null){
            if (help==count){
                return false;
            } else
            if (listAdj.get(nap) == null){
                break;
            } else
            if (listAdj.get(nap)==(12*destRow+destCol)){
                return true;
            } else {
                listAdj = AdjListsGraph.copyIterator(gr.adjacencies(listAdj.get(nap)).iterator());
                help++;
            }
        }
        return false;
    }

    private static Boolean ChampPath(int srcRow, int srcCol,
                                      int destRow, int destCol, AdjListsGraph gr) {
        for (int i = 0; i < 8; i++) {
            if (champHelp(srcRow, srcCol, destRow, destCol, gr, i, 2)) {
                return true;
            }
        }
        if (champHelp(srcRow, srcCol, destRow, destCol, gr, 0, 1)) {
            return true;
        }
        if (champHelp(srcRow, srcCol, destRow, destCol, gr, 1, 1)) {
            return true;
        }
        if (champHelp(srcRow, srcCol, destRow, destCol, gr, 2, 1)) {
            return true;
        }
        if (champHelp(srcRow,srcCol,destRow,destCol,gr,7,1)) {
            return true;
        }
        return false;
    }

    private static Boolean WizardHelp (int srcRow, int srcCol,
                                 int destRow, int destCol, AdjListsGraph gr, int nap, int nap2){
        List <Integer> listAdj = AdjListsGraph.copyIterator(gr.adjacencies(12*srcRow+srcCol).iterator());

        int help = 0;

        if (listAdj.get(nap)!=null) {
            listAdj = AdjListsGraph.copyIterator(gr.adjacencies(listAdj.get(nap)).iterator());
            if (listAdj.get(nap) != null) {
                listAdj = AdjListsGraph.copyIterator(gr.adjacencies(listAdj.get(nap)).iterator());
                if (listAdj.get(nap2) != null && listAdj.get(nap2) == 12 * destRow + destCol) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Boolean WizardHelp2(int srcRow, int srcCol,
                                 int destRow, int destCol, AdjListsGraph gr, int nap){
        List<Integer> listAdj = AdjListsGraph.copyIterator(gr.adjacencies(12*srcRow+srcCol).iterator());

        int help = 0;

        while(listAdj.get(nap)!=null){
            if (help == 1){
                return false;
            } else
            if (listAdj.get(nap) == null){
                break;
            } else
            if (listAdj.get(nap)==(12*destRow+destCol)){
                return true;
            } else {
                listAdj = AdjListsGraph.copyIterator(gr.adjacencies(listAdj.get(nap)).iterator());
                help++;
            }
        }
        return false;
    }

    private static Boolean WizardPath(int srcRow, int srcCol,
                                       int destRow, int destCol, AdjListsGraph gr) {
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,7,5)){
            return true;
        }
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,0,5)){
            return true;
        }
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,0,3)){
            return true;
        }
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,2,3)){
            return true;
        }
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,1,6)){
            return true;
        }
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,7,6)){
            return true;
        }
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,1,4)){
            return true;
        }
        if (WizardHelp(srcRow,srcCol,destRow,destCol,gr,2,4)){
            return true;
        }
        if (WizardHelp2(srcRow,srcCol,destRow,destCol,gr,3)){
            return true;
        }
        if (WizardHelp2(srcRow,srcCol,destRow,destCol,gr,4)){
            return true;
        }
        if (WizardHelp2(srcRow,srcCol,destRow,destCol,gr,5)){
            return true;
        }
        if (WizardHelp2(srcRow,srcCol,destRow,destCol,gr,6)){
            return true;
        }

        return false;
    }

}

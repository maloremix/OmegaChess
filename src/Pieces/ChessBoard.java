package Pieces;

import Graph.AdjListsGraph;
import Servecies.ChessUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessBoard {

    private String st;
    private static Scanner input = new Scanner(System.in);

    public ChessBoard (){
        ChessUtils.graphAdd(graph);
        ChessUtils.initialiseBoard(graph,list,list2, whitesTurnToMove);
        while (true){
            ChessUtils.printBoard(graph);
            ChessUtils.move(false,whitesTurnToMove,st,input,graph);
        }
    }
    private AdjListsGraph graph = new AdjListsGraph();
    private Figure [] graphWeith = new Figure[144];
    private static Boolean[] whitesTurnToMove = new Boolean[1];

    private static List<Integer> list = new ArrayList<Integer>();
    private static List <Integer> list2 = new ArrayList<Integer>();

    public Figure[] getFigureMas(){
        return graphWeith;
    }

    public static List<Integer> getList(){
        return list;
    }

    public static List<Integer> getList2(){
        return list2;
    }

    public static Boolean[] getWhitesTurnToMove(){
        return whitesTurnToMove;
    }


}

package com.example.chessclient;

import javafx.application.Platform;
import javafx.scene.Node;

public class GameThread extends Thread{
    ChessBoard board;
    private boolean checkmate = false;
    private boolean movePossible = false;
    private ChessSquare firstClicked = null;
    private ChessSquare secondClicked = null;
    private ChessSquare fromSquare = null;
    private ChessSquare toSquare = null;
    private int clickedCounter = 0;
    public int moveCounter = 0;
    King whiteKing, blackKing;

    public GameThread(ChessBoard board) {
        this.board = board;
    }

    @Override
    public void run() {
        findKings();
        while(!checkmate){
            try {
                if(movePossible) {
                    playMove();
                }

                for(Node node: board.getChildren()){

                    if(node instanceof ChessSquare){

                        switch (clickedCounter){
                            case 0->{

                                if(((ChessSquare) node).isSquareClicked()){
                                    firstClicked = (ChessSquare) node;
                                    secondClicked = null;
                                    clickedCounter = 1;
                                }

                            }

                            case 1->{

                                if(((ChessSquare) node).isSquareClicked()&& node != firstClicked){
                                    secondClicked = (ChessSquare) node;
                                    clickedCounter = 2;
                                }

                            }

                            case 2->{
                                if(firstClicked.getPiece()!=null){

                                    ChessSquare toSquare = (ChessSquare) board.getChildren().get((secondClicked.getRow()-1)*8 + (secondClicked.getColumn() -1));
                                    //check if move is legal and if new square is occupied by same color piece and whose move it is
                                    if(firstClicked.getPiece().isMoveToPositionLegal(secondClicked.getColumn(),secondClicked.getRow()) && !toSquare.isSquareOccupiedByColor(firstClicked.getPiece().getColor()) && firstClicked.getPiece().getColor()==(moveCounter%2) && !(whiteKing.isKingInCheck(whiteKing.col,whiteKing.row)) && !(blackKing.isKingInCheck(blackKing.col,blackKing.row))){
                                        movePossible=true;
                                    }
                                    else{
                                        firstClicked.setToNotCLicked();
                                        firstClicked = secondClicked;
                                        secondClicked = null;

                                        clickedCounter = 1;
                                        movePossible = false;
                                    }

                                }
                                else{
                                    firstClicked.setToNotCLicked();
                                    firstClicked = secondClicked;
                                    secondClicked = null;

                                    clickedCounter = 1;
                                    movePossible = false;
                                }

                            }
                            default -> throw new IllegalStateException("Unexpected value: " + clickedCounter);
                        }

                    }
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private void findKings() {
        for (Node node : board.getChildren()) {
            if (node instanceof ChessSquare) {
                ChessPiece piece = ((ChessSquare) node).getPiece();
                if (piece instanceof King) {
                    if (piece.getColor() == ChessPiece.WHITE) {
                        whiteKing = (King) piece;
                    } else if (piece.getColor() == ChessPiece.BLACK) {
                        blackKing = (King) piece;
                    }
                }
            }
        }
    }
    private void playMove() {

        fromSquare = firstClicked;
        toSquare = secondClicked;

        firstClicked.setToNotCLicked();
        firstClicked=null;

        secondClicked.setToNotCLicked();
        secondClicked=null;

        clickedCounter=0;
        moveCounter++;

        Platform.runLater(()->{
            board.move(fromSquare.getColumn(),toSquare.getColumn(),fromSquare.getRow(),toSquare.getRow());
            movePossible = false;

            fromSquare = null;
            toSquare = null;
        });


    }
}

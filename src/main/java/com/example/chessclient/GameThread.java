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
    ChessPiece pieceFromOrgSquare = null;
    ChessPiece pieceToReplace = null;
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

                                    //check if move is legal and if new square is occupied by same color piece and whose move it is
                                    if(firstClicked.getPiece().isMoveToPositionLegal(secondClicked.getColumn(),secondClicked.getRow()) && !secondClicked.isSquareOccupiedByColor(firstClicked.getPiece().getColor()) && firstClicked.getPiece().getColor()==(moveCounter%2)){
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

        firstClicked=null;
        secondClicked=null;

        fromSquare.setToNotCLicked();
        clickedCounter=0;

        pieceFromOrgSquare = fromSquare.getPiece();
        pieceToReplace = toSquare.getPiece();

        Platform.runLater(()->{
            board.move(fromSquare.getColumn(),toSquare.getColumn(),fromSquare.getRow(),toSquare.getRow());
            isInCheckAfterMove();

            movePossible = false;

            fromSquare = null;
            toSquare = null;
        });


    }

    private void isInCheckAfterMove() {
        if(moveCounter%2==0 && whiteKing.isKingInCheck(whiteKing.col,whiteKing.row)){
            fromSquare.replacePiece(pieceFromOrgSquare);
            toSquare.replacePiece(pieceToReplace);

            System.out.println("white king is in check");
        } else if (moveCounter%2 == 1 && blackKing.isKingInCheck(blackKing.col,blackKing.row)) {
            fromSquare.replacePiece(pieceFromOrgSquare);
            toSquare.replacePiece(pieceToReplace);
            System.out.println("black king is in check");
        }
        else{
            toSquare.setToNotCLicked();

            moveCounter++;
        }
    }
}

package com.example.chessclient;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class ChessBoard extends GridPane {

    public ChessBoard() {}

    public void generateBoard(){
        ChessSquare square;
        Color squareColor;

        for (int rows = 1; rows <= HelloController.HEIGHT ; rows++){
            for (int columns = 1; columns <= HelloController.WIDTH; columns ++){

                if(rows%2==0){
                    if(columns%2==0){
                        squareColor = Color.GREEN;
                    }
                    else{
                        squareColor = Color.LIGHTGRAY.brighter();
                    }
                }
                else{
                    if(columns%2==0){
                        squareColor = Color.LIGHTGRAY.brighter();
                    }
                    else{
                        squareColor = Color.GREEN;
                    }
                }
                square = new ChessSquare(this,squareColor,rows,columns);
                this.add(square,columns,rows);
            }
        }

        ChessPiece piece = null;
        ChessSquare tempSquare;
        for (int rows = 1; rows <= HelloController.HEIGHT ; rows++) {
            for (int columns = 1; columns <= HelloController.WIDTH; columns++) {
                //BLACK PIECES---->DOWN
                if (rows == 8) {
                    if (columns == 1 || columns == 8) {
                        piece = new Rook(ChessPiece.BLACK,columns,rows);
                    } else if (columns == 2 || columns == 7) {
                        piece = new Knight(ChessPiece.BLACK,columns,rows);
                    } else if (columns == 3 || columns == 6) {
                        piece = new Bishup(ChessPiece.BLACK,columns,rows);
                    } else if (columns == 4) {
                        piece = new King(ChessPiece.BLACK,columns,rows);
                    } else {
                        piece = new Queen(ChessPiece.BLACK,columns,rows);
                    }
                } else if (rows == 7) {
                    piece = new Pawn(ChessPiece.BLACK,columns,rows);
                }
                //WHITE PIECES--->TOP
                else if (rows == 1) {
                    if (columns == 1 || columns == 8) {
                        piece = new Rook(ChessPiece.WHITE,columns,rows);
                    } else if (columns == 2 || columns == 7) {
                        piece = new Knight(ChessPiece.WHITE,columns,rows);
                    } else if (columns == 3 || columns == 6) {
                        piece = new Bishup(ChessPiece.WHITE,columns,rows);
                    } else if (columns == 4) {
                        piece = new King(ChessPiece.WHITE,columns,rows);
                    } else {
                        piece = new Queen(ChessPiece.WHITE,columns,rows);
                    }
                } else if (rows == 2) {
                    piece = new Pawn(ChessPiece.WHITE,columns,rows);
                }
                else{
                    piece = null;
                }

                if (piece != null) {
                    tempSquare = (ChessSquare) this.getChildren().get((rows - 1) * 8 + (columns - 1));
                    tempSquare.addPiece(piece);
                }
            }
        }

    }

    public ChessSquare getSquare(int col, int row){
        ChessSquare square = (ChessSquare) this.getChildren().get((row - 1) * 8 + (col - 1));
        return square;
    }
    public ChessPiece getPieceOnSquare(int col, int row){
        ChessSquare square = (ChessSquare) this.getChildren().get((row - 1) * 8 + (col - 1));
        return square.getPiece();
    }

    public void move(int fromCol, int toCol, int fromRow, int toRow){
        ChessPiece pieceFromOriginalPos;
        pieceFromOriginalPos = getPieceOnSquare(fromCol,fromRow);

        ChessSquare fromSquare = (ChessSquare) this.getChildren().get((fromRow-1)*8 + (fromCol -1));
        ChessSquare toSquare = (ChessSquare) this.getChildren().get((toRow-1)*8 + (toCol -1));

        //check if new Square is occupied by same color
        System.out.println(pieceFromOriginalPos.getColor() + "original piece");
        if(!toSquare.isSquareOccupiedByColor(pieceFromOriginalPos.getColor())) {
            if (pieceFromOriginalPos.isMoveToPositionLegal(toCol, toRow)) {
                fromSquare.replacePiece(null);
                toSquare.replacePiece(pieceFromOriginalPos);
            }
        }

    }

}

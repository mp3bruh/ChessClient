package com.example.chessclient;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class ChessBoard extends GridPane {

    public ChessBoard() {}

    public void generateBoard(){
        ChessSquare square;
        Color squareColor;


        Label numberCoordinate = null;
        Label letterCoordinate = null;
        int iColumns; //inverted Columns

        for (int rows = 1; rows <= HelloController.HEIGHT ; rows++){
            for (int columns = 1; columns <= HelloController.WIDTH; columns ++){
                iColumns = HelloController.WIDTH - columns + 1;

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

                if(columns == 1){
                    numberCoordinate = new Label(String.valueOf(rows)); //add number coordinates
                    numberCoordinate.setPrefSize(ChessSquare.SQUARE_SIZE,ChessSquare.SQUARE_SIZE);
                    numberCoordinate.setAlignment(Pos.TOP_LEFT);
                }
                else{
                    numberCoordinate = null;
                }
                if(rows == 8){
                    letterCoordinate = new Label(Column.getLetter(iColumns));    //add letter coordinates
                    letterCoordinate.setPrefSize(ChessSquare.SQUARE_SIZE,ChessSquare.SQUARE_SIZE);
                    letterCoordinate.setAlignment(Pos.BOTTOM_RIGHT);
                }
                else{
                    letterCoordinate = null;
                }

                square = new ChessSquare(this,squareColor,rows,columns);
                this.add(square,columns,rows);

                if(numberCoordinate != null) {
                    square.getChildren().add(numberCoordinate);
                }
                if(letterCoordinate != null) {
                    square.getChildren().add(letterCoordinate);
                }
            }
        }

        ChessPiece piece = null;
        ChessSquare tempSquare;




        for (int rows = 1; rows <= HelloController.HEIGHT ; rows++) {
            for (int columns = 1; columns <= HelloController.WIDTH; columns++) {
                //BLACK PIECES---->DOWN
                if (rows == 8) {
                    if (columns == 1 || columns == 8) {
                        piece = new Rook(ChessPiece.BLACK,columns,rows,this);
                    } else if (columns == 2 || columns == 7) {
                        piece = new Knight(ChessPiece.BLACK,columns,rows,this);
                    } else if (columns == 3 || columns == 6) {
                        piece = new Bishup(ChessPiece.BLACK,columns,rows,this);
                    } else if (columns == 4) {
                        piece = new King(ChessPiece.BLACK,columns,rows,this);
                        piece = new King(ChessPiece.BLACK,columns,rows,this);
                    } else {
                        piece = new Queen(ChessPiece.BLACK,columns,rows,this);
                    }
                } else if (rows == 7) {
                    piece = new Pawn(ChessPiece.BLACK,columns,rows,this);
                }
                //WHITE PIECES--->TOP
                else if (rows == 1) {
                    if (columns == 1 || columns == 8) {
                        piece = new Rook(ChessPiece.WHITE,columns,rows,this);
                    } else if (columns == 2 || columns == 7) {
                        piece = new Knight(ChessPiece.WHITE,columns,rows,this);
                    } else if (columns == 3 || columns == 6) {
                        piece = new Bishup(ChessPiece.WHITE,columns,rows, this);
                    } else if (columns == 4) {
                        piece = new King(ChessPiece.WHITE,columns,rows,this);
                    } else {
                        piece = new Queen(ChessPiece.WHITE,columns,rows,this);
                    }
                } else if (rows == 2) {
                    piece = new Pawn(ChessPiece.WHITE,columns,rows,this);
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

        if (pieceFromOriginalPos.isMoveToPositionLegal(toCol, toRow)) {
            fromSquare.replacePiece(null);
            toSquare.replacePiece(pieceFromOriginalPos);
        }


    }

}

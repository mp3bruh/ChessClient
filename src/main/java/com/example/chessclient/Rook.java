package com.example.chessclient;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Rook extends ChessPiece{
    int color;
    private ChessBoard board;

    public Rook(int color, int col, int row, ChessBoard board) {
        this.color = color;
        this.col = col;
        this.row = row;
        this.board = board;

        if(color==WHITE){
            piece_img = new Image("rook_white.png");
        }
        else if(color==BLACK){
            piece_img = new Image("rook_black.png");
        }
        this.setImage(piece_img);
        this.setFitWidth(PIECE_SIZE);
        this.setFitHeight(PIECE_SIZE);
    }


    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean isMoveToPositionLegal(int toCol, int toRow) {
        this.toCol = toCol;
        this.toRow = toRow;
        return checkIfLegalMove();
    }

    @Override
    public boolean checkIfLegalMove() {
        if (toRow > 8 || toRow < 1 || toCol > 8 || toCol < 1) {
            return false;
        }else if (row != toRow && col != toCol) { //check if piece moved diagonal
            return false;
        }

        //check if piece moved through another piece
        int helpRow;
        int helpCol;
        if (toRow > row) {
            for(helpRow = row+1 ;helpRow < toRow; helpRow++){
                if(board.getSquare(col,helpRow).getPiece() != null){
                    System.out.println("something under me");
                    return false;
                }
            }
        }
        else if (toRow < row) {
            for(helpRow = row-1 ;helpRow > toRow; helpRow--){
                if(board.getSquare(col,helpRow).getPiece() != null){
                    System.out.println("something above me");
                    return false;
                }
            }
        }
        else if (toCol < col) {
            for(helpCol = col -1;helpCol > toCol; helpCol--){
                if(board.getSquare(helpCol,row).getPiece() != null){
                    System.out.println("something on my left");
                    return false;
                }
            }
        }
        else if (toCol > col) {
            for(helpCol = col +1;helpCol < toCol; helpCol++){
                if(board.getSquare(helpCol,row).getPiece() != null){
                    System.out.println("something on my right");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void updateCoordinates(int col, int row) {
        this.col = col;
        this.row = row;
    }

}

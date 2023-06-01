package com.example.chessclient;

import javafx.scene.image.Image;

public class Knight extends ChessPiece{
    int color;
    public Knight(int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;

        if(color==WHITE){
            piece_img = new Image("knight_white.png");
        }
        else if(color==BLACK){
            piece_img = new Image("knight_black.png");
        }
        this.setImage(piece_img);
        this.setFitWidth(PIECE_SIZE);
        this.setFitHeight(PIECE_SIZE);
    }


    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return col;
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
        }else if (Math.abs(toRow - row) + Math.abs(toCol - col) != 3){
            return false;
        }else if(Math.abs(toRow - row) == 3 || Math.abs(toCol - col) == 3){
            return false;
        }

        //check if piece moved through another piece

        return true;
    }

    @Override
    public void updateCoordinates(int col, int row) {
        this.col = col;
        this.row = row;
    }
}

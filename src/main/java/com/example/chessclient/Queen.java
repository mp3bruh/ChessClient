package com.example.chessclient;

import javafx.scene.image.Image;

public class Queen extends ChessPiece{
    int color;
    public Queen(int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;

        if(color==WHITE){
            piece_img = new Image("queen_white.png");
        }
        else if(color==BLACK){
            piece_img = new Image("queen_black.png");
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
        } else if ((Math.abs(toRow - row) != Math.abs(toCol - col)) && (row != toRow && col != toCol) ) { //check if piece moved same amount of tiles vertically and horizontally and check if piece moved straight
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

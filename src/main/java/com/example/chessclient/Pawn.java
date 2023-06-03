package com.example.chessclient;

import javafx.scene.image.Image;

public class Pawn extends ChessPiece{

    int color;

    public Pawn(int color, int col, int row) {

        this.color = color;
        this.col = col;
        this.row = row;

        if(color==WHITE){
            piece_img = new Image("pawn_white.png");
        }
        else if(color==BLACK){
            piece_img = new Image("pawn_black.png");
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
        }
        if ((color == WHITE && row > 2) && (toRow - row > 1)){//checks if pawn already has made first move so after his first row he cant make 2 moves forward
            return false;
        }else if ((color == BLACK && row < 7) && (row - toRow > 1)){
            return false;
        }
        if ((color == WHITE && row <= 2) && (toCol - col > 1 || col - toCol > 1 || row -toRow > 1 && col - toCol == 1 || toRow -row > 1 && toCol - col == -1)){ // checks that if its an l move
            return false;
        } else if ((color == BLACK && row <= 7) && (toCol - col > 1 || col - toCol > 1 || row -toRow > 1 && col - toCol == 1 || toRow -row > 1 && toCol - col == -1)){ // checks that if its an l move
            return false;
        }
        if(row == toRow){//pawn can not move in his row on the left or right ... diagonal is still possible since we have to check if there is another piece
            return false;
        }
        if(row == toRow && col == toCol){
            return false;
        } else if (color==WHITE) {
            if (toRow < row || toRow > row+2) {
                return false;
            }
        }else if (color == BLACK){
            if (toRow > row || toRow < row-2) {
                return false;
            }
        }
        //hello
        //check if piece moved through another piece

        return true;
    }

    @Override
    public void updateCoordinates(int col, int row) {
        this.col = col;
        this.row = row;
    }
}


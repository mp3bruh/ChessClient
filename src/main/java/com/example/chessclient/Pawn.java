package com.example.chessclient;

import javafx.scene.image.Image;

public class Pawn extends ChessPiece{

    int color;

    private ChessBoard board;

    public Pawn(int color, int col, int row, ChessBoard board) {
        this.color = color;
        this.col = col;
        this.row = row;
        this.board = board;

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
        if((row == 2 || row == 7) && (col != toCol) &&(Math.abs(toCol - col) >=2 )){
            return false;
        }
        else if ((Math.abs(toRow - row) != Math.abs(toCol - col)) && (row != toRow && col != toCol) ) { //check if piece moved same amount of tiles vertically and horizontally and check if piece moved straight
            return false;
        }
        else if(row == toRow){//pawn can not move in his row on the left or right ... diagonal is still possible since we have to check if there is another piece
            return false;
        }
        else if (color==WHITE) {
            if (toRow < row || toRow > row+2) { // make sure pawn can only move two squares
                return false;
            }

            //check if piece moved through another piece
            int helpRow;
            int helpCol;

            if(col == toCol) {
                for (helpRow = row + 1; helpRow <= toRow; helpRow++) {
                    if (board.getSquare(col, helpRow).getPiece() != null) {
                        System.out.println("something under me");
                        return false;
                    }
                }
            }
            else if(col < toCol) {
                helpRow = row+1;
                helpCol = col+1;
                while (helpCol <= toCol && helpRow <= toRow) {
                    if (board.getSquare(helpCol, helpRow).getPiece() == null) {
                        System.out.println("nothing under and right me");
                        return false;
                    }
                    helpCol++;
                    helpRow++;
                }
            }
            else{
                helpRow = row+1;
                helpCol = col-1;
                while (helpCol >= toCol && helpRow <= toRow) {
                    if (board.getSquare(helpCol, helpRow).getPiece() == null) {
                        System.out.println("nothing under and left me");
                        return false;
                    }
                    helpCol--;
                    helpRow++;
                }
            }

        }else if (color == BLACK){
            if (toRow > row || toRow < row-2) {
                return false;
            }

            //check if piece moved through another piece
            int helpRow;
            int helpCol;

            if(col == toCol) {
                for (helpRow = row - 1; helpRow >= toRow; helpRow--) {
                    if (board.getSquare(col, helpRow).getPiece() != null) {
                        System.out.println("something under me");
                        return false;
                    }
                }
            }
            else if(col < toCol) {
                helpRow = row-1;
                helpCol = col+1;
                while (helpCol <= toCol && helpRow >= toRow) {
                    if (board.getSquare(helpCol, helpRow).getPiece() == null) {
                        System.out.println("nothing above and right me");
                        return false;
                    }
                    helpCol++;
                    helpRow--;
                }
            }
            else{
                helpRow = row-1;
                helpCol = col-1;
                while (helpCol >= toCol && helpRow >= toRow) {
                    if (board.getSquare(helpCol, helpRow).getPiece() == null) {
                        System.out.println("nothing above and left me");
                        return false;
                    }
                    helpCol--;
                    helpRow--;
                }
            }
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


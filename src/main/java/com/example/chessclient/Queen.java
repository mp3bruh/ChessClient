package com.example.chessclient;

import javafx.scene.image.Image;

public class Queen extends ChessPiece{
    int color;
    private ChessBoard board;

    public Queen(int color, int col, int row, ChessBoard board) {
        this.color = color;
        this.col = col;
        this.row = row;
        this.board = board;

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
        int helpRow;
        int helpCol;
        if (toRow > row) {
            if (toCol < col){
                helpRow = row +1;
                helpCol = col -1;
                while (helpCol > toCol && helpRow < toRow){
                    if(board.getSquare(helpCol,helpRow).getPiece() != null){
                        return false;
                    }
                    helpCol --;
                    helpRow ++;
                }
            }
            else if(toCol > col){
                helpRow = row +1;
                helpCol = col +1;
                while (helpCol < toCol && helpRow < toRow){
                    if(board.getSquare(helpCol,helpRow).getPiece() != null){
                        return false;
                    }
                    helpCol ++;
                    helpRow ++;
                }
            }
            else {
                for (helpRow = row + 1; helpRow < toRow; helpRow++) {
                    if (board.getSquare(col, helpRow).getPiece() != null) {
                        return false;
                    }
                }
            }
        }
        else if (toRow < row) {
            if (toCol < col){
                helpRow = row -1;
                helpCol = col -1;
                while (helpCol > toCol && helpRow > toRow){
                    if(board.getSquare(helpCol,helpRow).getPiece() != null){
                        return false;
                    }
                    helpCol --;
                    helpRow --;
                }
            }
            else if(toCol > col){
                helpRow = row -1;
                helpCol = col +1;
                while (helpCol < toCol && helpRow > toRow){
                    if(board.getSquare(helpCol,helpRow).getPiece() != null){
                        return false;
                    }
                    helpCol ++;
                    helpRow --;
                }
            }
            else {
                for (helpRow = row - 1; helpRow > toRow; helpRow--) {
                    if (board.getSquare(col, helpRow).getPiece() != null) {
                        return false;
                    }
                }
            }
        }
        else if(toRow == row){
            if(col > toCol) {
                for (helpCol = col - 1; helpCol > toCol; helpCol--) {
                    if (board.getSquare(helpCol, row).getPiece() != null) {
                        return false;
                    }
                }
            }else{
                for(helpCol = col +1;helpCol < toCol; helpCol++){
                    if(board.getSquare(helpCol,row).getPiece() != null){
                        return false;
                    }
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

    @Override
    public boolean hasMoved() {
        return false;
    }
}

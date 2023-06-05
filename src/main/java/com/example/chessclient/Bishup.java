package com.example.chessclient;

import javafx.scene.image.Image;

public class Bishup extends ChessPiece{
    int color;
    private ChessBoard board;

    public Bishup(int color, int col, int row, ChessBoard board) {
        this.color = color;
        this.col = col;
        this.row = row;
        this.board = board;

        if(color==WHITE){
            piece_img = new Image("bishup_white.png");
        }
        else if(color==BLACK){
            piece_img = new Image("bishup_black.png");
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
        } else if (Math.abs(toRow - row) != Math.abs(toCol - col)) { //check if piece moved same amount of tiles vertically and horizontally
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
                        System.out.println("something under and left me");
                        return false;
                    }
                    helpCol --;
                    helpRow ++;
                }
            }
            else{
                helpRow = row +1;
                helpCol = col +1;
                while (helpCol < toCol && helpRow < toRow){
                    if(board.getSquare(helpCol,helpRow).getPiece() != null){
                        System.out.println("something under and right me");
                        return false;
                    }
                    helpCol ++;
                    helpRow ++;
                }
            }

        }
        else if (toRow < row) {
            if (toCol < col){
                helpRow = row -1;
                helpCol = col -1;
                while (helpCol > toCol && helpRow > toRow){
                    if(board.getSquare(helpCol,helpRow).getPiece() != null){
                        System.out.println("something above and left me");
                        return false;
                    }
                    helpCol --;
                    helpRow --;
                }
            }
            else{
                helpRow = row -1;
                helpCol = col +1;
                while (helpCol < toCol && helpRow > toRow){
                    if(board.getSquare(helpCol,helpRow).getPiece() != null){
                        System.out.println("something above and right me");
                        return false;
                    }
                    helpCol ++;
                    helpRow --;
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

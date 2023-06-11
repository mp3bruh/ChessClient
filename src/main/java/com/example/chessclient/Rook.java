package com.example.chessclient;

import javafx.scene.image.Image;



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
        } else if (row != toRow && col != toCol) { // check if piece moved diagonally
            return false;
        }

        // Check if piece moved through another piece
        int helpRow;
        int helpCol;
        if (toRow > row) {
            for(helpRow = row + 1; helpRow < toRow; helpRow++) {
                if (board.getSquare(col, helpRow).getPiece() != null) {
                    return false;
                }
            }
        } else if (toRow < row) {
            for(helpRow = row - 1; helpRow > toRow; helpRow--) {
                if (board.getSquare(col, helpRow).getPiece() != null) {
                    return false;
                }
            }
        } else if (toCol < col) {
            for(helpCol = col - 1; helpCol > toCol; helpCol--) {
                if (board.getSquare(helpCol, row).getPiece() != null) {
                    return false;
                }
            }
        } else if (toCol > col) {
            for(helpCol = col + 1; helpCol < toCol; helpCol++) {
                if (board.getSquare(helpCol, row).getPiece() != null) {
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

    @Override
    public boolean hasMoved() {
        return false;
    }

    public boolean checkForCastle(int toCol, int toRow) {
        // Check if the move is a castle move
        if (!hasMoved()) {
            if (color == WHITE) {
                if (toRow == 1 && (toCol == 3 || toCol == 7)) {
                    return checkForCastle(toCol, toRow, 1, 8);
                }
            } else if (color == BLACK) {
                if (toRow == 8 && (toCol == 3 || toCol == 7)) {
                    return checkForCastle(toCol, toRow, 8, 1);
                }
            }
        }
        return false;
    }



    private boolean checkForCastle(int toCol, int toRow, int kingCol, int rookCol) {
        int minCol = Math.min(kingCol, toCol);
        int maxCol = Math.max(kingCol, toCol);

        for (int col = minCol + 1; col < maxCol; col++) {
            if (board.getPieceOnSquare(col, toRow) != null) {
                return false;
            }
        }

        ChessPiece rook = board.getPieceOnSquare(rookCol, toRow);
        if (rook instanceof Rook && !rook.hasMoved()) {
            return true;
        }

        return false;
    }
}

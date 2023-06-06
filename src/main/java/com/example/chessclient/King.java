package com.example.chessclient;

import javafx.scene.image.Image;

public class King extends ChessPiece {
    private int color;
    private boolean hasMoved;
    private ChessBoard board;

    public King(int color, int col, int row, ChessBoard board) {
        this.color = color;
        this.col = col;
        this.row = row;
        this.board = board;
        this.hasMoved = false;

        if (color == WHITE) {
            piece_img = new Image("king_white.png");
        } else if (color == BLACK) {
            piece_img = new Image("king_black.png");
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
        } else if (Math.abs(toRow - row) > 1 || Math.abs(toCol - col) > 1) {
            if (isCastlingMove(toCol, toRow)) {
                return true;
            }
            return false;
        }

        // Check if the piece moved through another piece
        // ...

        return true;
    }

    @Override
    public void updateCoordinates(int col, int row) {
        this.col = col;
        this.row = row;
        this.hasMoved = true;
    }

    boolean isCastlingMove(int toCol, int toRow) {
        if (toCol == 3 && toRow == row && !hasMoved) {
            ChessPiece leftRook = board.getPieceOnSquare(1, row);
            if (leftRook instanceof Rook && !leftRook.hasMoved()) {
                if (board.getPieceOnSquare(2, row) == null && board.getPieceOnSquare(3, row) == null) {
                    return true;
                }
            }
        } else if (toCol == 7 && toRow == row && !hasMoved) {
            ChessPiece rightRook = board.getPieceOnSquare(8, row);
            if (rightRook instanceof Rook && !rightRook.hasMoved()) {
                if (board.getPieceOnSquare(6, row) == null && board.getPieceOnSquare(7, row) == null) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }
}

package com.example.chessclient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ChessPiece extends ImageView {
    public static final int PIECE_SIZE = 45;
    protected Image piece_img;
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    protected int row;
    protected int col;
    protected int toRow;
    protected int toCol;
    public abstract int getColor();
    public abstract boolean isMoveToPositionLegal(int col, int row);
    public abstract boolean checkIfLegalMove();
    public abstract void updateCoordinates(int col, int row);
    public abstract boolean hasMoved();
}

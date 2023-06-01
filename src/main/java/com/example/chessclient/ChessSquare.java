package com.example.chessclient;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessSquare extends StackPane {

    protected int row;
    protected int col;
    private ChessBoard board;
    public final static int SQUARE_SIZE = 50;

    private final Rectangle rect = new Rectangle(SQUARE_SIZE,SQUARE_SIZE);
    private final Color defaultColor;

    private ChessPiece piece;

    private boolean isOccupied = false;
    private boolean isClicked = false;



    public ChessSquare(ChessBoard board,Color color, int row, int column) {
        this.row = row;
        this.col = column;

        this.board=board;
        defaultColor = color;
        rect.setFill(defaultColor);
        this.getChildren().add(rect);

        this.setOnMouseClicked(e->{
            setSquareColor(Color.YELLOW);
            isClicked=true;
        });

    }

    public ChessPiece getPiece(){
        return piece;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public void addPiece(ChessPiece piece){
        this.piece = piece;
        this.getChildren().add(piece);
    }

    public void replacePiece(ChessPiece piece){
        this.piece = piece;

        for(Node node: this.getChildren()){
            if(node instanceof ChessPiece){
                node.setVisible(false);
            }
        }

        if(piece != null) {
            this.addPiece(piece);
            piece.setVisible(true);
            piece.updateCoordinates(piece.toCol,piece.toRow);
        }
    }

    public boolean isSquareOccupiedByColor(int color){

        if (this.piece != null) {
            System.out.println(piece.getColor());
            return color == piece.getColor();
        } else {
            return false;
        }

    }


    public boolean isSquareClicked(){
        return isClicked;
    }

    private void setSquareColor(Color color){
        rect.setFill(color);
    }
    public void setToNotCLicked(){
        setSquareColor(defaultColor);
        isClicked=false;
    }
}


package com.example.chessclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class HelloController {

    //
    @FXML
    private Label welcomeText;
    @FXML
    private BorderPane bp;
    private ChessBoard board = new ChessBoard();
    private GameThread gameThread;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;




    @FXML
    protected void onHelloButtonClick(){
        bp.setTop(board);
        board.generateBoard();
        gameThread = new GameThread(board);
        gameThread.start();
    }



    @FXML
    private void sendData() {
        try {
            //socket
            DatagramSocket socket = new DatagramSocket();


            //datagram definieren ->Postkarte
            String payload = "MOnfrecola;5;97;8;LEFT";
            byte buffer[] = payload.getBytes();

            InetAddress remoteIP = InetAddress.getByName("localhost");
            int remotePort = 4567;
            DatagramPacket postkarte = new DatagramPacket(buffer, buffer.length, remoteIP, remotePort);
            //datagramm verschicken
            socket.send(postkarte);
            socket.close();

        } catch (SocketException e) {
            System.out.println("Fehler " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Fehler " + e.getMessage());
        }
    }
}
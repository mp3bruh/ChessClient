package com.example.chessclient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class HelloController {

    public TextField joinRoomTextField;
    public Label createRoomLabel;
    //
    @FXML
    private Label welcomeText;
    @FXML
    private BorderPane bp;
    private ChessBoard board = new ChessBoard();
    private GameThread gameThread;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;


    public void joinRoom() throws IOException {
        if(joinRoomTextField.getText().isEmpty()){
            System.out.println("Keine ID angegeben!");
        }else{
            String roomId = joinRoomTextField.getText();

            DatagramSocket socket = new DatagramSocket();
            String payload = "JOIN_ROOM:" + roomId;
            byte buffer[] = payload.getBytes();

            InetAddress remoteIP = InetAddress.getByName("localhost");
            int remotePort = 6969;
            DatagramPacket postkarte = new DatagramPacket(buffer, buffer.length, remoteIP, remotePort);

            //senden
            socket.send(postkarte);
            socket.close();

            //socket nicht schließen --- gameloop
        }
    }

    @FXML
    private void createRoom() {
        new Thread(() -> {
            try {
                // socket
                DatagramSocket socket = new DatagramSocket();

                // datagram definieren -> Postkarte
                String payload = "CREATE_ROOM";
                byte buffer[] = payload.getBytes();

                InetAddress remoteIP = InetAddress.getByName("localhost");
                int remotePort = 6969;
                DatagramPacket postkarte = new DatagramPacket(buffer, buffer.length, remoteIP, remotePort);

                // datagramm verschicken
                socket.send(postkarte);

                // Empfangen der Room-ID vom Server
                buffer = new byte[1024];
                postkarte = new DatagramPacket(buffer, buffer.length);
                socket.receive(postkarte);

                // Ausgabe der Room-ID
                String roomId = new String(postkarte.getData(), 0, postkarte.getLength());
                // Updating the UI components must be done in the JavaFX Application Thread
                Platform.runLater(() -> createRoomLabel.setText(roomId));
                System.out.println("RoomID: " + roomId);

                socket.close();

            } catch (IOException e) {
                System.out.println("Fehler " + e.getMessage());
            }
        }).start();
    }



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
            int remotePort = 6969;
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
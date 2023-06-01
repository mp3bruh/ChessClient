module com.example.chessclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chessclient to javafx.fxml;
    exports com.example.chessclient;
}
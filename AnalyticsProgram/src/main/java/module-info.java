module com.example.analyticsprogram {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.analyticsprogram to javafx.fxml;
    exports com.example.analyticsprogram;
}
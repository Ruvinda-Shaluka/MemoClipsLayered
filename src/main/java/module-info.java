module lk.ijse.memoclipsinlayered {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;
    requires jakarta.mail;


    opens lk.ijse.memoclipsinlayered.controller to javafx.fxml;
    opens lk.ijse.memoclipsinlayered to javafx.graphics;
    opens lk.ijse.memoclipsinlayered.view.tdm to javafx.base;

    exports lk.ijse.memoclipsinlayered;
    exports lk.ijse.memoclipsinlayered.controller;
}
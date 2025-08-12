module lk.ijse.memoclipsinlayered {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.memoclipsinlayered to javafx.fxml;
    exports lk.ijse.memoclipsinlayered;
}
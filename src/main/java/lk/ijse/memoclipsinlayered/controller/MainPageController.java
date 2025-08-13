package lk.ijse.memoclipsinlayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainPageController {

    @FXML
    private AnchorPane ancMainConnt;

    @FXML
    void btnGoSignInPageOnAction(ActionEvent event) throws IOException {
        ancMainConnt.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SignInPage.fxml"));
        ancMainConnt.getChildren().add(load);
    }
}

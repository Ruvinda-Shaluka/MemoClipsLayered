package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.PhotoSessionBO;
import lk.ijse.memoclipsinlayered.dto.PhotoSessionDto;
import lk.ijse.memoclipsinlayered.view.tdm.PhotoSessionTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhotoSessionPageController implements Initializable {
    public Label lblSessionId;
    public TextField txtBookingId;
    public TextField txtPhotographerId;
    public TextField txtSessionType;
    public TextField txtDuration;


    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<PhotoSessionTm> tblPhotoSessions;
    public TableColumn<PhotoSessionTm,String> colSessionId;
    public TableColumn<PhotoSessionTm,String> colBookingId;
    public TableColumn<PhotoSessionTm,String> colPhotographerId;
    public TableColumn<PhotoSessionTm,String> colSessionType;
    public TableColumn<PhotoSessionTm,String> colDuration;

    private final PhotoSessionBO photoSessionModel = (PhotoSessionBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.PHOTO_SESSION);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colPhotographerId.setCellValueFactory(new PropertyValueFactory<>("photographerId"));
        colSessionType.setCellValueFactory(new PropertyValueFactory<>("sessionType"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        try {
            resetpage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }



    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblPhotoSessions.setItems(FXCollections.observableArrayList(
                photoSessionModel.getAllPhotoSession().stream()
                        .map(photoSessionDto -> new PhotoSessionTm(
                                photoSessionDto.getSessionId(),
                                photoSessionDto.getBookingId(),
                                photoSessionDto.getPhotographerId(),
                                photoSessionDto.getSessionType(),
                                photoSessionDto.getDuration()
                        )).toList()
        ));
    }

    private void resetpage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtBookingId.setText(null);
            txtPhotographerId.setText(null);
            txtSessionType.setText(null);
            txtDuration.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = photoSessionModel.generateNewPhotoSessionId();
        lblSessionId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String sessionId = lblSessionId.getText();
        String bookingId = txtBookingId.getText();
        String photographerId = txtPhotographerId.getText();
        String sessionType = txtSessionType.getText();
        String duration = txtDuration.getText();


        try {
            boolean isSaved=photoSessionModel.savePhotoSession(new PhotoSessionDto(sessionId,bookingId,photographerId,sessionType,duration));
            if(isSaved){
                resetpage();
                new Alert(Alert.AlertType.INFORMATION,"Photo Session Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Photo Session Saved Failed").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String sessionId = lblSessionId.getText();
        String bookingId = txtBookingId.getText();
        String photographerId = txtPhotographerId.getText();
        String sessionType = txtSessionType.getText();
        String duration = txtDuration.getText();

        try {
            boolean isUpdated=photoSessionModel.updatePhotoSession(new PhotoSessionDto(sessionId,bookingId,photographerId,sessionType,duration));
            if(isUpdated){
                resetpage();
                new Alert(Alert.AlertType.INFORMATION,"Session Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Session Updated Failed").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure?",
                ButtonType.YES,ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES){
            String sessionId = lblSessionId.getText();
            try {
                boolean isDeleted=photoSessionModel.deletePhotoSession(sessionId);
                if(isDeleted){
                    resetpage();
                    new Alert(Alert.AlertType.INFORMATION,"Session Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Session Deleted Failed").show();
                }

            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetpage();
    }

    public void OnClickTable(MouseEvent mouseEvent) {
        PhotoSessionTm selectedSession = tblPhotoSessions.getSelectionModel().getSelectedItem();
        if (selectedSession != null) {
            lblSessionId.setText(selectedSession.getSessionId());
            txtBookingId.setText(selectedSession.getBookingId());
            txtPhotographerId.setText(selectedSession.getPhotographerId());
            txtSessionType.setText(selectedSession.getSessionType());
            txtDuration.setText(selectedSession.getDuration());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

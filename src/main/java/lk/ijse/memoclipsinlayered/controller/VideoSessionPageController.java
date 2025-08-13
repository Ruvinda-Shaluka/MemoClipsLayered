package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.VideoSessionBO;
import lk.ijse.memoclipsinlayered.dto.VideoSessionDto;
import lk.ijse.memoclipsinlayered.view.tdm.VideoSessionTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class VideoSessionPageController implements Initializable {
    public Label lblVideoId;
    public TextField txtBookingId;
    public TextField txtVideographerId;
    public TextField txtDuration;
    public TextField txtFormat;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<VideoSessionTm> tblVideoSession;
    public TableColumn<VideoSessionTm,String> colVideoId;
    public TableColumn<VideoSessionTm,String> colBookingId;
    public TableColumn<VideoSessionTm,String> colDuration;
    public TableColumn<VideoSessionTm,String> colFormat;
    public TableColumn<VideoSessionTm,String> colVideographerId;

    private final VideoSessionBO videoSessionModel = (VideoSessionBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.VIDEO_SESSION);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colVideoId.setCellValueFactory(new PropertyValueFactory<>("videoId"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFormat.setCellValueFactory(new PropertyValueFactory<>("format"));
        colVideographerId.setCellValueFactory(new PropertyValueFactory<>("videographerId"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = videoSessionModel.generateNewVideoSessionId();
        lblVideoId.setText(nextId);
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblVideoSession.setItems(FXCollections.observableArrayList(
                videoSessionModel.getAllVideoSession().stream()
                        .map(videoSessionDto -> new VideoSessionTm(
                                videoSessionDto.getVideoId(),
                                videoSessionDto.getBookingId(),
                                videoSessionDto.getDuration(),
                                videoSessionDto.getFormat(),
                                videoSessionDto.getVideographerId()
                        )).toList()
        ));
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String videoId =lblVideoId.getText();
        String bookingId =txtBookingId.getText();
        String duration =txtDuration.getText();
        String format =txtFormat.getText();
        String videographerId =txtVideographerId.getText();

        try {
            boolean isSaved = videoSessionModel.saveVideoSession(new VideoSessionDto(
                    videoId,
                    bookingId,
                    duration,
                    format,
                    videographerId
            ));
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Video Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String videoId =lblVideoId.getText();
        String bookingId =txtBookingId.getText();
        String duration =txtDuration.getText();
        String format =txtFormat.getText();
        String videographerId =txtVideographerId.getText();

        try {
            boolean isUpdated = videoSessionModel.updateVideoSession(new VideoSessionDto(
                    videoId,
                    bookingId,
                    duration,
                    format,
                    videographerId
            ));
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Video Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }



    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }
    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtBookingId.setText(null);
            txtDuration.setText(null);
            txtFormat.setText(null);
            txtVideographerId.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void OnClickTable(MouseEvent mouseEvent) {
        VideoSessionTm selectedVideoSession =  tblVideoSession.getSelectionModel().getSelectedItem();
        if(selectedVideoSession != null){
            lblVideoId.setText(selectedVideoSession.getVideoId());
            txtBookingId.setText(selectedVideoSession.getBookingId());
            txtDuration.setText(selectedVideoSession.getDuration());
            txtFormat.setText(selectedVideoSession.getFormat());
            txtVideographerId.setText(selectedVideoSession.getVideographerId());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES , ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String videoSessionId = lblVideoId.getText();
            try {
                boolean isDeleted = videoSessionModel.deleteVideoSession(videoSessionId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Video Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }
    }

}

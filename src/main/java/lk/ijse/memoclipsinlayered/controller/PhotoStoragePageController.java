package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.PhotoStorageBO;
import lk.ijse.memoclipsinlayered.dto.PhotoStorageDto;
import lk.ijse.memoclipsinlayered.view.tdm.PhotoStorageTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhotoStoragePageController implements Initializable {
    public Label lblPhotoId;
    public TextField txtSessionId;
    public TextField txtUploadDate;
    public TextField txtUploadTime;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<PhotoStorageTm> tblPhotoStorage;
    public TableColumn<PhotoStorageTm,String> colPhotoId;
    public TableColumn<PhotoStorageTm,String> colSessionId;
    public TableColumn<PhotoStorageTm,String> colUploadDate;
    public TableColumn<PhotoStorageTm,String> colUploadTime;

    private final PhotoStorageBO photoStorageModel =(PhotoStorageBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.PHOTO_STORAGE);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPhotoId.setCellValueFactory(new PropertyValueFactory<>("photoId"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colUploadDate.setCellValueFactory(new PropertyValueFactory<>("uploadDate"));
        colUploadTime.setCellValueFactory(new PropertyValueFactory<>("uploadTime"));
        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }



    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblPhotoStorage.setItems(FXCollections.observableArrayList(
                photoStorageModel.getAllPhotoStorage().stream()
                        .map(photoStorageDto -> new PhotoStorageTm(
                                photoStorageDto.getPhotoId(),
                                photoStorageDto.getSessionId(),
                                photoStorageDto.getUploadDate(),
                                photoStorageDto.getUploadTime()
                        )).toList()
        ));
    }
    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtSessionId.setText(null);
            txtUploadDate.setText(null);
            txtUploadTime.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId= photoStorageModel.generateNewPhotoStorageId();
        lblPhotoId.setText(nextId);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String photoId = lblPhotoId.getText();
        String sessionId = txtSessionId.getText();
        String uploadDate = txtUploadDate.getText();
        String uploadTime = txtUploadTime.getText();


        try {
            boolean isSaved=photoStorageModel.savePhotoStorage(new PhotoStorageDto(photoId, sessionId, uploadDate, uploadTime));
            if(isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Photo Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Photo Saved Failed").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String photoId = lblPhotoId.getText();
        String sessionId = txtSessionId.getText();
        String uploadDate = txtUploadDate.getText();
        String uploadTime = txtUploadTime.getText();

        try {
            boolean isUpdated=photoStorageModel.updatePhotoStorage(new PhotoStorageDto(photoId, sessionId, uploadDate, uploadTime));
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer Updated Failed").show();
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
            String photoId = lblPhotoId.getText();
            try {
                boolean isDeleted=photoStorageModel.deletePhotoStorage(photoId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Photo Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Photo Deleted Failed").show();
                }

            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void OnClickTable(MouseEvent mouseEvent) {
        PhotoStorageTm  selectedPhoto=tblPhotoStorage.getSelectionModel().getSelectedItem();
        if (selectedPhoto != null) {
            lblPhotoId.setText(selectedPhoto.getPhotoId());
            txtSessionId.setText(selectedPhoto.getSessionId());
            txtUploadDate.setText(selectedPhoto.getUploadDate());
            txtUploadTime.setText(selectedPhoto.getUploadTime());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

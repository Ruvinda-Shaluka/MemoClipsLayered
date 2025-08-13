package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.PhotoAlbumBO;
import lk.ijse.memoclipsinlayered.dto.PhotoAlbumDto;
import lk.ijse.memoclipsinlayered.view.tdm.PhotoAlbumTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhotoAlbumPageController implements Initializable {
    public Label lblAlbumId;
    public TextField txtBookingId;
    public TextField txtAlbumType;
    public TextField txtPrice;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<PhotoAlbumTm> tblPhotoAlbum;
    public TableColumn<PhotoAlbumTm,String> colAlbumId;
    public TableColumn<PhotoAlbumTm,String> colBookingId;
    public TableColumn<PhotoAlbumTm,String> colAlbumType;
    public TableColumn<PhotoAlbumTm,String> colPrice;

    private final PhotoAlbumBO photoAlbumModel = (PhotoAlbumBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.PHOTO_ALBUM);

    public void initialize(URL url, ResourceBundle resourcesBundle) {

        colAlbumId.setCellValueFactory(new PropertyValueFactory<>("albumId"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colAlbumType.setCellValueFactory(new PropertyValueFactory<>("albumType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }

    public void loadTableData () throws SQLException, ClassNotFoundException {
        tblPhotoAlbum.setItems(FXCollections.observableArrayList(
                photoAlbumModel.getAllPhotoAlbum().stream()
                        .map(photoAlbumDto -> new PhotoAlbumTm(
                                photoAlbumDto.getAlbumId(),
                                photoAlbumDto.getBookingId(),
                                photoAlbumDto.getAlbumType(),
                                photoAlbumDto.getPrice()

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

            txtBookingId.setText(null);
            txtAlbumType.setText(null);
            txtPrice.setText(null);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = photoAlbumModel.generateNewPhotoAlbumId();
        lblAlbumId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String photoAlbumId = txtBookingId.getText();
        String bookingId = txtAlbumType.getText();
        String albumType = txtAlbumType.getText();
        String price = txtPrice.getText();


        try {
            boolean isSaved = photoAlbumModel.savePhotoAlbum(new PhotoAlbumDto(photoAlbumId, bookingId, albumType, price));
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String photoAlbumId = lblAlbumId.getText();
        String bookingId = txtBookingId.getText();
        String albumType = txtAlbumType.getText();
        String price = txtPrice.getText();

        try {
            boolean isUpdated=photoAlbumModel.updatePhotoAlbum(new PhotoAlbumDto(photoAlbumId, bookingId, albumType, price));
            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
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
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String AlbumId = lblAlbumId.getText();
            try {
                boolean isDeleted = photoAlbumModel.deletePhotoAlbum(AlbumId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Album Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Error").show();
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
        PhotoAlbumTm selectedItem = tblPhotoAlbum.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblAlbumId.setText(selectedItem.getAlbumId());
            txtBookingId.setText(selectedItem.getBookingId());
            txtAlbumType.setText(selectedItem.getAlbumType());
            txtPrice.setText(selectedItem.getPrice());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

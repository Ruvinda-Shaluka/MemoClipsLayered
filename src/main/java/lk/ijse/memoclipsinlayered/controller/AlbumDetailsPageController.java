package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.AlbumDetailsBO;
import lk.ijse.memoclipsinlayered.dto.AlbumDetailsDto;
import lk.ijse.memoclipsinlayered.view.tdm.AlbumDetailsTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlbumDetailsPageController implements Initializable {
    public Label lblDetailId;
    public TextField txtAlbumId;
    public TextField txtNumberOfphotos;
    public TextField txtCoverType;
    public TextField txtSize;
    public TextField txtPaperQuantity;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<AlbumDetailsTm> tableAlbumDetails;
    public TableColumn<AlbumDetailsTm,String> colDetailId;
    public TableColumn<AlbumDetailsTm,String> colAlbumId;
    public TableColumn<AlbumDetailsTm,String> numberOfPhotos;
    public TableColumn<AlbumDetailsTm,String> coverType;
    public TableColumn<AlbumDetailsTm,String> colSize;
    public TableColumn<AlbumDetailsTm,String> colPaperQuantity;

    private AlbumDetailsBO albumDetailsModel = (AlbumDetailsBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ALBUM_DETAIL);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDetailId.setCellValueFactory(new PropertyValueFactory<>("detailId"));
        colAlbumId.setCellValueFactory(new PropertyValueFactory<>("albumId"));
        numberOfPhotos.setCellValueFactory(new PropertyValueFactory<>("numberOfPhotos"));
        coverType.setCellValueFactory(new PropertyValueFactory<>("coverType"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPaperQuantity.setCellValueFactory(new PropertyValueFactory<>("paperQuantity"));

        try {
            resetpage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tableAlbumDetails.setItems(FXCollections.observableArrayList(
                albumDetailsModel.getAllAlbumDetails().stream()
                        .map(albumDetailsDto -> new AlbumDetailsTm(
                                albumDetailsDto.getDetailId(),
                                albumDetailsDto.getAlbumId(),
                                albumDetailsDto.getSize(),
                                albumDetailsDto.getCoverType(),
                                albumDetailsDto.getPaperQuantity(),
                                albumDetailsDto.getNumberOfPhotos()
                        )).toList()
        ));
    }

    public void resetpage(){
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

            txtAlbumId.setText(null);
            txtNumberOfphotos.setText(null);
            txtCoverType.setText(null);
            txtSize.setText(null);
            txtPaperQuantity.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = albumDetailsModel.generateNewAlbumDetailsId();
        lblDetailId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String detailId = txtAlbumId.getText();
        String albumId = txtAlbumId.getText();
        String size = txtSize.getText();
        String coverType = txtCoverType.getText();
        String paperQuantity = txtPaperQuantity.getText();
        String numberOfPhotos = txtNumberOfphotos.getText();

        try {
            boolean isSaved = albumDetailsModel.saveAlbumDetails(new AlbumDetailsDto(detailId,
                    albumId,
                    size,
                    coverType,
                    paperQuantity,
                    numberOfPhotos
            ));
            if(isSaved){
                resetpage();
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
        String detailId = txtAlbumId.getText();
        String albumId = txtAlbumId.getText();
        String size = txtSize.getText();
        String coverType = txtCoverType.getText();
        String paperQuantity = txtPaperQuantity.getText();
        String numberOfPhotos = txtNumberOfphotos.getText();


        try {
            boolean isupdated = albumDetailsModel.updateAlbumDetails(new AlbumDetailsDto(detailId,
                    albumId,
                    size,
                    coverType,
                    paperQuantity,
                    numberOfPhotos
            ));
            if(isupdated){
                resetpage();
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
            String DetailId = lblDetailId.getText();
            try {
                boolean isDeleted = albumDetailsModel.deleteAlbumDetails(DetailId);
                if(isDeleted){
                    resetpage();
                    new Alert(Alert.AlertType.INFORMATION,"Detail Deleted").show();
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
        resetpage();
    }

    public void OnClickTable(MouseEvent mouseEvent) {
        AlbumDetailsTm selectedAlbumDetail = tableAlbumDetails.getSelectionModel().getSelectedItem();
        if (selectedAlbumDetail != null) {
            lblDetailId.setText(selectedAlbumDetail.getDetailId());
            txtAlbumId.setText(selectedAlbumDetail.getAlbumId());
            txtSize.setText(selectedAlbumDetail.getSize());
            txtCoverType.setText(selectedAlbumDetail.getCoverType());
            txtPaperQuantity.setText(selectedAlbumDetail.getPaperQuantity());
            txtNumberOfphotos.setText(selectedAlbumDetail.getNumberOfPhotos());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

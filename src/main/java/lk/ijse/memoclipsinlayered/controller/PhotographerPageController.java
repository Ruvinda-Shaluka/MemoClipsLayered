package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.PhotographerBO;
import lk.ijse.memoclipsinlayered.dto.PhotographerDto;
import lk.ijse.memoclipsinlayered.view.tdm.PhotographerTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhotographerPageController implements Initializable {

    public Label lblPhotographerId;
    public TextField txtName;
    public TextField txtSpeciality;
    public TextField txtContact;
    public TextField txtAvailability;

    public Button btnUpdate;
    public Button btnSave;
    public Button btnDelete;
    public Button btnReset;

    public TableView<PhotographerTm> tblPhotographer;
    public TableColumn<PhotographerTm,String> colPhotographerId;
    public TableColumn<PhotographerTm,String> colName;
    public TableColumn<PhotographerTm,String> colSpeciality;
    public TableColumn<PhotographerTm,String> colContact;
    public TableColumn<PhotographerTm,String> colAvailability;

    private final PhotographerBO photographerModel = (PhotographerBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.PHOTOGRAPHER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPhotographerId.setCellValueFactory(new PropertyValueFactory<>("photographerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }



    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblPhotographer.setItems(FXCollections.observableArrayList(
                photographerModel.getAllPhotographer().stream()
                        .map(photographerDto -> new PhotographerTm(
                                photographerDto.getPhotographerId(),
                                photographerDto.getName(),
                                photographerDto.getSpeciality(),
                                photographerDto.getContact(),
                                photographerDto.getAvailability()
                        )).toList()



        ));
    }
    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtName.setText(null);
            txtSpeciality.setText(null);
            txtContact.setText(null);
            txtAvailability.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = photographerModel.generateNewPhotographerId();
        lblPhotographerId.setText(nextId);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String photographerId = lblPhotographerId.getText();
        String name = txtName.getText();
        String speciality = txtSpeciality.getText();
        String contact = txtContact.getText();
        String availability = txtAvailability.getText();

        try {
            boolean isSaved=photographerModel.savePhotographer(new PhotographerDto(photographerId, name, speciality, contact, availability));
            if(isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        String photographerId = lblPhotographerId.getText();
        String name = txtName.getText();
        String speciality = txtSpeciality.getText();
        String contact = txtContact.getText();
        String availability = txtAvailability.getText();

        try {
            boolean isUpdated=photographerModel.updatePhotographer(new PhotographerDto(photographerId, name, speciality, contact, availability));
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Success").show();
            }else{
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
        if(result.isPresent() && result.get() == ButtonType.YES){
            String photographerId = lblPhotographerId.getText();
            try {
                boolean isDeleted=photographerModel.deletePhotographer(photographerId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Photographer Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Photographer Deleted Failed").show();
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
        PhotographerTm selectedPhotographer = tblPhotographer.getSelectionModel().getSelectedItem();
        if (selectedPhotographer != null) {
            lblPhotographerId.setText(selectedPhotographer.getPhotographerId());
            txtName.setText(selectedPhotographer.getName());
            txtSpeciality.setText(selectedPhotographer.getSpeciality());
            txtContact.setText(selectedPhotographer.getContact());
            txtAvailability.setText(selectedPhotographer.getAvailability());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
}

package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.VideographerBO;
import lk.ijse.memoclipsinlayered.dto.VideographerDto;
import lk.ijse.memoclipsinlayered.view.tdm.VideographerTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class VideographerPageController implements Initializable {
    public Label lblVideographerId;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtAvailability;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<VideographerTm> tblVideographer;
    public TableColumn<VideographerTm,String> colVideographerId;
    public TableColumn<VideographerTm,String> colName;
    public TableColumn<VideographerTm,String> colContact;
    public TableColumn<VideographerTm,String> colAvailability;

    private final VideographerBO videographerModel = (VideographerBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.VIDEOGRAPHER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colVideographerId.setCellValueFactory(new PropertyValueFactory<>("videographerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
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
        tblVideographer.setItems(FXCollections.observableArrayList(
                videographerModel.getAllVideographer().stream()
                        .map(videographerDto -> new VideographerTm(
                                videographerDto.getVideographerId(),
                                videographerDto.getName(),
                                videographerDto.getContact(),
                                videographerDto.getAvailability()
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

            txtName.setText(null);
            txtContact.setText(null);
            txtAvailability.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId=videographerModel.generateNewVideographerId();
        lblVideographerId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String videographerId=lblVideographerId.getText();
        String name=txtName.getText();
        String contact=txtContact.getText();
        String availability=txtAvailability.getText();

        try {
            boolean isSaved=videographerModel.saveVideographer(new VideographerDto(videographerId,name,contact,availability));
            if(isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Videographer Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Videographer Saved Failed").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String videographerId=lblVideographerId.getText();
        String name=txtName.getText();
        String contact=txtContact.getText();
        String availability=txtAvailability.getText();

        try {
            boolean isUpdated=videographerModel.updateVideographer(new VideographerDto(videographerId,name,contact,availability));
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Videographer Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Videographer Updated Failed").show();
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
            String videographerId = lblVideographerId.getText();
            try {
                boolean isDeleted=videographerModel.deleteVideographer(videographerId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Customer Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Customer Deleted Failed").show();
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
        VideographerTm selectedVideographer=(VideographerTm) tblVideographer.getSelectionModel().getSelectedItem();
        if(selectedVideographer!=null){
            lblVideographerId.setText(selectedVideographer.getVideographerId());
            txtName.setText(selectedVideographer.getName());
            txtContact.setText(selectedVideographer.getContact());
            txtAvailability.setText(selectedVideographer.getAvailability());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

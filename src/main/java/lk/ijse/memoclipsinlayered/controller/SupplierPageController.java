package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.SupplierBO;
import lk.ijse.memoclipsinlayered.dto.SupplierDto;
import lk.ijse.memoclipsinlayered.view.tdm.SupplierTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierPageController implements Initializable {
    public Label lblSupplierId;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtSupplyQuantity;


    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<SupplierTm> tblSupplier;
    public TableColumn<SupplierTm,String> colSupplierId;
    public TableColumn<SupplierTm,String> colName;
    public TableColumn<SupplierTm,String> colContact;
    public TableColumn<SupplierTm,String> colSupplierQuantity;

    private final SupplierBO supplierModel = (SupplierBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSupplierQuantity.setCellValueFactory(new PropertyValueFactory<>("supplyQuantity"));
        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblSupplier.setItems(FXCollections.observableArrayList(
                supplierModel.getAllSupplier().stream()
                        .map(supplierDto -> new SupplierTm(
                                supplierDto.getSupplierId(),
                                supplierDto.getName(),
                                supplierDto.getContact(),
                                supplierDto.getSupplyQuantity()
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
            txtSupplyQuantity.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = supplierModel.generateNewSupplierId();
        lblSupplierId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String supplierId = lblSupplierId.getText();
        String supplierName = txtName.getText();
        String supplierQuantity = txtSupplyQuantity.getText();
        String contact = txtContact.getText();

        try {
            boolean isSaved=supplierModel.saveSupplier(new SupplierDto(supplierId, supplierName, supplierQuantity, contact));
            if(isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Supplier Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier Saved Failed").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String supplierId = lblSupplierId.getText();
        String supplierName = txtName.getText();
        String supplierQuantity = txtSupplyQuantity.getText();
        String contact = txtContact.getText();

        try {
            boolean isUpdated=supplierModel.updateSupplier(new SupplierDto(supplierId, supplierName, supplierQuantity, contact));
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Supplier Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier Updated Failed").show();
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
            String supplierId = lblSupplierId.getText();
            try {
                boolean isDeleted=supplierModel.deleteSupplier(supplierId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Supplier Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Supplier Deleted Failed").show();
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
        SupplierTm selectedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            lblSupplierId.setText(selectedSupplier.getSupplierId());
            txtName.setText(selectedSupplier.getName());
            txtContact.setText(selectedSupplier.getContact());
            txtSupplyQuantity.setText(selectedSupplier.getSupplyQuantity());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

        }
    }
}

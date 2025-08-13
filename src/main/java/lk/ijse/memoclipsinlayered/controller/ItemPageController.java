package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.ItemBO;
import lk.ijse.memoclipsinlayered.dto.ItemDto;
import lk.ijse.memoclipsinlayered.view.tdm.ItemTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemPageController implements Initializable {
    public Label lblItemId;
    public TextField txtName;
    public TextField txtQuantity;
    public TextField txtLastUpdateDate;
    public TextField txtSupplierId;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<ItemTm> tblItem;
    public TableColumn<ItemTm,String> colItemId;
    public TableColumn<ItemTm,String> colName;
    public TableColumn<ItemTm,String> colQuantity;
    public TableColumn<ItemTm,String> colLastUpdateDate;
    public TableColumn<ItemTm,String> colSupplierId;

    private final ItemBO itemModel = (ItemBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ITEM);

    public void initialize (URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colLastUpdateDate.setCellValueFactory(new PropertyValueFactory<>("lastUpdateDate"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblItem.setItems(FXCollections.observableArrayList(
                itemModel.getAllItem().stream()
                        .map(itemDto -> new ItemTm(
                                itemDto.getItemId(),
                                itemDto.getItemName(),
                                itemDto.getQuantity(),
                                itemDto.getLastUpdateDate(),
                                itemDto.getSupplierId()
                        )).toList()
        ));
    }

    private void resetPage(){
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtName.setText(null);
            txtQuantity.setText(null);
            txtLastUpdateDate.setText(null);
            txtSupplierId.setText(null);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = itemModel.generateNewItemId();
        lblItemId.setText(nextId);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String ItemId = lblItemId.getText();
        String ItemName = txtName.getText();
        String Quantity = txtQuantity.getText();
        String LastUpdateDate = txtLastUpdateDate.getText();
        String SupplierId = txtSupplierId.getText();

        try {
            boolean isSaved = itemModel.saveItem(new ItemDto(ItemId,ItemName,Quantity,LastUpdateDate,SupplierId));
            if(isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Item Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String ItemId = lblItemId.getText();
        String ItemName = txtName.getText();
        String Quantity = txtQuantity.getText();
        String LastUpdateDate = txtLastUpdateDate.getText();
        String SupplierId = txtSupplierId.getText();


        try {
            boolean isUpdated=itemModel.updateItem(new ItemDto(ItemId,ItemName,Quantity,LastUpdateDate,SupplierId));
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Item Updated").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();

        }
    }

    public void btnDeleteonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure?",
                ButtonType.YES,ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String ItemId = lblItemId.getText();
            try {
                boolean isDeleted = itemModel.deleteItem(ItemId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Item Deleted").show();
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
        ItemTm selectedItem = tblItem.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblItemId.setText(selectedItem.getItemId());
            txtName.setText(selectedItem.getItemName());
            txtQuantity.setText(String.valueOf(selectedItem.getQuantity()));
            txtLastUpdateDate.setText(selectedItem.getLastUpdateDate());
            txtSupplierId.setText(selectedItem.getSupplierId());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

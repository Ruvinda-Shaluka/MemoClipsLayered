package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.CustomerBO;
import lk.ijse.memoclipsinlayered.dto.CustomerDto;
import lk.ijse.memoclipsinlayered.view.tdm.CustomerTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {


    public Label lblCustomerId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmail;
    public TextField txtAdminId;

    public Button btnSve;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<CustomerTm> tblCustomer;
    public TableColumn<CustomerTm , String> colId;
    public TableColumn<CustomerTm , String> colName;
    public TableColumn<CustomerTm , String> colContact;
    public TableColumn<CustomerTm, String> colAddress;
    public TableColumn<CustomerTm , String> colEmail;
    public TableColumn<CustomerTm , String> colAdminId;

    private final CustomerBO customerModel = (CustomerBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdminId.setCellValueFactory(new PropertyValueFactory<>("adminId"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblCustomer.setItems(FXCollections.observableArrayList(
                customerModel.getAllCustomer().stream()
                        .map(customerDto -> new CustomerTm(
                                customerDto.getCustomerId(),
                                customerDto.getCustomerName(),
                                customerDto.getContactNo(),
                                customerDto.getAddress(),
                                customerDto.getEmail(),
                                customerDto.getAdminId()
                        )).toList()
        ));
    }
    private void resetPage(){
        try {
            loadTableData();
            loadNextId();

            btnSve.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtName.setText(null);
            txtContact.setText(null);
            txtAddress.setText(null);
            txtEmail.setText(null);
            txtAdminId.setText(null);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = customerModel.generateNewCustomerId();
        lblCustomerId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String adminId = txtAdminId.getText();


        try {
            boolean isSaved=customerModel.saveCustomer(new CustomerDto(customerId,name,contact,address,email,adminId));
            if(isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Customer Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer Saved Failed").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String adminId = txtAdminId.getText();


        try {
            boolean isUpdated=customerModel.updateCustomer(new CustomerDto(customerId,name,contact,address,email,adminId));
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
            String customerId = lblCustomerId.getText();
            try {
                boolean isDeleted=customerModel.deleteCustomer(customerId);
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


    public void onClickTable(MouseEvent mouseEvent) {
        CustomerTm selectedCustomer =  tblCustomer.getSelectionModel().getSelectedItem();
        if(selectedCustomer!=null){
            lblCustomerId.setText(selectedCustomer.getCustomerId());
            txtName.setText(selectedCustomer.getCustomerName());
            txtContact.setText(selectedCustomer.getContactNo());
            txtAddress.setText(selectedCustomer.getAddress());
            txtEmail.setText(selectedCustomer.getEmail());
            txtAdminId.setText(selectedCustomer.getAdminId());

            btnSve.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.AdminBO;
import lk.ijse.memoclipsinlayered.dto.AdminDto;
import lk.ijse.memoclipsinlayered.view.tdm.AdminTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    public Label lblAdminId;
    public TextField txtName;
    public TextField txtRole;
    public PasswordField txtPassword;

    public TableView<AdminTm> tblAdmin;
    public TableColumn<AdminTm , String> colId;
    public TableColumn<AdminTm , String> colName;
    public TableColumn<AdminTm , String> colPassword;
    public TableColumn<AdminTm, String> colRole;
    public TableColumn<AdminTm , String> colEmail;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ADMIN);

    private final String userNamePattern = "^[A-Za-z0-9_ ]{3,}$";
    private final String passwordPattern = "^[A-Za-z0-9@#$%^&+=]{6,}$";
    public TextField txtEmail;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblAdmin.setItems(FXCollections.observableList(
                adminBO.getAllAdmin().stream()
                        .map(adminDto -> new AdminTm(
                                adminDto.getAdminId(),
                                adminDto.getName(),
                                adminDto.getPassword(),
                                adminDto.getRole(),
                                adminDto.getEmail()
                        )).toList()
        ));
    }

    private void resetPage() throws SQLException, ClassNotFoundException {
        loadTableData();
        loadNextId();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText(null);
        txtPassword.setText(null);
        txtRole.setText(null);
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = adminBO.generateNewAdminId();
        lblAdminId.setText(nextId);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String adminId = lblAdminId.getText();
        String adminName = txtName.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();
        String email = txtEmail.getText();

        boolean isValidName = adminName.matches(userNamePattern);
        boolean isValidPassword = password.matches(passwordPattern);
        boolean isValidRole = role.matches(role);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: #7367F0");
        txtRole.setStyle(txtRole.getStyle() + ";-fx-border-color: #7367F0");

        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidPassword) txtPassword.setStyle("-fx-border-color: red;");
        if (!isValidRole) txtRole.setStyle("-fx-border-color: red;");


        if (isValidName && isValidPassword && isValidRole){
            try {
                boolean isSaved = adminBO.saveAdmin(new AdminDto(
                        adminId,
                        adminName,
                        password,
                        role,
                        email
                ));

                if (isSaved){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Successfully Saved!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Saved Failed").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String adminId = lblAdminId.getText();
        String adminName = txtName.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();
        String email = txtEmail.getText();

        boolean isValidName = adminName.matches(userNamePattern);
        boolean isValidPassword = password.matches(passwordPattern);
        boolean isValidRole = role.matches(role);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: #7367F0");
        txtRole.setStyle(txtRole.getStyle() + ";-fx-border-color: #7367F0");

        if (!isValidName) txtName.setStyle("-fx-border-color: red;");
        if (!isValidPassword) txtPassword.setStyle("-fx-border-color: red;");
        if (!isValidRole) txtRole.setStyle("-fx-border-color: red;");

        if (isValidName && isValidPassword && isValidRole) {

            try {
                boolean isUpdated = adminBO.updateAdmin(new AdminDto(
                        adminId,
                        adminName,
                        password,
                        role,
                        email
                ));

                if (isUpdated) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Updated!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update Failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this admin?",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES){
            String adminId = lblAdminId.getText();
            try {
                boolean isDeleted = adminBO.deleteAdmin(adminId);
                if (isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Delete Failed").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        resetPage();
    }


    public void onClickTable(MouseEvent mouseEvent) {
        AdminTm selectedItem = tblAdmin.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtName.setText(selectedItem.getName());
            txtPassword.setText(selectedItem.getPassword());
            txtRole.setText(selectedItem.getRole());
            txtEmail.setText(selectedItem.getEmail());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

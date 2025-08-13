package lk.ijse.memoclipsinlayered.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.EmailUtil;
import lk.ijse.memoclipsinlayered.bo.custom.AdminBO;
import lk.ijse.memoclipsinlayered.dao.SQLUtil;
import lk.ijse.memoclipsinlayered.dto.AdminDto;

import java.sql.ResultSet;

public class SignUpPageController {
    public AnchorPane ancSignUp;
    public TextField txtUsername;
    public TextField txtPassword;
    public TextField txtRole;
    public TextField txtEmail;
    public Button btnSignUp;

    private final String userNamePattern = "^[A-Za-z0-9_ ]{3,}$";
    private final String passwordPattern = "^[A-Za-z0-9@#$%^&+=]{6,}$";
    private final String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

    private final AdminBO adminModel = (AdminBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ADMIN);

    public void initialize() {
        txtUsername.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> validFields());

        btnSignUp.setDisable(true);
    }

    public void validFields() {
        boolean isValidUsername = txtUsername.getText().matches(userNamePattern);
        boolean isValidPassword = txtPassword.getText().matches(passwordPattern);
        boolean isValidEmail = txtEmail.getText().matches(emailPattern);

        if (!isValidUsername) txtUsername.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        if (!isValidPassword) txtPassword.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        if (!isValidEmail) txtEmail.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        btnSignUp.setDisable(!(isValidUsername && isValidPassword && isValidEmail));
    }


    public void btnSignUpOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();
        String email = txtEmail.getText();

        if (username.isEmpty() || password.isEmpty() || role.isEmpty() || email.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }
        if (!email.matches(emailPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid email format. Please use a valid Gmail address.", ButtonType.OK).show();
            return;
        }
        if (!username.matches(userNamePattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid username format. Username must be at least 3 characters long and can contain letters, numbers, and underscores.", ButtonType.OK).show();
            return;
        }
        if (!password.matches(passwordPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid password format. Password must be at least 6 characters long and can contain letters, numbers, and special characters.", ButtonType.OK).show();
        }

        try {
            ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM admin WHERE name = ? OR email = ?",
                    username,email
            );
            if ((resultSet.next())){
                new Alert(Alert.AlertType.ERROR, "Username or Email already exists", ButtonType.OK).show();
                return;
            }
            String adminId = adminModel.generateNewAdminId() ;

            boolean isSaved = adminModel.saveAdmin(new AdminDto(adminId, username, password, role, email));

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"User has been successfully registered", ButtonType.OK).show();

                String subject = "Welcome to Memoclips";
                String message = "Dear " + username + ",\n\n" +
                        "Thank you for registering with Memoclips. Your account has been created successfully.\n" +
                        "Your Username: " + username + "\n" +
                        "Your Role: " + role + "\n\n" +
                        "Best regards,\n" +
                        "Memoclips Team";
                EmailUtil.sendEmail(email,subject,message);
                btnSignUpOnAction(actionEvent);
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to register user", ButtonType.OK).show();
            }
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SignUp Failed", ButtonType.OK).show();
        }
    }
}

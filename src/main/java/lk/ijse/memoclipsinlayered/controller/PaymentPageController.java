package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.PaymentBO;
import lk.ijse.memoclipsinlayered.dto.PaymentDto;
import lk.ijse.memoclipsinlayered.view.tdm.PaymentTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {
    private final PaymentBO paymentModel = (PaymentBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.PAYMENT);
    public Label lblPaymentId;
    public TextField txtInvoiceId;
    public TextField txtAmountPaid;
    public TextField txtPaymentDate;
    public TextField txtPaymentMethod;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<PaymentTm> tblPayment;
    public TableColumn<PaymentTm, String> colPaymentId;
    public TableColumn<PaymentTm, String> colInvoiceId;
    public TableColumn<PaymentTm,String> colAmountPaid;
    public TableColumn<PaymentTm, String> colPaymentDate;
    public TableColumn<PaymentTm, String> colPaymentMethod;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colInvoiceId.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        colAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        try {
            resetpage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }
    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblPayment.setItems(FXCollections.observableArrayList(
                paymentModel.getAllPayment().stream()
                        .map(paymentDto -> new PaymentTm(
                                paymentDto.getPaymentId(),
                                paymentDto.getInvoiceId(),
                                paymentDto.getAmountPaid(),
                                paymentDto.getPaymentDate(),
                                paymentDto.getPaymentMethod()
                        )).toList()
        ));
    }

    private void resetpage(){
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtAmountPaid.setText(null);
            txtInvoiceId.setText(null);
            txtPaymentDate.setText(null);
            txtPaymentMethod.setText(null);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = paymentModel.generateNewPaymentId();
        lblPaymentId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String paymentId = lblPaymentId.getText();
        String invoiceId = txtInvoiceId.getText();
        String amountPaid = txtAmountPaid.getText();
        String paymentDate = txtPaymentDate.getText();
        String paymentMethod = txtPaymentMethod.getText();


        try {
            boolean isSaved = paymentModel.savePayment(new PaymentDto(paymentId, invoiceId, amountPaid, paymentDate, paymentMethod));
            if(isSaved){
                resetpage();
                new Alert(Alert.AlertType.INFORMATION,"Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String paymentId = lblPaymentId.getText();
        String invoiceId = txtInvoiceId.getText();
        String amountPaid = txtAmountPaid.getText();
        String paymentDate = txtPaymentDate.getText();
        String paymentMethod = txtPaymentMethod.getText();

        try {
            boolean isupdated=paymentModel.updatePayment(new PaymentDto(paymentId, invoiceId, amountPaid, paymentDate, paymentMethod));
            if(isupdated){
                resetpage();
                new Alert(Alert.AlertType.INFORMATION,"Success").show();
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
            String PaymentId = lblPaymentId.getText();
            try {
                boolean isDeleted = paymentModel.deletePayment(PaymentId);
                if(isDeleted){
                    resetpage();
                    new Alert(Alert.AlertType.INFORMATION,"Payment Deleted").show();
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
        PaymentTm selectedItem = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblPaymentId.setText(selectedItem.getPaymentId());
            txtInvoiceId.setText(selectedItem.getInvoiceId());
            txtAmountPaid.setText(selectedItem.getAmountPaid());
            txtPaymentDate.setText(selectedItem.getPaymentDate());
            txtPaymentMethod.setText(selectedItem.getPaymentMethod());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

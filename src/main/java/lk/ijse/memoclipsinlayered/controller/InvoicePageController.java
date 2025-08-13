package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.InvoiceBO;
import lk.ijse.memoclipsinlayered.dto.InvoiceDto;
import lk.ijse.memoclipsinlayered.view.tdm.InvoiceTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class InvoicePageController implements Initializable {


    public Label lblInvoiceId;
    public TextField txtBookingId;
    public TextField txtAmount;
    public TextField txtDueDate;
    public TextField txtPaymentStatus;


    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<InvoiceTm> tblInvoice;
    public TableColumn<InvoiceTm, String> colInvoiceId;
    public TableColumn<InvoiceTm, String> colBookingId;
    public TableColumn<InvoiceTm, String> colAmount;
    public TableColumn<InvoiceTm, String> colDueDate;
    public TableColumn<InvoiceTm, String> colPaymentStatus;

    private final InvoiceBO invoiceModel = (InvoiceBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.INVOICE);

    public void initialize (URL url, ResourceBundle resourceBundle) {
        colInvoiceId.setCellValueFactory(new PropertyValueFactory<>("InvoiceId"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("BookingId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("PaymentStatus"));
        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblInvoice.setItems(FXCollections.observableArrayList(
                invoiceModel.getAllInvoice().stream()
                        .map(invoiceDto -> new InvoiceTm(
                                invoiceDto.getInvoiceId(),
                                invoiceDto.getBookingId(),
                                invoiceDto.getAmount(),
                                invoiceDto.getDueDate(),
                                invoiceDto.getDueDate()
                        )).toList()
        ));
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

            txtBookingId.setText(null);
            txtAmount.setText(null);
            txtDueDate.setText(null);
            txtPaymentStatus.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = invoiceModel.generateNewInvoiceId();
        lblInvoiceId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String invoiceId = lblInvoiceId.getText();
        String bookingId = txtBookingId.getText();
        String amount = txtAmount.getText();
        String dueDate = txtDueDate.getText();
        String paymentStatus = txtPaymentStatus.getText();


        try {
            boolean isSaved = invoiceModel.saveInvoice(new InvoiceDto(invoiceId, bookingId, amount, dueDate, paymentStatus));
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Saved").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String invoiceId = lblInvoiceId.getText();
        String bookingId = txtBookingId.getText();
        String amount = txtAmount.getText();
        String dueDate = txtDueDate.getText();
        String paymentStatus = txtPaymentStatus.getText();

        try {
            boolean isUpdate = invoiceModel.updateInvoice(new InvoiceDto(invoiceId, bookingId, amount, dueDate, paymentStatus));
            if (isUpdate) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure?",
                ButtonType.YES,ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES){
            String invoiceId = lblInvoiceId.getText();
            try {
                boolean isDeleted=invoiceModel.deleteInvoice(invoiceId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Invoice Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Invoice Deleted Failed").show();
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
        InvoiceTm selectedItem = (InvoiceTm) tblInvoice.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblInvoiceId.setText(selectedItem.getInvoiceId());
            txtBookingId.setText(selectedItem.getBookingId());
            txtAmount.setText(selectedItem.getAmount());
            txtDueDate.setText(selectedItem.getDueDate());
            txtPaymentStatus.setText(selectedItem.getPaymentStatus());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

        }
    }
}

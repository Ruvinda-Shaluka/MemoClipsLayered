package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.BookingBO;
import lk.ijse.memoclipsinlayered.dto.BookingDto;
import lk.ijse.memoclipsinlayered.view.tdm.BookingTm;

import java.sql.SQLException;

public class BookingPageController implements Initializable {

    public Label lblBookingId;
    public TextField txtCustomerId;
    public TextField txtDate;
    public TextField txtTime;
    public TextField txtLocation;
    public TextField txtEventType;
    public TextField txtStatus;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;


    public TableView<BookingTm> tblBooking;
    public TableColumn<BookingTm,String> colId;
    public TableColumn<BookingTm,String> colCustomerId;
    public TableColumn<BookingTm,String> colDate;
    public TableColumn<BookingTm,String> colTime;
    public TableColumn<BookingTm,String> colLocation;
    public TableColumn<BookingTm,String> colEventType;
    public TableColumn<BookingTm,String> colStatus;

    private final BookingBO bookingModel = (BookingBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.BOOKING);
    public Button btnPlaceBooking;


    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colEventType.setCellValueFactory(new PropertyValueFactory<>("bookingType"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }



    public void loadTableData() throws Exception {
        tblBooking.setItems(FXCollections.observableArrayList(
                bookingModel.getAllBooking().stream()
                        .map(bookingDto -> new BookingTm(
                                bookingDto.getBookingId(),
                                bookingDto.getCustomerId(),
                                bookingDto.getDate(),
                                bookingDto.getTime(),
                                bookingDto.getLocation(),
                                bookingDto.getBookingType(),
                                bookingDto.getBookingStatus()
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

            txtCustomerId.setText(null);
            txtDate.setText(null);
            txtTime.setText(null);
            txtLocation.setText(null);
            txtEventType.setText(null);
            txtStatus.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = bookingModel.generateNewBookingId();
        lblBookingId.setText(nextId);

    }


    public void btnSaveonAction(ActionEvent actionEvent) {
        String bookingId = lblBookingId.getText();
        String customerId = txtCustomerId.getText();
        String bookingDate = txtDate.getText();
        String bookingTime = txtTime.getText();
        String bookingLocation = txtLocation.getText();
        String eventType = txtEventType.getText();
        String bookingStatus = txtStatus.getText();

        try {
            boolean isSaved = bookingModel.saveBooking(new BookingDto(bookingId,customerId,bookingDate,bookingTime,bookingLocation,eventType,bookingStatus));
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String bookingId = lblBookingId.getText();
        String customerId = txtCustomerId.getText();
        String bookingDate = txtDate.getText();
        String bookingTime = txtTime.getText();
        String bookingLocation = txtLocation.getText();
        String eventType = txtEventType.getText();
        String bookingStatus = txtStatus.getText();


        try {
            boolean isUpdated = bookingModel.updateBooking(new BookingDto(bookingId,customerId,bookingDate,bookingTime,bookingLocation,eventType,bookingStatus));
            if (isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully Updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Update Failed").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    public void btnDeleteonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this booking?",
                ButtonType.YES,
                ButtonType.NO
        );
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES){
            String bookingId = lblBookingId.getText();
            try {
                boolean isDeleted = bookingModel.deleteBooking(bookingId);
                if (isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Booking Successfully Deleted!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Booking Delete Failed").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickTable(MouseEvent mouseEvent){
        BookingTm selectedBooking = tblBooking.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            lblBookingId.setText(selectedBooking.getBookingId());
            txtCustomerId.setText(selectedBooking.getCustomerId());
            txtDate.setText(selectedBooking.getDate());
            txtTime.setText(selectedBooking.getTime());
            txtEventType.setText(selectedBooking.getBookingType());
            txtLocation.setText(selectedBooking.getLocation());
            txtStatus.setText(selectedBooking.getBookingStatus());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }

    public void btnPlaceBookingOnAction(ActionEvent actionEvent) {
        String bookingId = lblBookingId.getText();
        String customerId = txtCustomerId.getText();
        String bookingDate = txtDate.getText();
        String bookingTime = txtTime.getText();
        String bookingLocation = txtLocation.getText();
        String eventType = txtEventType.getText();
        String bookingStatus = txtStatus.getText();

        BookingDto bookingDto = new BookingDto(bookingId,customerId,bookingDate,bookingTime,bookingLocation,eventType,bookingStatus);
        try {
            boolean isSaved = bookingModel.placeBooking(bookingDto);
            if (isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Booking Placed Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Booking Placement Failed").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }
}

package lk.ijse.memoclipsinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.memoclipsinlayered.bo.BOFactory;
import lk.ijse.memoclipsinlayered.bo.custom.AssistantBO;
import lk.ijse.memoclipsinlayered.dto.AssistantDto;
import lk.ijse.memoclipsinlayered.view.tdm.AssistantTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AssistantPageController implements Initializable {

    public Label lblAssistantId;
    public TextField txtName;
    public TextField txtPhotographerId;
    public TextField txtAvailability;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    public TableView<AssistantTm> tblAssistant;
    public TableColumn<AssistantTm,String> colName;
    public TableColumn<AssistantTm,String> colAssistantId;
    public TableColumn<AssistantTm,String> colPhotographerId;
    public TableColumn<AssistantTm,String> colAvailability;

    private final AssistantBO assistantModel = (AssistantBO) BOFactory.getInstance().getSuperBO(BOFactory.BOTypes.ASSISTANT);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAssistantId.setCellValueFactory(new PropertyValueFactory<>("assistantId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("assistantName"));
        colPhotographerId.setCellValueFactory(new PropertyValueFactory<>("photographerId"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    public void  loadTableData() throws SQLException, ClassNotFoundException {
        tblAssistant.setItems(FXCollections.observableArrayList(
                assistantModel.getAllAssistant().stream()
                        .map(assistantDto -> new AssistantTm(
                                assistantDto.getAssistantId(),
                                assistantDto.getAssistantName(),
                                assistantDto.getPhotographerId(),
                                assistantDto.getAvailability()
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
            txtPhotographerId.setText(null);
            txtAvailability.setText(null);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = assistantModel.generateNewAssistantId();
        lblAssistantId.setText(nextId);

    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String assistantId = lblAssistantId.getText();
        String assistantName = txtName.getText();
        String photographerId = txtPhotographerId.getText();
        String availability = txtAvailability.getText();


        try {
            boolean isSaved=assistantModel.saveAssistant(new AssistantDto(assistantId, assistantName, photographerId, availability));
            if(isSaved){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Assistant Saved!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Assistant Saved Failed!").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String assistantId = lblAssistantId.getText();
        String assistantName = txtName.getText();
        String photographerId = txtPhotographerId.getText();
        String availability = txtAvailability.getText();


        try {
            boolean isUpdated = assistantModel.updateAssistant(new AssistantDto(assistantId, assistantName, photographerId, availability));
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Assistant Updated!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Assistant Updated Failed!").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure?",
                ButtonType.YES,ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES){
            String assistantId = lblAssistantId.getText();
            try {
                boolean isDeleted=assistantModel.deleteAssistant(assistantId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Assistant Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Assistant Deleted Failed").show();
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
        AssistantTm selectedAssistant= tblAssistant.getSelectionModel().getSelectedItem();
        if(selectedAssistant!=null){
            lblAssistantId.setText(selectedAssistant.getAssistantId());
            txtName.setText(selectedAssistant.getAssistantName());
            txtPhotographerId.setText(selectedAssistant.getPhotographerId());
            txtAvailability.setText(selectedAssistant.getAvailability());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}

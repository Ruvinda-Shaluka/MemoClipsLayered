package lk.ijse.memoclipsinlayered.bo.custom;

import lk.ijse.memoclipsinlayered.bo.SuperBO;
import lk.ijse.memoclipsinlayered.dto.AdminDto;
import lk.ijse.memoclipsinlayered.dto.AssistantDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AssistantBO extends SuperBO {
    public ArrayList<AssistantDto> getAllAssistant() throws SQLException, ClassNotFoundException;
    public boolean saveAssistant(AssistantDto assistantDto) throws SQLException, ClassNotFoundException;
    public boolean updateAssistant(AssistantDto assistantDto) throws SQLException, ClassNotFoundException;
    public boolean existAssistant(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteAssistant(String id) throws SQLException, ClassNotFoundException;
    public String generateNewAssistantId() throws SQLException, ClassNotFoundException;
    public ArrayList<AssistantDto> searchAssistant(String id) throws SQLException, ClassNotFoundException;
}

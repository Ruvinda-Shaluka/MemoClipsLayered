package lk.ijse.memoclipsinlayered.bo.custom.impl;

import lk.ijse.memoclipsinlayered.bo.custom.AssistantBO;
import lk.ijse.memoclipsinlayered.dao.DAOFactory;
import lk.ijse.memoclipsinlayered.dao.custom.AssistantDAO;
import lk.ijse.memoclipsinlayered.dto.AssistantDto;
import lk.ijse.memoclipsinlayered.entity.AssistantEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class AssistantBOImpl implements AssistantBO {
    AssistantDAO assistantDAO = (AssistantDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOTypes.ASSISTANT);

    @Override
    public ArrayList<AssistantDto> getAllAssistant() throws SQLException, ClassNotFoundException {
        ArrayList<AssistantEntity> entity = assistantDAO.getAll();
        ArrayList<AssistantDto> assistantDtos = new ArrayList<>();
        for (AssistantEntity e: entity) {
            assistantDtos.add(new AssistantDto(
                    e.getAssistantId(),
                    e.getAssistantName(),
                    e.getPhotographerId(),
                    e.getAvailability()
            ));
        }

        return assistantDtos;
    }

    @Override
    public boolean saveAssistant(AssistantDto assistantDto) throws SQLException, ClassNotFoundException {
        return assistantDAO.save(new AssistantEntity(
                assistantDto.getAssistantId(),
                assistantDto.getAssistantName(),
                assistantDto.getPhotographerId(),
                assistantDto.getAvailability()
        ));
    }

    @Override
    public boolean updateAssistant(AssistantDto assistantDto) throws SQLException, ClassNotFoundException {
        return assistantDAO.update(new AssistantEntity(
                assistantDto.getAssistantId(),
                assistantDto.getAssistantName(),
                assistantDto.getPhotographerId(),
                assistantDto.getAvailability()
        ));
    }

    @Override
    public boolean existAssistant(String id) throws SQLException, ClassNotFoundException {
        return assistantDAO.exist(id);
    }

    @Override
    public boolean deleteAssistant(String id) throws SQLException, ClassNotFoundException {
        return assistantDAO.delete(id);
    }

    @Override
    public String generateNewAssistantId() throws SQLException, ClassNotFoundException {
        return assistantDAO.generateNewId();
    }

    @Override
    public ArrayList<AssistantDto> searchAssistant(String id) throws SQLException, ClassNotFoundException {
        ArrayList<AssistantEntity> entity = assistantDAO.search(id);
        ArrayList<AssistantDto> assistantDtos = new ArrayList<>();
        for (AssistantEntity e : entity) {
            assistantDtos.add(new AssistantDto(
                    e.getAssistantId(),
                    e.getAssistantName(),
                    e.getPhotographerId(),
                    e.getAvailability()
            ));
        }
        return assistantDtos;
    }


}

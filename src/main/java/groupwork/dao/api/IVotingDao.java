package groupwork.dao.api;

import groupwork.dto.SavedVoiceDTO;

import java.util.List;

public interface IVotingDao {
    //исправить на entity
    List<SavedVoiceDTO> getVoiceList();


    void save(SavedVoiceDTO voice);
}

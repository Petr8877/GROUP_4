package groupwork.dao.api;

import groupwork.dto.SavedVoiceEntity;

import java.util.List;

public interface IVotingDao {
    //исправить на entity
    List<SavedVoiceEntity> getVoiceList();


    void save(SavedVoiceEntity voice);
}

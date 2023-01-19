package groupwork.dao.api;

import groupwork.dto.SavedVoiceDTO;

import java.util.List;

public interface IVotingDao {

    List<SavedVoiceDTO> getVoiceList();


    SavedVoiceDTO save(SavedVoiceDTO voice);
}

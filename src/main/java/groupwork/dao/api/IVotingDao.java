package groupwork.dao.api;

import groupwork.dto.SavedVoiceDTO;

import java.util.List;
import java.util.Map;

public interface IVotingDao {

    List<SavedVoiceDTO> getVoiceList();

    Map<Long, Long> getIdAndKey();
    void auth (long id);

    int save(SavedVoiceDTO voice);
}

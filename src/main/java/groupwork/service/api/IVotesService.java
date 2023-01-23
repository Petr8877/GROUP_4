package groupwork.service.api;

import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;

import java.util.List;
import java.util.Map;

public interface IVotesService {

    SavedVoiceDTO save(VoiceDTO voice);

    void save2(SavedVoiceDTO voice);

    void reb (long id);
    Map<Long, Long> red();
    List<SavedVoiceDTO> get();

}

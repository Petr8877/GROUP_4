package groupwork.service.api;

import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;

import java.util.List;

public interface IVotesService {

    SavedVoiceDTO save(VoiceDTO voice);

    List<SavedVoiceDTO> get();

}

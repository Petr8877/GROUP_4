package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dao2.api.IVotingDao;
import by.it_course.groupwork.dto.SavedVoiceDTO;
import by.it_course.groupwork.dto.VoiceDTO;

import java.util.List;

public interface IVotesService {

    void save(VoiceDTO voice);
     List<SavedVoiceDTO> get();

}

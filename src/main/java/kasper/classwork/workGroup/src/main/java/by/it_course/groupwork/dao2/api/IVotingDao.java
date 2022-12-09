package by.it_course.groupwork.dao2.api;

import by.it_course.groupwork.dto.SavedVoiceDTO;
import java.util.List;

public interface IVotingDao {

    List<SavedVoiceDTO> getVoiceList();

    void save(SavedVoiceDTO voice);
}

package by.it_course.groupwork.dao2;

import by.it_course.groupwork.dao2.api.IVotingDao;
import by.it_course.groupwork.dto.SavedVoiceDTO;
import java.util.ArrayList;
import java.util.List;

public class VotingDao implements IVotingDao {

    private List<SavedVoiceDTO> voices = new ArrayList<>();

    @Override
    public List<SavedVoiceDTO> getVoiceList() {
        return voices;
    }

    @Override
    public void save(SavedVoiceDTO voice) {
        voices.add(voice);
    }
}

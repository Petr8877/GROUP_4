package groupwork.dao.memory;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class VotingDao implements IVotingDao {

    private Set<SavedVoiceDTO> voices = new ConcurrentSkipListSet<>();

    @Override
    public synchronized List<SavedVoiceDTO> getVoiceList() {
        return new ArrayList<>(voices);
    }

    @Override
    public Map<Long, Long> red() {
        return null;
    }

    @Override
    public void reb(long id) {

    }

    @Override
    public synchronized int save(SavedVoiceDTO voice) {
        voices.add(voice);
        return 0;
    }
}

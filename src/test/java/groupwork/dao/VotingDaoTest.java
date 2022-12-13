package groupwork.dao;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotingDaoTest {
    private final IVotingDao dao = new VotingDao();
    @Test
    public void testVotingDaoTrue() {
        int singer = 1;
        int[] genre = {1, 2, 3};
        String message = "lololo";
        SavedVoiceDTO savedVoiceDTO = new SavedVoiceDTO(new VoiceDTO(singer, genre, message));
        dao.save(savedVoiceDTO);
        boolean result;
        result = !dao.getVoiceList().isEmpty();
        assertTrue(result);
    }
}
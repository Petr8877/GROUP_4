package by.it_course.groupwork.service;

import by.it_course.groupwork.dao.VotingDaoSingleton;
import by.it_course.groupwork.dao.service.GenresServiceSingleton;
import by.it_course.groupwork.dao.service.SingersServiceSingleton;
import by.it_course.groupwork.dao2.api.IVotingDao;
import by.it_course.groupwork.dto.VoiceDTO;
import by.it_course.groupwork.service.api.IVotesService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoteService implements IVotesService {
    private final IVotingDao voiceDao;

    public VoteService(IVotingDao voiceDao) {
        this.voiceDao = voiceDao;
    }

    @Override
    public void check(String singer, String[] genre, String aboutMe) throws Exception {
        GenresServiceSingleton.getInstance().check(genre);
        SingersServiceSingleton.getInstance().check(singer);
        Set<String> set = new HashSet<>(List.of(genre));
        if (set.size() < 3 || set.size() > 5) {
            throw new Exception("Please, choice three - five different genres");
        }
        if (aboutMe == null || aboutMe.isEmpty()) {
            throw new Exception("Please, Tell me about yourself");
        }
    }

    @Override
    public void save(String singer, String[] genre, String aboutMe) {
        VoiceDTO voice = new VoiceDTO(singer, genre, aboutMe);
        VotingDaoSingleton.getInstance().save(voice);
    }

    @Override
    public List<VoiceDTO> getAllVoice() {
        return voiceDao.getVoiceList();
    }
}

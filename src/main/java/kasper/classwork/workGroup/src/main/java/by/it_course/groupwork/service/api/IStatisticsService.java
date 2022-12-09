package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dao2.VotingDao;
import by.it_course.groupwork.dto.ResultForMapDTO;
import by.it_course.groupwork.dto.SavedVoiceDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IStatisticsService {

    void calcVoice(VotingDao votingDao);
    ResultForMapDTO get();
}

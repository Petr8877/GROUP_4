package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dao2.VotingDao;
import by.it_course.groupwork.dto.GenreDTO;
import by.it_course.groupwork.dto.SingerDTO;
import by.it_course.groupwork.dto.StaticticsDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IStatisticsService {

    void calcVoice( );
    Map<SingerDTO,Integer> getMapSingers();
    Map<GenreDTO,Integer> getMapGenres();
    Map<String, LocalDateTime> getUserInfo();
}

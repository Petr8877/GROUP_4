package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dto.GenreDTO;
import by.it_course.groupwork.dto.SingerDTO;
import by.it_course.groupwork.dto.SingleStatisticDTO;
import by.it_course.groupwork.dto.AllStatisticDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IStatisticsService {

    // void calcVoice( );
    //тестовый прогон
    SingleStatisticDTO<SingerDTO, Integer> getMapSingers();

    Map<GenreDTO, Integer> getMapGenres();
    // Map<String, LocalDateTime> getUserInfo();

    AllStatisticDTO getAllSort();
}

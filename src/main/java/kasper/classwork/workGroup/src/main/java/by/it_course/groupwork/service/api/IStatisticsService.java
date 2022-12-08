package by.it_course.groupwork.service.api;

import java.time.LocalDateTime;
import java.util.Map;

public interface IStatisticsService {

    /**
     *
     * @return возвращает 3 отсортированные мапы (жынры, исполнитель, о себе)
     * ключ просто название (Singers, Genres, AboutMe)
     */

    Map<String, Integer>getResultSingers();
    Map<String, Integer> getResultGenres();
    Map<String,LocalDateTime> getUserInfo();
}

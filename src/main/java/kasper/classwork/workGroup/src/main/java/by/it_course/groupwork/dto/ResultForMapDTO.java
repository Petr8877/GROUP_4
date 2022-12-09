package by.it_course.groupwork.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResultForMapDTO {
    private final Map<String, Integer> mapSingers;
    private final Map<String, Integer> mapGenres;
    private final Map<String, LocalDateTime> mapUserInfo;

    public ResultForMapDTO(Map<String, Integer> mapSingers,
                           Map<String, Integer> mapGenres,
                           Map<String, LocalDateTime> mapUserInfo) {
        this.mapSingers = mapSingers;
        this.mapGenres = mapGenres;
        this.mapUserInfo = mapUserInfo;
    }

    public Map<String, Integer> getMapSingers() {
        return mapSingers;
    }

    public Map<String, Integer> getMapGenres() {
        return mapGenres;
    }

    public Map<String, LocalDateTime> getMapUserInfo() {
        return mapUserInfo;
    }
}

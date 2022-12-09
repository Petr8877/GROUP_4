package by.it_course.groupwork.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResultForMapDTO {
    private Map<SingerDTO, Integer> mapSingers;
    private Map<GenreDTO, Integer> mapGenres;
    private Map<String, LocalDateTime> mapUserInfo;

    public ResultForMapDTO(Map<SingerDTO, Integer> mapSingers,
                           Map<GenreDTO, Integer> mapGenres,
                           Map<String, LocalDateTime> mapUserInfo) {
        this.mapSingers = mapSingers;
        this.mapGenres = mapGenres;
        this.mapUserInfo = mapUserInfo;
    }

    public ResultForMapDTO() {
        this.mapSingers = new HashMap<>();
        this.mapGenres = new HashMap<>();
        this.mapUserInfo = new HashMap<>();
    }

    public Map<SingerDTO, Integer> getMapSingers() {
        return mapSingers;
    }

    public Map<GenreDTO, Integer> getMapGenres() {
        return mapGenres;
    }

    public Map<String, LocalDateTime> getMapUserInfo() {
        return mapUserInfo;
    }
}

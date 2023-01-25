package groupwork.dto;

import groupwork.entity.SingerEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AllStatisticDTO {

    private final Map<SingerEntity, Integer> mapSingers;
    private final Map<GenreDTO, Integer> mapGenres;
    private final Map<String, LocalDateTime> mapUserInfo;

    public AllStatisticDTO(Map<SingerEntity, Integer> mapSingers,
                           Map<GenreDTO, Integer> mapGenres,
                           Map<String, LocalDateTime> mapUserInfo) {
        this.mapSingers = mapSingers;
        this.mapGenres = mapGenres;
        this.mapUserInfo = mapUserInfo;
    }

    public Map<SingerEntity, Integer> getMapSingers() {
        return mapSingers;
    }

    public Map<GenreDTO, Integer> getMapGenres() {
        return mapGenres;
    }

    public Map<String, LocalDateTime> getMapUserInfo() {
        return mapUserInfo;
    }


}

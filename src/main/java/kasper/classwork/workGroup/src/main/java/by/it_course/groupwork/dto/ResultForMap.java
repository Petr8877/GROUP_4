package by.it_course.groupwork.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResultForMap {
    private Map<String, Integer> mapSingers =new HashMap<>();
    private Map<String, Integer> mapGenres =new HashMap<>();
    private Map<String, LocalDateTime> mapUserInfo =new HashMap<>();

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

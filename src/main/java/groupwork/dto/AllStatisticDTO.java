package groupwork.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AllStatisticDTO {

    private final Map<SingerDTO, Integer> mapSingers;
    private final Map<GenreDTO, Integer> mapGenres;
    private final Map<String, LocalDateTime> mapUserInfo;

    public AllStatisticDTO(Map<SingerDTO, Integer> mapSingers,
                           Map<GenreDTO, Integer> mapGenres,
                           Map<String, LocalDateTime> mapUserInfo) {
        this.mapSingers = mapSingers;
        this.mapGenres = mapGenres;
        this.mapUserInfo = mapUserInfo;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        builder.append("Результаты голосования за лучшего исполнителя: " + "\n");

        for (Map.Entry<SingerDTO, Integer> entry : mapSingers.entrySet()) {
            builder.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }

        builder.append("Результаты голосования за лучший жанр: " + "\n");

        for (Map.Entry<GenreDTO, Integer> entry : mapGenres.entrySet()) {
            builder.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }

        builder.append("О пользователе: " + "\n");

        for (Map.Entry<String, LocalDateTime> entry : mapUserInfo.entrySet()) {
            builder.append(entry.getKey()).append(" -> ").append(dtf.format(entry.getValue())).append("\n");
        }

        return builder.toString();
    }
}

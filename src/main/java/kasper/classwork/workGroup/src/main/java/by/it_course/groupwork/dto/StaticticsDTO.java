package by.it_course.groupwork.dto;

import by.it_course.groupwork.service.api.IStatisticsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StaticticsDTO {
    private final IStatisticsService iStatisticsService;
    private List<String> allStatistic = new ArrayList<>();

    public StaticticsDTO(IStatisticsService iStatisticsService) {
        this.iStatisticsService = iStatisticsService;
    }
    public List<String> getAllStatistic(){
        Map<SingerDTO, Integer> mapSingers = iStatisticsService.getMapSingers();

        for ( SingerDTO val:mapSingers.keySet()) {
            allStatistic.add(val.getName()+" - "+ mapSingers.get(val));
        }

        Map<GenreDTO, Integer> mapGenres = iStatisticsService.getMapGenres();

        for ( GenreDTO val:mapGenres.keySet()) {
            allStatistic.add(val.getName()+" - "+ mapGenres.get(val));
        }

        Map<String, LocalDateTime> userInfo = iStatisticsService.getUserInfo();

        for (String message: userInfo.keySet()) {
            allStatistic.add(message+ " "+ userInfo.get(message));
        }return allStatistic;

    }

//    private <K,V> List<String > fill(Map<K,V> map){
//        for (K val:map.keySet()) {
//
//        }
//    }


//
////    private final Map<SingerDTO, Integer> mapSingers;
////    private final Map<GenreDTO, Integer> mapGenres;
////    private final Map<String, LocalDateTime> mapUserInfo;
////    , Map<SingerDTO, Integer> mapSingers,
////    Map<GenreDTO, Integer> mapGenres,
////    Map<String, LocalDateTime> mapUserInfo
//
////    public StaticticsDTO(SavedVoiceDTO savedVoiceDTO) {
//////        this.savedVoiceDTO = savedVoiceDTO;
//////        this.mapSingers = mapSingers;
//////        this.mapGenres = mapGenres;
//////        this.mapUserInfo = mapUserInfo;
////    }
//
////    public Map<String, Integer> getMapSingers() {
//
////        return savedVoiceDTO.getVoice().getSinger();
//    }
//
//    public Map<String, Integer> getMapGenres() {
//        return mapGenres;
//    }
//
//    public Map<String, LocalDateTime> getMapUserInfo() {
//        return mapUserInfo;
//    }
}

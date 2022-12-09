package by.it_course.groupwork.service;

import by.it_course.groupwork.dao.service.GenresServiceSingleton;
import by.it_course.groupwork.dao.service.SingersServiceSingleton;
import by.it_course.groupwork.dao.service.VoteServiceSingleton;
import by.it_course.groupwork.dto.ResultForMapDTO;
import by.it_course.groupwork.dto.VoiceDTO;
import by.it_course.groupwork.service.api.IGenreService;
import by.it_course.groupwork.service.api.ISingerService;
import by.it_course.groupwork.service.api.IStatisticsService;
import by.it_course.groupwork.service.api.IVotesService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StatisticsService implements IStatisticsService {
    private IVotesService voteService = VoteServiceSingleton.getInstance();

    @Override
    public Map<String, Integer> getResultSingers() {
        ISingerService ss = SingersServiceSingleton.getInstance();
        List<String> singerList = ss.get();
        List<VoiceDTO> allVoice = voteService.getAllVoice();
        ResultForMapDTO resultForMap = new ResultForMapDTO();
        Map<String, Integer> resultSingers = resultForMap.getMapSingers();
        for (String s : singerList) {
            resultSingers.put(s, 0);
        }
        for (VoiceDTO voice : allVoice) {
            String singer = voice.getSinger();
            for (Map.Entry<String, Integer> stringIntegerEntry : resultSingers.entrySet()) {
                if (stringIntegerEntry.getKey().equals(singer)) {
                    stringIntegerEntry.setValue(stringIntegerEntry.getValue() + 1);
                }
            }
        }
        return resultSingers.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));
    }

    @Override
    public Map<String, Integer> getResultGenres() {
        IGenreService gs = GenresServiceSingleton.getInstance();
        List<String> singerList = gs.get();
        List<VoiceDTO> allVoice = voteService.getAllVoice();

        ResultForMapDTO resultForMap = new ResultForMapDTO();
        Map<String, Integer> resultGenres = resultForMap.getMapGenres();
        for (String s : singerList) {
            resultGenres.put(s, 0);
        }
        for (VoiceDTO voice : allVoice) {
            String[] genres = voice.getGenre();
            for (String genre : genres) {
                for (Map.Entry<String, Integer> stringIntegerEntry : resultGenres.entrySet()) {
                    if (stringIntegerEntry.getKey().equals(genre)) {
                        stringIntegerEntry.setValue(stringIntegerEntry.getValue() + 1);
                    }
                }
            }
        }
        return resultGenres.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));
    }

    @Override
    public Map<String, LocalDateTime> getUserInfo() {
        List<VoiceDTO> allVoice = voteService.getAllVoice();
        ResultForMapDTO resultForMap = new ResultForMapDTO();
        Map<String, LocalDateTime> resultUserInfo = resultForMap.getMapUserInfo();
        for (VoiceDTO voice : allVoice) {
            ShortAboutUser shortAboutUser = voice.getAboutUser();
            resultUserInfo.put(shortAboutUser.getAboutUser(), shortAboutUser.getCreationTime());
        }
        return resultUserInfo.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(LocalDateTime::compareTo)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));
    }
}

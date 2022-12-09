package by.it_course.groupwork.service;

import by.it_course.groupwork.dao.GenreDaoSingleton;
import by.it_course.groupwork.dao.SingerDaoSingleton;
import by.it_course.groupwork.dao2.VotingDao;
import by.it_course.groupwork.dao2.api.IGenreDao;
import by.it_course.groupwork.dao2.api.ISingerDao;
import by.it_course.groupwork.dto.*;
import by.it_course.groupwork.service.api.IStatisticsService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StatisticsService implements IStatisticsService {

    private Map<String, Integer> resultSingers;

    private Map<String, Integer> resultGenres;

    private Map<String, LocalDateTime> resultAboutMe;

    private void initialization(){
        ISingerDao singerDao = SingerDaoSingleton.getInstance();
        List<SingerDTO> listSinger = singerDao.getSingerList();
        for (SingerDTO singerDTO : listSinger) {
            resultSingers.put(singerDTO.getName(), 0);
        }

        IGenreDao genreDao = GenreDaoSingleton.getInstance();
        List<GenreDTO> listGenre = genreDao.getGenreList();
        for (GenreDTO genreDTO : listGenre) {
            resultGenres.put(genreDTO.getName(), 0);
        }
    }

    @Override
    public void calcVoice(VotingDao votingDao) {
        initialization();

        List<SavedVoiceDTO> savedVoiceDTOS = votingDao.getVoiceList();

        for (SavedVoiceDTO voice : savedVoiceDTOS) {
            String singer = voice.getVoice().getSinger();
            if (resultSingers.containsKey(singer)) {
                resultSingers.put(singer, resultSingers.get(singer) + 1);
            }

            String[] genres = voice.getVoice().getGenre();
            for (String genre : genres) {
                resultGenres.put(genre, resultGenres.get(genre)+1);
            }

            resultAboutMe.put(voice.getVoice().getMessage(), voice.getCreationTime());
        }
    }

    @Override
    public ResultForMapDTO get() {
        Map<String, Integer> tmpStringer = resultSingers.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));

        Map<String, Integer> tmpGenre = resultGenres.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));

        Map<String, LocalDateTime> tmpAboutMe = resultAboutMe.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(LocalDateTime::compareTo)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));

        return new ResultForMapDTO(tmpStringer, tmpGenre, tmpAboutMe);
    }
}

package groupwork.service;

import groupwork.dao.fabrics.GenreDaoSingleton;
import groupwork.dto.*;
import groupwork.service.api.IGenreService;
import groupwork.service.api.ISingerService;
import groupwork.service.api.IStatisticsService;
import groupwork.service.api.IVotesService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class StatisticsService implements IStatisticsService {

    private final IVotesService iVotesService;
    private final ISingerService iSingerService;
    private final IGenreService iGenreService;


    public StatisticsService(IVotesService iVotesService,
                             ISingerService iSingerService,
                             IGenreService iGenreService) {
        this.iVotesService = iVotesService;
        this.iSingerService = iSingerService;
        this.iGenreService = iGenreService;
    }

    private void calcVoice(Map<SingerDTO, Integer> mapSinger, Map<GenreDTO, Integer> mapGenre, Map<String,
            LocalDateTime> mapUser, List<SingerDTO> singerDTOS, List<GenreDTO> genreDTOS) {
        List<SavedVoiceDTO> savedVoiceDTOS = iVotesService.get();
        for (SavedVoiceDTO savedVoiceDTO : savedVoiceDTOS) {
            int idSinger = savedVoiceDTO.getVoice().getSinger();
            int[] idGenre = savedVoiceDTO.getVoice().getGenre();

            mapUser.put(savedVoiceDTO.getVoice().getMessage(), savedVoiceDTO.getCreationTime());

            for (SingerDTO SingerDTO : singerDTOS) {
                if (idSinger == SingerDTO.getId()) {
                    mapSinger.put(SingerDTO, mapSinger.get(SingerDTO) + 1);
                }
            }

            for (GenreDTO genreDTO : genreDTOS) {
                for (int i : idGenre) {
                    if (i == genreDTO.getId()) {
                        mapGenre.put(genreDTO, mapGenre.get(genreDTO) + 1);
                    }
                }
            }
        }
    }

    private AllStatisticDTO createResultObject(Map<SingerDTO, Integer> mapSinger, Map<GenreDTO, Integer> mapGenre, Map<String, LocalDateTime> mapUser) {
        return new AllStatisticDTO(
                mapSinger.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                                , (v1, v2) -> v1, LinkedHashMap::new)),
                mapGenre.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                                , (v1, v2) -> v1, LinkedHashMap::new)),
                mapUser.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(LocalDateTime::compareTo)))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                                , (v1, v2) -> v1, LinkedHashMap::new))
        );
    }

    @Override
    public AllStatisticDTO getAllSort() {
        Map<SingerDTO, Integer> mapSinger = new HashMap<>();
        Map<GenreDTO, Integer> mapGenre = new HashMap<>();
        Map<String, LocalDateTime> mapUser = new HashMap<>();
        List<SingerDTO> singerDTOS = iSingerService.getAll();
        List<GenreDTO> genreDTOS = iGenreService.getAll();

        for (SingerDTO listSingerDTO : singerDTOS) {
            mapSinger.put(listSingerDTO, 0);
        }
        for (GenreDTO genreDTO : genreDTOS) {
            mapGenre.put(genreDTO, 0);
        }

        calcVoice(mapSinger, mapGenre, mapUser, singerDTOS, genreDTOS);

        return createResultObject(mapSinger, mapGenre, mapUser);
    }
//    @Override
//    public Map<String, Integer> getResultSingers() {
//
//        Map<String, Integer> resultSingers = new HashMap<>();
//
//
//
//        for (String s : SingersServiceSingleton.getInstance().get()) {
//            resultSingers.put(s,0);
//        }
//
//        for (VoteDTO val : votingDao.get()) {
//            for (String key : val.getSinger()) {
//                Integer mapValue = resultSingers.get(key);
//                resultSingers.put(key, mapValue == 0 ? 1 : mapValue + 1);
//            }
//        } return resultSingers.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
//                        , (v1, v2) -> v1, LinkedHashMap::new));
//    }
//    @Override
//    public Map<String, Integer> getResultGenres() {
//        List<GenreDTO> genresList = GenreDaoSingleton.getInstance().getAll();
//
//        Map<String, Integer> resultGenres = new HashMap<>();
//
//        for (GenreDTO s : genresList) {
//            resultGenres.put(s.getName(),0);
//        }
//        for (SavedVoiceDTO voice: iVotesService.get()) {
//            for (Integer genre: voice.getVoice().getGenre()) {
//                Integer mapValue = resultGenres.get(genre);
//                resultGenres.put(genre, mapValue == 0 ? 1 : mapValue + 1);
////
//            }
//        }
//        return resultGenres.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
//                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue
//                        ,(v1, v2) -> v1, LinkedHashMap::new));
//
//    }
//
//    @Override
//    public Map<LocalDateTime, String> getUserInfo() {
//        Map<LocalDateTime,String> resultGenres = new HashMap<>();
//        for (VoteDTO voice: votingDao.get()) {
//            for (String message:voice.getMessage()) {
//                resultGenres.put(voice.getDateTime(),message);
//            }
////
//        }return  resultGenres.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey(Collections.reverseOrder(LocalDateTime::compareTo)))
//                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue
//                        ,(v1, v2) -> v1, LinkedHashMap::new));
//    }
}

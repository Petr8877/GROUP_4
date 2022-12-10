package by.it_course.groupwork.service;

import by.it_course.groupwork.dto.*;
import by.it_course.groupwork.service.api.IGenreService;
import by.it_course.groupwork.service.api.ISingerService;
import by.it_course.groupwork.service.api.IStatisticsService;
import by.it_course.groupwork.service.api.IVotesService;

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




    @Override
    public void calcVoice() {
//        List<SavedVoiceDTO> savedVoiceDTOS = iVotesService.get();
//        calcVoiceSingers(savedVoiceDTOS);
//        SavedVoiceDTO savedVoiceDTO = savedVoiceDTOS.get(1);
//        VoiceDTO voice = savedVoiceDTO.getVoice();


    }
//тестовый прогон SingleStatisticDTO
    private SingleStatisticDTO<SingerDTO, Integer> calcVoiceSingers(  ){

        Map<SingerDTO, Integer> mapSingers = new HashMap<>();

        List<SavedVoiceDTO> savedVoiceDTOS = iVotesService.get();
        List<SingerDTO> singerDTOS = iSingerService.get();
        for (SingerDTO singerDTO :  singerDTOS) {
            mapSingers.put(singerDTO, 0);
        }
        for (SavedVoiceDTO saveVoiceDTO:savedVoiceDTOS) {

            for (SingerDTO singerDTO :  singerDTOS) {
                if (singerDTO.getId()== saveVoiceDTO.getVoice().getSinger())
                    mapSingers.put(singerDTO,
                            (mapSingers.get(singerDTO) == 0) ? 1 : mapSingers.get(singerDTO) + 1);

            }
        }
        return new SingleStatisticDTO<>(mapSingers.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new)));
    }


    private Map<GenreDTO,Integer> calcVoiceGenres(  ){
        Map<GenreDTO, Integer> mapGenres = new HashMap<>();
        List<SavedVoiceDTO> savedVoiceDTOS = iVotesService.get();
        List<GenreDTO> genreDTOS = iGenreService.get();
        for (GenreDTO genreDTO :  genreDTOS) {
            mapGenres.put(genreDTO, 0);
        }
        for (SavedVoiceDTO saveVoiceDTO:savedVoiceDTOS) {

            for (GenreDTO genreDTO : genreDTOS) {
                for (int genre : saveVoiceDTO.getVoice().getGenre()) {

                    if (genreDTO.getId() == genre)
                        mapGenres.put(genreDTO,
                                (mapGenres.get(genreDTO) == 0) ? 1 : mapGenres.get(genreDTO) + 1);

                }
            }
        }
        return mapGenres.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));
    }

    private Map<String, LocalDateTime> calcUserInfo(  ){
        Map<String, LocalDateTime> mapUserInfo = new HashMap<>();
        List<SavedVoiceDTO> savedVoiceDTOS = iVotesService.get();

        for (SavedVoiceDTO savedVoiceDTO :  savedVoiceDTOS) {
            mapUserInfo.put(savedVoiceDTO.getVoice().getMessage(), savedVoiceDTO.getCreationTime());
        }

        return mapUserInfo.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(LocalDateTime::compareTo)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (v1, v2) -> v1, LinkedHashMap::new));
    }

//тестовый прогон AllStatisticDTO,под замену кода внутри
   public AllStatisticDTO getAllSort(){
        Map<SingerDTO, Integer> mapSingers = new HashMap<>();
        Map<GenreDTO, Integer> mapGenres = calcVoiceGenres();
        Map<String, LocalDateTime> mapUserInfo = calcUserInfo();
        return new AllStatisticDTO(mapSingers, mapGenres, mapUserInfo);
    }
//тестовый прогон
    @Override
    public SingleStatisticDTO<SingerDTO, Integer> getMapSingers() {
        return calcVoiceSingers();
    }

    @Override
    public Map<GenreDTO, Integer> getMapGenres() {
        return calcVoiceGenres();
    }

    @Override
    public Map<String, LocalDateTime> getUserInfo() {

        return calcUserInfo();
    }


//    private Map<SingerDTO, Integer> resultSingers = new HashMap<>();
//
//    private Map<GenreDTO, Integer> resultGenres = new HashMap<>();
//
//    private Map<String, LocalDateTime> resultAboutMe = new HashMap<>();
//    private final IVotesService votesService;
//
//    public StatisticsService(IVotesService votesService) {
//        this.votesService = votesService;
//    }
//
//    private void initialization(){
////        ISingerDao singerDao = SingerDaoSingleton.getInstance();
//        votesService.
//        List<SingerDTO> listSinger = singerDao.getSingerList();
//        for (SingerDTO singerDTO : listSinger) {
//            resultSingers.put(singerDTO.getName(), 0);
//        }
//
//        IGenreDao genreDao = GenreDaoSingleton.getInstance();
//        List<GenreDTO> listGenre = genreDao.getGenreList();
//        for (GenreDTO genreDTO : listGenre) {
//            resultGenres.put(genreDTO.getName(), 0);
//        }
//    }
//
//    @Override
//    public void calcVoice(VotingDao votingDao) {
//        initialization();
//
//        List<SavedVoiceDTO> savedVoiceDTOS = votingDao.getVoiceList();
//
//        for (SavedVoiceDTO voice : savedVoiceDTOS) {
//            Integer singer = voice.getVoice().getSinger();
//            if (resultSingers.containsKey(singer)) {
//                resultSingers.put( resultSingers., resultSingers.get(singer) + 1);
//            }
//
//            int[] genres = voice.getVoice().getGenre();
//            for (int genre : genres) {
//                resultGenres.put(genre, resultGenres.get(genre)+1);
//            }
//
//            resultAboutMe.put(voice.getVoice().getMessage(), voice.getCreationTime());
//        }
//    }
//
//    @Override
//    public ResultForMapDTO get() {
//        Map<Integer, Integer> tmpStringer = resultSingers.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
//                        , (v1, v2) -> v1, LinkedHashMap::new));
//
//        Map<Integer, Integer> tmpGenre = resultGenres.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
//                        , (v1, v2) -> v1, LinkedHashMap::new));
//
//        Map<String, LocalDateTime> tmpAboutMe = resultAboutMe.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(LocalDateTime::compareTo)))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
//                        , (v1, v2) -> v1, LinkedHashMap::new));
//
//        return new ResultForMapDTO(tmpStringer, tmpGenre, tmpAboutMe);
//    }
}

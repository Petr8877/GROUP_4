package groupwork.service;

import groupwork.dto.*;
import groupwork.entity.SingerEntity;
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

    private void calcVoice(Map<SingerEntity, Integer> mapSinger, Map<GenreDTO, Integer> mapGenre, Map<String,
            LocalDateTime> mapUser, List<SingerEntity> singerDTOS, List<GenreDTO> genreDTOS) {
        List<SavedVoiceDTO> savedVoiceDTOS = iVotesService.get();
        for (SavedVoiceDTO savedVoiceDTO : savedVoiceDTOS) {
            int idSinger = savedVoiceDTO.getVoice().getSinger();
            int[] idGenre = savedVoiceDTO.getVoice().getGenre();

            mapUser.put(savedVoiceDTO.getVoice().getMessage(), savedVoiceDTO.getCreationTime());

            for (SingerEntity singer : singerDTOS) {
                if (idSinger == singer.getId()) {
                    mapSinger.put(singer, mapSinger.get(singer) + 1);
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

    private AllStatisticDTO createResultObject(Map<SingerEntity, Integer> mapSinger, Map<GenreDTO, Integer> mapGenre, Map<String, LocalDateTime> mapUser) {
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
        Map<SingerEntity, Integer> mapSinger = new HashMap<>();
        Map<GenreDTO, Integer> mapGenre = new HashMap<>();
        Map<String, LocalDateTime> mapUser = new HashMap<>();
        List<SingerEntity> singerEntities = iSingerService.get();
        List<GenreDTO> genreDTOS = iGenreService.get();

        for (SingerEntity singer : singerEntities) {
            mapSinger.put(singer, 0);
        }
        for (GenreDTO genreDTO : genreDTOS) {
            mapGenre.put(genreDTO, 0);
        }

        calcVoice(mapSinger, mapGenre, mapUser, singerEntities, genreDTOS);

        return createResultObject(mapSinger, mapGenre, mapUser);
    }
}

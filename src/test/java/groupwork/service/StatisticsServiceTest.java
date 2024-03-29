package groupwork.service;

import groupwork.dao.memory.GenreDao;
import groupwork.dao.memory.SingerDao;
import groupwork.dao.memory.VotingDao;
import groupwork.dto.VoiceDTO;
import groupwork.service.api.IVotesService;
import groupwork.service.fabrics.MailServiceSingleton;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class StatisticsServiceTest {
//    private final IVotesService votesService = new VoteService(new VotingDao(), new SingerService(new SingerDao()), new GenreService(new GenreDao()), MailServiceSingleton.getInstance());
//
//    private final StatisticsService statisticsService = new StatisticsService(votesService, new SingerService(new SingerDao()), new GenreService(new GenreDao()));
//
//    @Test
//    public void testStatisticsServiceSingle() {
//        int singer = 2;
//        int[] genre = {1, 2, 3};
//        String message = "lololo";
//        String mail="ivanivanov2023_18@mail.ru";
//        votesService.save(new VoiceDTO(singer, genre, message,mail));
//        boolean result =statisticsService.getAllSort().getMapSingers().containsValue(1);
//        assertTrue(result);
//    }
//    @Test
//    public void testStatisticsServiceGenres() {
//        int singer = 2;
//        int[] genre = {1, 2, 3};
//        String message = "lololo";
//        String mail="ivanivanov2023_18@mail.ru";
//        votesService.save(new VoiceDTO(singer, genre, message,mail));
//        boolean result =statisticsService.getAllSort().getMapGenres().containsValue(1);
//        assertTrue(result);
//    }
//    @Test
//    public void testStatisticsServiceUserInfo() {
//        int singer = 2;
//        int[] genre = {1, 2, 3};
//        String message = "lololo";
//        String mail="ivanivanov2023_18@mail.ru";
//        votesService.save(new VoiceDTO(singer, genre, message,mail));
//        boolean result =statisticsService.getAllSort().getMapGenres().isEmpty();
//        assertFalse(result);
//    }
}
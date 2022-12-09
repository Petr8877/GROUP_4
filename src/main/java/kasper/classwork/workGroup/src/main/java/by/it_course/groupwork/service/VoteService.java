package by.it_course.groupwork.service;

import by.it_course.groupwork.dao2.api.IVotingDao;
import by.it_course.groupwork.dto.SavedVoiceDTO;
import by.it_course.groupwork.dto.VoiceDTO;
import by.it_course.groupwork.service.api.IGenreService;
import by.it_course.groupwork.service.api.ISingerService;
import by.it_course.groupwork.service.api.IVotesService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VoteService implements IVotesService {
    private final IVotingDao votingDao;

    private final ISingerService singerService;

    private final IGenreService genreService;

    public VoteService(IVotingDao voiceDao, ISingerService singerService, IGenreService genreService) {
        this.votingDao = voiceDao;
        this.singerService = singerService;
        this.genreService = genreService;
    }


    @Override
    public void save(VoiceDTO voice) {
        check(voice);
        votingDao.save(new SavedVoiceDTO(voice));
    }

    private void check(VoiceDTO voice) {
        String singer = voice.getSinger();

        if (singer == null || singer.isBlank()) {
            throw new IllegalArgumentException("Артист не введен");
        }

        if (singerService.checkName(voice.getSinger())) {
            throw new IllegalArgumentException("Артист " + singer + " отсутствует в списке выбора");
        }

        Set<String> genres = new HashSet<>(Arrays.asList(voice.getGenre()));

        if (genres == null) {
            throw new IllegalArgumentException("Жанры не переданы");
        }

        if (genres.size() < 3 || genres.size() > 5) {
            throw new IllegalArgumentException("Введите 3-5 не повторяющихся жанров");
        }

        for (String genre : genres) {
            if (genre == null) {
                throw new IllegalArgumentException("Жанр не введен");
            }

            if (!genreService.checkName(genre)) {
                throw new IllegalArgumentException("Введенный жанр не содержится в списке");
            }
        }

        String aboutMe = voice.getMessage();

        if (aboutMe == null || aboutMe.isBlank()) {
            throw new IllegalArgumentException("Нужно ввести информацию о себе");
        }

    }
}

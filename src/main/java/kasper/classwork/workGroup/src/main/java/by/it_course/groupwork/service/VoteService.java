package by.it_course.groupwork.service;

import by.it_course.groupwork.dao2.api.IVotingDao;
import by.it_course.groupwork.dto.SavedVoiceDTO;
import by.it_course.groupwork.dto.VoiceDTO;
import by.it_course.groupwork.service.api.IGenreService;
import by.it_course.groupwork.service.api.ISingerService;
import by.it_course.groupwork.service.api.IVotesService;

import java.util.*;

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

    @Override
    public List<SavedVoiceDTO> get() {
        return votingDao.getVoiceList();
    }

    private void check(VoiceDTO voice) {
        int singer = voice.getSinger();
        if (!singerService.checkNumber(voice.getSinger())) {
            throw new IllegalArgumentException("Артист №" + singer + " отсутствует в списке выбора");
        }

        int[] genres = voice.getGenre();

        Set<Integer> setGenre = new HashSet<>();

        for (int val : genres) {
            setGenre.add(val);
        }

        if (setGenre.size() < 3 || setGenre.size() > 5) {
            throw new IllegalArgumentException("Неверное количество жанров, должно быть от 3 до 5");
        }

        if(setGenre.size() != genres.length) {
            throw new IllegalArgumentException("Переданные жанры содержат дубли");
        }

        for (Integer genre : setGenre) {
            if (!genreService.check(genre)) {
                throw new IllegalArgumentException("Введенный жанр №" + genre + " не содержится в списке");
            }
        }

        String aboutMe = voice.getMessage();
        if (aboutMe == null || aboutMe.isBlank()) {
            throw new IllegalArgumentException("Нужно ввести информацию о себе");
        }
    }
}

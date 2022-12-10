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
        Integer singer = voice.getSinger();


        if (!singerService.checkNumber(voice.getSinger())) {
            throw new IllegalArgumentException("Артист " + singer + " отсутствует в списке выбора");
        }

        HashSet<Integer> genres = new HashSet<>();
        for (int val: voice.getGenre()) {
            genres.add(val);

        }



        if (genres.size() < 3 || genres.size() > 5) {
            throw new IllegalArgumentException("Введите 3-5 не повторяющихся жанров");
        }

        for (Integer genre : genres) {
            if (genre == null) {
                throw new IllegalArgumentException("Жанр не введен");
            }

            if (!genreService.check(genre)) {
                throw new IllegalArgumentException("Введенный жанр не содержится в списке");
            }
        }

        String aboutMe = voice.getMessage();

        if (aboutMe == null || aboutMe.isBlank()) {
            throw new IllegalArgumentException("Нужно ввести информацию о себе");
        }

    }
}

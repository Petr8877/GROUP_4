package groupwork.service;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import groupwork.service.api.IGenreService;
import groupwork.service.api.IMailService;
import groupwork.service.api.ISingerService;
import groupwork.service.api.IVotesService;

import java.util.*;

public class VoteService implements IVotesService {
    private final IVotingDao votingDao;

    private final ISingerService singerService;

    private final IGenreService genreService;
    private final IMailService mailService;

    public VoteService(IVotingDao voiceDao, ISingerService singerService, IGenreService genreService,
                       MailService mailService) {
        this.votingDao = voiceDao;
        this.singerService = singerService;
        this.genreService = genreService;
        this.mailService = mailService;

    }

    @Override
    public void save(VoiceDTO voice) {
        SavedVoiceDTO savedVoiceDTO = new SavedVoiceDTO(voice);
        votingDao.save(savedVoiceDTO);
        mailService.send(savedVoiceDTO);
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

        if (setGenre.size() != genres.length) {
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

//        String email = voice.getMail();
//        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!#$%&'*+/=?^_`{|},~\\-]+(?:\\\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~\\-]+)*@+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?+\\.+[a-zA-Z]*$");
//        if (!pattern.matcher(email).matches()){
//            throw new IllegalArgumentException("E-MAIL IS NOT CORRECT");
//        }
    }
}

package by.it_course.groupwork.service.api;

import by.it_course.groupwork.dto.VoiceDTO;

import java.util.List;

public interface IVotesService {
    /**
     * проверяет на корректность ввода (исполнителя и жанров)
     */
    void check(String singer, String[] genre, String aboutMe) throws Exception;

    /**
     * добавляет к о себе дату и время
     * сохраняет объект Vote в List<Vote> listVote
     */
    void save(String singer, String[] genre, String aboutMe);

    List<VoiceDTO> getAllVoice();
}

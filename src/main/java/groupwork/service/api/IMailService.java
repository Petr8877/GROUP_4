package groupwork.service.api;

import groupwork.dto.SavedVoiceDTO;


public interface IMailService {
    void send(SavedVoiceDTO savedVoiceEntity);
}

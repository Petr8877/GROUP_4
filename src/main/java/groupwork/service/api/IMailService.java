package groupwork.service.api;

import groupwork.dto.SavedVoiceEntity;


public interface IMailService {
    void send(SavedVoiceEntity savedVoiceEntity);
}

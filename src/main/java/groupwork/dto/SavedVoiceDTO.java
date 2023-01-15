package groupwork.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class SavedVoiceDTO {
    private VoiceDTO voice;
    private LocalDateTime creationTime;
    private Timestamp date;

    public SavedVoiceDTO(VoiceDTO voice) {
        this.voice = voice;
        this.creationTime = LocalDateTime.now();
    }

    public SavedVoiceDTO(VoiceDTO voice, Timestamp creationTime) {
        this.voice = voice;
        this.date = creationTime;
    }

    public VoiceDTO getVoice() {
        return voice;
    }

    public void setVoice(VoiceDTO voice) {
        this.voice = voice;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedVoiceDTO that = (SavedVoiceDTO) o;
        return Objects.equals(voice, that.voice) && Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voice, creationTime);
    }

    @Override
    public String toString() {
        return "Verified voice: " +
                "voice = " + voice +
                ", creationTime = " + creationTime;
    }
}

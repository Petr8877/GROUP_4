package groupwork.dto;

import java.util.Arrays;
import java.util.Objects;

public class VoiceDTO {
    private int singer;
    private int[] genre;
    private String message;
    private String mail;

    public VoiceDTO(int singer, int[] genre, String message, String mail) {
        this.mail = mail;
        this.singer = singer;
        this.genre = genre;
        this.message = message;
    }

    public String getMail() {
        return mail;
    }

    public int getSinger() {
        return singer;
    }

    public int[] getGenre() {
        return genre;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoiceDTO voiceDTO = (VoiceDTO) o;
        return Objects.equals(singer, voiceDTO.singer)
                && Arrays.equals(genre, voiceDTO.genre)
                && Objects.equals(message, voiceDTO.message)
                && Objects.equals(mail, voiceDTO.mail);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(singer, message, mail);
        result = 31 * result + Arrays.hashCode(genre);
        return result;
    }

    @Override
    public String toString() {
        return "Accepted voice: " +
                "singer = " + singer +
                ", genre = " + Arrays.toString(genre) +
                ", message = " + message +
                ",mail = " + mail;
    }
}

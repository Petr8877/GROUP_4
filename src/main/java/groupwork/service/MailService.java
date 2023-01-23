package groupwork.service;


import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import groupwork.service.api.IGenreService;
import groupwork.service.api.IMailService;
import groupwork.service.api.ISingerService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class MailService  implements IMailService {
    private Properties properties;
    private ISingerService singerService;
    private IGenreService genreService;

    public MailService(Properties properties, ISingerService singerService, IGenreService genreService) {
        this.properties = properties;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    @Override
    public void send (SavedVoiceDTO savedVoiceDTO, int id) {

        try {
            Session session = new SessionCreator(properties).createSession();
            session.setDebug(true);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.user.name")));
            String email = savedVoiceDTO.getVoice().getMail();
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("You have successfully voted");
            String msg =  createMailText(savedVoiceDTO, id);
            message.setText(msg);
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private String createMailText(SavedVoiceDTO savedVoiceDTO, int id){
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        VoiceDTO voice = savedVoiceDTO.getVoice();

        int singerID = voice.getSinger();
        int[] genreID = voice.getGenre();
        String message = voice.getMessage();
        LocalDateTime creationTime = savedVoiceDTO.getCreationTime();

        String singer = singerService.get(singerID);

        builder.append("Ваш голос:").append("\n").append("Исполнитель - ").append(singer).append("\n").append("\n")
                .append("Жанры - ");

        for (int genre : genreID) {
            builder.append(genreService.get(genre)).append(", ").append("\n");
        }

        builder.append("\n").append("Информация о себе - ").append(message).append("\n").append("\n")
                .append("Дата и время голосования - ").append(dtf.format(creationTime)).append("\n");

        builder.append("\n").append("\n");

        int random = (int) (Math.random()*100);

        builder.append("Для подтверждения перейдите по следуещей ссылке:").append("\n").append("http://localhost:8080/groupwork/check?id="
                + id + "&key="+savedVoiceDTO.getKey());
        return builder.toString();
    }
}

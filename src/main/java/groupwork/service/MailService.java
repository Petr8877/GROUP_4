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
    public void send (SavedVoiceDTO savedVoiceEntity) {

        try {
            Session session = new SessionCreator(properties).createSession();
            session.setDebug(true);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.user.name")));
            String email = savedVoiceEntity.getVoice().getMail();
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("You have successfully voted");
            String msg =  createMailText(savedVoiceEntity);
            message.setText(msg);
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private String createMailText(SavedVoiceDTO savedVoiceEntity){
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        VoiceDTO voice = savedVoiceEntity.getVoice();

        int singerID = voice.getSinger();
        int[] genreID = voice.getGenre();
        String message = voice.getMessage();
        LocalDateTime creationTime = savedVoiceEntity.getCreationTime();

        String singer = singerService.get(singerID).getName();

        builder.append("Ваш голос: исполнитель - ").append(singer)
                .append(", жанры - ");

        for (int genre : genreID) {
            builder.append(genreService.get(genre)).append(", ");
        }

        builder.append("информация о себе - ").append(message)
                .append(", дата и время голосования - ").append(dtf.format(creationTime));

        return builder.toString();
    }
}

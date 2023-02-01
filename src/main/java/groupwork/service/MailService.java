package groupwork.service;


import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import groupwork.entity.GenreEntity;
import groupwork.entity.SavedVoice;
import groupwork.entity.SingerEntity;
import groupwork.service.api.IGenreService;
import groupwork.service.api.IMailService;
import groupwork.service.api.ISingerService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class MailService implements IMailService {
    private Properties properties;
    private ISingerService singerService;
    private IGenreService genreService;

    public MailService(Properties properties, ISingerService singerService, IGenreService genreService) {
        this.properties = properties;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    @Override
    public void send(SavedVoice savedVoiceEntity, long id) {
        boolean send = false;
        int count = 0;

        Session session = new SessionCreator(properties).createSession();
        session.setDebug(true);
        Message message = new MimeMessage(session);
        while (!send && count <= 5) {
            try {
                count++;
                message.setFrom(new InternetAddress(properties.getProperty("mail.user.name")));
                String email = savedVoiceEntity.getEmail();
                message.setRecipients(
                        Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("You have successfully voted");
                String msg = createMailText(savedVoiceEntity, id);
                message.setText(msg);
                Transport.send(message);
                send = true;
            } catch (Exception e) {
                try {
                    Thread.sleep(300000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private String createMailText(SavedVoice savedVoiceEntity, long id) {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        //VoiceDTO voice = savedVoiceEntity.getVoice();

        //int singerID =  voice.getSinger();
        SingerEntity singerEntity = savedVoiceEntity.getSinger();
        //int[] genreID = voice.getGenre();
        List<GenreEntity> genreEntities = savedVoiceEntity.getGenres();
        String message = savedVoiceEntity.getMessage();
        LocalDateTime creationTime = savedVoiceEntity.getCreationTime();

        String singer = singerEntity.getName();

        builder.append("Ваш голос:").append("\n").append("Исполнитель - ").append(singer)
                .append("\n").append("Жанры - ");

        for (GenreEntity genreEntity : genreEntities) {
            builder.append(genreEntity.getName()).append(", ");
        }
//        for (int genre : genreID) {
//            builder.append(genreService.get(genre)).append(", ");
//        }
        builder.append("\n");

        builder.append("Информация о себе - ").append(message).append("\n")
                .append("Дата и время голосования - ").append(dtf.format(creationTime));

        builder.append("\n").append("\n");

        builder.append("Для подтверждения перейдите по следующей ссылке:").append("\n")
                .append("http://localhost:8080/groupwork/check?id=").append(id).append("&key=")
                .append(savedVoiceEntity.getKey());

        return builder.toString();
    }
}

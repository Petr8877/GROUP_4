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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;


public class MailService implements IMailService, Runnable {
    private Properties properties;
    private ISingerService singerService;
    private IGenreService genreService;
    private LinkedTransferQueue<Message> queue = new LinkedTransferQueue<>();

    public MailService(Properties properties, ISingerService singerService, IGenreService genreService) {
        this.properties = properties;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    @Override
    public void send(SavedVoiceDTO savedVoiceDTO, int id) {
        boolean send = false;
        Session session = new SessionCreator(properties).createSession();
        session.setDebug(true);
        Message message = new MimeMessage(session);
        while (!send) {


            try {
                message.setFrom(new InternetAddress(properties.getProperty("mail.user.name")));
                String email = savedVoiceDTO.getVoice().getMail();
                message.setRecipients(
                        Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("You have successfully voted");
                String msg = createMailText(savedVoiceDTO, id);
                message.setText(msg);
                queue.add(message);
                Transport.send(queue.take());
                queue.remove(message);
                send = true;
            } catch (Exception e) {
//                Timer timer = new Timer();
//
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        try {
//                            Transport.send(message);
//                        } catch (MessagingException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    }
//                }, 100,100);
              // timer.cancel();
//                try {
//                    queue.remove(queue.take());
//                    send = true;
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//                try {
//                    Thread.sleep(1000);
//                   // timer.wait(100);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }

//                try {
//                    Thread.sleep(300000); // очень плохо
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
            }
        }
    }

    private String createMailText(SavedVoiceDTO savedVoiceDTO, int id) {
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


        builder.append("Для подтверждения перейдите по следуещей ссылке:").append("\n").append("http://localhost:8080/groupwork/check?id=").append(id).append("&key=").append(savedVoiceDTO.getKey());
        return builder.toString();
    }

    @Override
    public void run() {
//        send();
    }
}

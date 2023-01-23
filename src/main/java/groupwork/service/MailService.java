package groupwork.service;


import groupwork.dto.SavedVoiceDTO;
import groupwork.service.api.IGenreService;
import groupwork.service.api.IMailService;
import groupwork.service.api.ISingerService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
//    public MailService() {
//        this.username = USERNAME;
//        this.password = PASSWORD;
//        props = new Properties();
//        props.put("mail.smtp.host", "smtp.mail.ru");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//    }
    @Override
    public void send (SavedVoiceDTO savedVoiceDTO) {

        try {
            Session session = new SessionCreator(properties).createSession();
            session.setDebug(true);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.user.name")));
            String email = savedVoiceDTO.getVoice().getMail();
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("you have successfully voted");
            String msg =  createMailText(savedVoiceDTO);
            message.setText(msg);
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private String createMailText(SavedVoiceDTO savedVoiceDTO){

        StringBuilder stringBuilder = new StringBuilder();
        String time = savedVoiceDTO.getCreationTime().toString();
        stringBuilder.append("Your voice added at ").append(time);

        int singer = savedVoiceDTO.getVoice().getSinger();
        String name = singerService.get(singer);
        stringBuilder.append("\nArtist:\n").append(name);
        stringBuilder.append("\nGenres:\n");
        for (int v : savedVoiceDTO.getVoice().getGenre()) {
            String name1 = genreService.get(v);
            stringBuilder.append(name1).append("\n");
        }
        stringBuilder.append("Message:\n").append(savedVoiceDTO.getVoice().getMessage());


        return stringBuilder.toString();
    }
}

//    public void send(String subject, SavedVoiceDTO info, String toEmail) {
//        Session session = Session.getDefaultInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            //от кого
//            message.setFrom(new InternetAddress(username));
//            //кому
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//            //тема сообщения
//            message.setSubject(subject);
//            //текст
//            message.setText(info.toString());
//
//            //отправляем сообщение
//            Transport.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
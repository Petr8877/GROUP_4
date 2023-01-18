package groupwork.service;


import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class SendEMail {
    /*    private static final String USERNAME = "ivanivanov2023_18@mail.ru";
        private static final String PASSWORD = "C3apO%TrjdA2";
        private static final String EMAIL_TO = "";*/
    private String username;
    private String password;
    private Properties props;

    public SendEMail(String username, String password) {
        this.username = username;
        this.password = password;
        props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public void send(String subject, List<SavedVoiceDTO> info, String toEmail) {
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //тема сообщения
            message.setSubject(subject);
            //текст
            message.setText(info.toString());

            //отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
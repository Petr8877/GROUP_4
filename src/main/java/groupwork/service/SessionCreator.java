package groupwork.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {
    public static final String MAIL_USER_PASSWORD = "mail.user.password";
//    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
//    private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
//    private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
//    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
//    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_USER_NAME = "mail.user.name";
    private String password;
    private String username;
    private Properties sessionProperties;

    public SessionCreator(Properties configProperties) {
        username = configProperties.getProperty(MAIL_USER_NAME);
        password = configProperties.getProperty(MAIL_USER_PASSWORD);

        this.sessionProperties = configProperties;
//        sessionProperties.setProperty(MAIL_SMTP_HOST,configProperties.getProperty(MAIL_SMTP_HOST));
//        sessionProperties.setProperty(MAIL_SMTP_SOCKET_FACTORY_PORT,configProperties.getProperty(MAIL_SMTP_SOCKET_FACTORY_PORT));
//        sessionProperties.setProperty(MAIL_SMTP_SOCKET_FACTORY_CLASS,configProperties.getProperty(MAIL_SMTP_SOCKET_FACTORY_CLASS));
//        sessionProperties.setProperty(MAIL_SMTP_AUTH, configProperties.getProperty(MAIL_SMTP_AUTH));
//        sessionProperties.setProperty(MAIL_SMTP_PORT, configProperties.getProperty(MAIL_SMTP_PORT));
    }
    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}

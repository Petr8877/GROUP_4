package groupwork.service.fabrics;

import groupwork.service.SendEMailService;


public class SendEMailSingleton {
    private volatile static SendEMailService instance;

    private SendEMailSingleton() {
    }

    public static SendEMailService getInstance() {
        if (instance == null) {
            synchronized (SendEMailSingleton.class) {
                if (instance == null) {
                    instance = new SendEMailService();
                }
            }
        }
        return instance;
    }
}

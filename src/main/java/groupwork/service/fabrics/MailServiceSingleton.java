package groupwork.service.fabrics;

import groupwork.dao.db.ds.fabrics.DataSourceSingleton;
import groupwork.service.MailService;

import java.util.Properties;


public class MailServiceSingleton {
    private volatile static MailService instance;
    private static Properties properties;

    private MailServiceSingleton() {
    }

    public static void setProperties(Properties properties) {
        synchronized (DataSourceSingleton.class) {
            if (instance != null) {
                throw new IllegalStateException("нельзя изменить настройки когда уже было подкл к бд");
            }
            MailServiceSingleton.properties = properties;
        }
    }

    public static MailService getInstance() {
        if (instance == null) {
            synchronized (MailServiceSingleton.class) {
                if (instance == null) {
                    instance = new MailService(properties,
                            SingersServiceSingleton.getInstance(),
                            GenresServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

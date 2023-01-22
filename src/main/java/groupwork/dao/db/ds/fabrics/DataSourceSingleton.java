package groupwork.dao.db.ds.fabrics;

import groupwork.dao.db.ds.DataSourceC3PO;
import groupwork.dao.db.ds.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;
import java.util.Properties;

public class DataSourceSingleton {
    private static Properties properties;
    private volatile static IDataSourceWrapper instance;

    public DataSourceSingleton() {
    }

    public static void setProperties(Properties properties) {
        synchronized (DataSourceSingleton.class) {
            if (instance != null) {
                throw new IllegalStateException("Нельзя менять настройки когда уже было создано подключение к бд");
            }
            DataSourceSingleton.properties = properties;
        }
    }

    public static IDataSourceWrapper getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (DataSourceSingleton.class) {
                if (instance == null) {
                    instance = new DataSourceC3PO(properties);
                }
            }
        }
        return instance;
    }
}

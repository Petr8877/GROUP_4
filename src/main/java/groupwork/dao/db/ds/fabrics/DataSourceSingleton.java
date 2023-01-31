package groupwork.dao.db.ds.fabrics;

import groupwork.dao.db.ds.DataSourceC3P0;
import groupwork.dao.db.ds.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;
import java.util.Properties;
//убрать
public class DataSourceSingleton {
    private static Properties properties;
    private volatile static IDataSourceWrapper instance;
    private DataSourceSingleton(){

    }
//можно сделать так упрощенно так ка редко выбирают
    public static void setProperties(Properties properties) {
        synchronized (DataSourceSingleton.class) {
            if (instance != null) {
                throw new IllegalStateException("нельзя изменить настройки когда уже было подкл к бд");
            }
            DataSourceSingleton.properties = properties;
        }
    }

    public static IDataSourceWrapper getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (DataSourceSingleton.class) {
                if (instance == null) {
                    instance = new DataSourceC3P0(properties);
                }
            }
        }
        return instance;
    }
}

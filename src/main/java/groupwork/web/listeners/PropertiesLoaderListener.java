package groupwork.web.listeners;

import groupwork.dao.db.ds.api.IDataSourceWrapper;
import groupwork.dao.db.ds.fabrics.DataSourceSingleton;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        File confDir = new File(System.getenv("catalina_base") + "/conf");
        File prop = new File(confDir + "/application.properties");
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(prop));
            DataSourceSingleton.setProperties(properties);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Файл с настройкпми не найден");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        IDataSourceWrapper iDataSourceWrapper = (IDataSourceWrapper) servletContext.getAttribute("DateSource");
        try {
            iDataSourceWrapper.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

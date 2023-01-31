package groupwork.dao.db.ds;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import groupwork.dao.db.ds.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceC3P0 implements IDataSourceWrapper {
    private static final String DRIVER_CLASS_PROPERTY_NAME ="db.c3p0.class";//"org.postgresql.Driver";
    private static  final String URL_PROPERTY_NAME ="db.c3p0.url";//"jdbc:postgresql://localhost:5432/voting";
    private static  final String USER_PROPERTY_NAME ="db.c3p0.user";//"postgres";
    private static  final String PASSWORD_PROPERTY_NAME ="db.c3p0.password";//"pass";

    private ComboPooledDataSource dataSource;

    public DataSourceC3P0(Properties properties) throws PropertyVetoException {
        dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty(DRIVER_CLASS_PROPERTY_NAME)); //"db.c3p0.class"
        dataSource.setJdbcUrl(properties.getProperty(URL_PROPERTY_NAME));//"db.c3p0.url"
        dataSource.setUser(properties.getProperty(USER_PROPERTY_NAME)); // "db.c3p0.user"
        dataSource.setPassword(properties.getProperty(PASSWORD_PROPERTY_NAME)); // "db.c3p0.password"
       }


    @Override
    public void close() throws Exception {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

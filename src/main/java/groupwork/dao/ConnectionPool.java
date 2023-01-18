package groupwork.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


class ConnectionPool {
    private static final ComboPooledDataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/voting");// todo change port
            dataSource.setUser("postgres"); // todo change name
            dataSource.setPassword("pass"); // todo change password
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    private ConnectionPool(){
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
}

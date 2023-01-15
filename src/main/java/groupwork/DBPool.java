package groupwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBPool {
    private static final String url = "jdbc:postgresql://localhost:5434/voting";
    private static final String user = "postgres";
    private static final String password = "kasper";

    public DBPool() throws ClassNotFoundException {
        LoadDriver.loading();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void putConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

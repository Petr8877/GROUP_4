package groupwork.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionBuilder {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private ConnectionBuilder(){

    }
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/voting";
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "pass");
        properties.put("autoRecconect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("userUnicode", "true");
        return  DriverManager.getConnection
                (url,properties);
    }
}

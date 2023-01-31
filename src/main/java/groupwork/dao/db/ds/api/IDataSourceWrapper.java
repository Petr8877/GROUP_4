package groupwork.dao.db.ds.api;

import java.sql.Connection;
import java.sql.SQLException;
//убрать
public interface IDataSourceWrapper extends AutoCloseable{
    Connection getConnection() throws SQLException;
}

package groupwork.dao.DB;

import groupwork.DBPool;
import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoDB implements ISingerDao {
    private final String INSERT = "INSERT INTO app.artists(name) VALUES (?);";
    private final String DELETE = "DELETE FROM app.artists WHERE name = ?;";
    private final String UPDATE = "UPDATE app.artists SET name=? WHERE id = ?;";
    private final String GET_ALL = "SELECT id, name FROM app.artists;";
    private final DBPool dbPool;

    {
        try {
            dbPool = new DBPool();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String singer) {
        try {
            Connection conn = dbPool.getConnection();
            long x = -1;
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
            preparedStatement.setString(1, singer);
            preparedStatement.executeUpdate();
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String singer) {
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE);
            preparedStatement.setString(1, singer);
            preparedStatement.executeUpdate();
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(long id, String name) {
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SingerDTO> getList() {
        List<SingerDTO> singerDTOList = new ArrayList<>();
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                singerDTOList.add(new SingerDTO(resultSet.getString("name"), resultSet.getLong("id")));
            }
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singerDTOList;
    }

    @Override
    public boolean isContain(int id) {
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getLong("id") == id) {
                    return true;
                }
            }
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

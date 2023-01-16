package groupwork.dao.DB;

import groupwork.DBPool;
import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GenreDaoDB implements IGenreDao {
    private final String INSERT = "INSERT INTO app.genres(name) VALUES (?);";
    private final String DELETE = "DELETE FROM app.genres WHERE id = ?;";
    private final String UPDATE = "UPDATE app.genres SET name=? WHERE id = ?;";
    private final String GET_ALL = "SELECT id, name FROM app.genres;";
    private final DBPool dbPool;

    {
        try {
            dbPool = new DBPool();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String genre) {
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
            preparedStatement.setString(1, genre);
            preparedStatement.executeUpdate();
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
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
    public List<GenreDTO> getList() {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genreDTOList.add(new GenreDTO(resultSet.getString("name"), resultSet.getLong("id")));
            }
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genreDTOList;
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

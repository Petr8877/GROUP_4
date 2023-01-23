package groupwork.dao.db;


import groupwork.dao.api.IGenreDao;
import groupwork.dao.db.ds.api.IDataSourceWrapper;
import groupwork.dto.GenreDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO_DB implements IGenreDao {

    private final String SQL_GET = "SELECT id, name FROM app.genres;";
    private final String SQL_GET_NAME = "SELECT name FROM app.genres WHERE id = ?";
    private final String SQL_IS_CONTAIN = "SELECT id FROM app.genres WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM app.genres WHERE id = ?;";
    private final String SQL_CREATE = "INSERT INTO app.genres (name) VALUES (?);";
    private final String SQL_UPDATE = "UPDATE app.genres SET name = ? WHERE id = ?;";
    private final IDataSourceWrapper dataSourceWrapper;

    public GenreDAO_DB(IDataSourceWrapper dataSourceWrapper) {
        this.dataSourceWrapper = dataSourceWrapper;
    }

    @Override
    public List<GenreDTO> getGenreList() {
        List<GenreDTO>list = new ArrayList<>();

        try(Connection connection =dataSourceWrapper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new GenreDTO(name, id));
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

        return list;
    }

    @Override
    public boolean isContain(int id) {
        boolean result = false;

        try(Connection connection = dataSourceWrapper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_CONTAIN)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                result = true;
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
        return result;
    }

    @Override
    public void delete(int id) {

        try (Connection connection = dataSourceWrapper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
    }

    @Override
    public void create(String name) {

        try(Connection connection = dataSourceWrapper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)){

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
    }

    @Override
    public void update(int id, GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        try(Connection connection = dataSourceWrapper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){

            preparedStatement.setString(1, genre);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

    }

    @Override
    public String get(Integer id) {
        String name = null;
        try (Connection connection = dataSourceWrapper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_NAME)
        ) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}

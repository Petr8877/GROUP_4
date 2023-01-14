package groupwork.dao;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoDB implements IGenreDao {

    private static final String SELECT_ID_GENRE_NAME = "SELECT id_genre, name FROM app.genres";
    private static final String SELECT_ID = "SELECT id_genre FROM app.genres WHERE id_genre = (?)";
    private static final String INSERT_INTO_APP_GENRES_NAME = "INSERT INTO app.genres (name) VALUES (?)";
    private static final String UPDATE_APP_GENRES = "UPDATE app.genres SET name = (?) WHERE id_genre = (?) RETURNING id_genre, name;";
    private static final String DELETE_FROM_GENRES = "DELETE FROM app.genres WHERE id_genre = (?)";
//    RETURNING*;

    public GenreDaoDB() {

    }


    @Override
    public List<GenreDTO> getAll() {
        List<GenreDTO> genres = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ID_GENRE_NAME)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GenreDTO genreDTO = new GenreDTO();
                genreDTO.setId(resultSet.getInt("id_genre"));
                genreDTO.setName(resultSet.getString("name"));
                genres.add(genreDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }


        @Override
    public boolean isContain(Integer id) {
        boolean flag;
            try (Connection connection = ConnectionBuilder.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_ID)
            ) {
                statement.setInt(1,id);
                ResultSet resultSet = statement.executeQuery();
                flag = (resultSet != null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return flag;
    }

    @Override
    public boolean insert(GenreDTO genreDTO) {
        boolean flag;
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_APP_GENRES_NAME)
        ) {
            String name = genreDTO.getName();
            statement.setString(1, name);
            flag = (statement.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    @Override
    public GenreDTO update(GenreDTO genreDTO) {
        GenreDTO update_genreDTO = new GenreDTO();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_APP_GENRES)
        ) {
            statement.setString(1,genreDTO.getName());
            statement.setInt(2,genreDTO.getId());
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                update_genreDTO.setId(resultSet.getInt("id_genre"));
                update_genreDTO.setName(resultSet.getString("name"));
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update_genreDTO;
    }
        @Override
    public boolean delete(GenreDTO genreDTO) {
        boolean flag;
            try (Connection connection = ConnectionBuilder.getConnection();
                 PreparedStatement statement = connection.prepareStatement(DELETE_FROM_GENRES)
            ) {
                statement.setInt(1,genreDTO.getId());
                flag = (statement.executeUpdate() != 0);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return flag;
    }
}

package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SingerDaoDB implements ISingerDao {
    private static final String SELECT_ID_SINGERS_NAME = "SELECT id_artist, name FROM app.artists";
    private static final String SELECT_ID = "SELECT id_artist FROM app.artists WHERE id_artist = (?)";
    private static final String INSERT_INTO_APP_SINGERS_NAME = "INSERT INTO app.artists (name) VALUES (?)";
    private static final String UPDATE_SINGERS = "UPDATE app.artists SET name = (?) WHERE id_artist = (?) RETURNING id_artist, name;";
    private static final String DELETE_FROM_SINGERS = "DELETE FROM app.artists WHERE id_artist = (?)";
    public SingerDaoDB() {
    }

    @Override
    public List<SingerDTO> getAll() {
        List<SingerDTO> singers = new ArrayList<>();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ID_SINGERS_NAME)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SingerDTO singerDTO = new SingerDTO();
                singerDTO.setId(resultSet.getInt("id_artist"));
                singerDTO.setName(resultSet.getString("name"));
                singers.add(singerDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singers;
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
    public boolean insert(SingerDTO singerDTO) {
        boolean flag;
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_APP_SINGERS_NAME)
        ) {
            String name = singerDTO.getName();
            statement.setString(1, name);
            flag = (statement.executeUpdate() != 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    @Override
    public SingerDTO update(SingerDTO singerDTO) {
        SingerDTO update_singerDTO = new SingerDTO();
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SINGERS)
        ) {
            statement.setString(1,singerDTO.getName());
            statement.setInt(2,singerDTO.getId());
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                update_singerDTO.setId(resultSet.getInt("id_artist"));
                update_singerDTO.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update_singerDTO;
    }

    @Override
    public boolean delete(SingerDTO singerDTO) {
        boolean flag;
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FROM_SINGERS)
        ) {
            statement.setInt(1,singerDTO.getId());
            flag = (statement.executeUpdate() != 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

}

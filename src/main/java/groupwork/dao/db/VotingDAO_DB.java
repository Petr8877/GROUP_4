package groupwork.dao.db;

import groupwork.dao.api.IVotingDao;
import groupwork.dao.db.ds.api.IDataSourceWrapper;
import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VotingDAO_DB implements IVotingDao {

    private final String SQL_GET = "SELECT app.votes.id, dt_create, about, id_artist, id_genre, mail " +
            "FROM app.votes " +
            "INNER JOIN app.vote_artists ON app.votes.id = app.vote_artists.id_user " +
            "INNER JOIN app.vote_genre ON app.votes.id = app.vote_genre.id_user;";

    private final String SQL_INSERT_VOICE = "INSERT INTO app.votes (dt_create, about, mail) VALUES (?,?,?) RETURNING id;";

    private final String SQL_INSERT_SINGER = "INSERT INTO app.vote_artists (id_user, id_artist) VALUES (?, ?);";

    private final String SQL_INSERT_GENRE = "INSERT INTO app.vote_genre (id_user, id_genre) VALUES (?, ?);";
    private final IDataSourceWrapper dataSourceWrapper;

    public VotingDAO_DB(IDataSourceWrapper dataSourceWrapper) {
        this.dataSourceWrapper = dataSourceWrapper;
    }

    @Override
    public List<SavedVoiceDTO> getVoiceList() {
        List<SavedVoiceDTO> list = new ArrayList<>();

        try (Connection connection = dataSourceWrapper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                List<Integer> genreList = new ArrayList<>();
                int currId = resultSet.getInt("id");
                LocalDateTime dtCreate = resultSet.getObject("dt_create", LocalDateTime.class);
                String about = resultSet.getString("about");
                String mail = resultSet.getString("mail");
                int singer = resultSet.getInt("id_artist");
                int genre = resultSet.getInt("id_genre");
                genreList.add(genre);

                while (resultSet.next()) {
                    if (resultSet.getInt("id") == currId) {
                        genreList.add(resultSet.getInt("id_genre"));
                    } else {
                        resultSet.previous();
                        break;
                    }
                }

                int[] genres = genreList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();

                SavedVoiceDTO voice = new SavedVoiceDTO(
                        new VoiceDTO(singer, genres, about, mail),
                        dtCreate);

                list.add(voice);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }

        return list;

    }

    @Override
    public void save(SavedVoiceDTO voice) {

        VoiceDTO voiceDTO = voice.getVoice();

        int singer = voiceDTO.getSinger();
        int[] genres = voiceDTO.getGenre();
        String message = voiceDTO.getMessage();
        LocalDateTime creationTime = voice.getCreationTime();
        String mail = voiceDTO.getMail();
        int id = 0;

        try (Connection connection = dataSourceWrapper.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_VOICE);

            preparedStatement.setObject(1, creationTime);
            preparedStatement.setString(2, message);
            preparedStatement.setString(3, mail);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            preparedStatement = connection.prepareStatement(SQL_INSERT_SINGER);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, singer);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_INSERT_GENRE);

            for (int genre : genres) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, genre);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }

    }
}

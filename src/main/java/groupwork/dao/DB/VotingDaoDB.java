package groupwork.dao.DB;

import groupwork.DBPool;
import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VotingDaoDB implements IVotingDao {

    private static final String URL_DB = "jdbc:postgresql://localhost:5434/voting";
    private static final String USER_DB = "postgres";
    private static final String PASSWORD_DB = "kasper";
    private final DBPool dbPool;

    {
        try {
            dbPool = new DBPool(URL_DB, USER_DB, PASSWORD_DB);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SavedVoiceDTO> getVoiceList() {
        List<SavedVoiceDTO> list = new ArrayList<>();
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, dt_create, about FROM app.votes;");
            ResultSet resultSet = preparedStatement.executeQuery();
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void save(SavedVoiceDTO voice) {
        check(voice);
        int singer = voice.getVoice().getSinger();
        int[] genres = voice.getVoice().getGenre();
        String about = voice.getVoice().getMessage();
        Date date = voice.getDate();                    // костыль
        try {
            Connection conn = dbPool.getConnection();
            long newId = -1;

            PreparedStatement preparedStatement1 = conn.prepareStatement(
                    "INSERT INTO app.votes(about, dt_create) VALUES (?, ?);",
                    new String[]{"id"});
            preparedStatement1.setString(1, about);
            preparedStatement1.setDate(2, date);
            preparedStatement1.executeUpdate();
            ResultSet resultSet = preparedStatement1.getGeneratedKeys();
            if (resultSet.next()) {
                newId = resultSet.getLong("id");
            }

            PreparedStatement preparedStatement2 = conn.prepareStatement("INSERT INTO app.voteartists(id_artist, id_user) VALUES (?, ?);");
            preparedStatement2.setLong(1, singer);
            preparedStatement2.setLong(2, newId);
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3 = conn.prepareStatement("INSERT INTO app.votegenres(id_genre, id_user) VALUES (?, ?);");
            for (int genre : genres) {
                preparedStatement3.setLong(1, genre);
                preparedStatement3.setLong(2, newId);
                preparedStatement3.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void check(SavedVoiceDTO voice) {
        int singer = voice.getVoice().getSinger();
        int[] genres = voice.getVoice().getGenre();
        String about = voice.getVoice().getMessage();
        if (about == null || about.isBlank()) {
            throw new IllegalArgumentException("Информация о себе отсутствует");
        }
        if (genres.length < 3 || genres.length > 5) {
            throw new IllegalArgumentException("Выберете от 3 до 5 жанров");
        }
        try {
            Connection conn = dbPool.getConnection();
            PreparedStatement preparedStatementGenre = conn.prepareStatement("SELECT id FROM app.genres;");
            ResultSet resultSetGenre = preparedStatementGenre.executeQuery();
            Set<Long> setGenre = new HashSet<>();
            while (resultSetGenre.next()) {
                setGenre.add(resultSetGenre.getLong("id"));
            }
            for (int genre : genres) {
                if (!setGenre.contains((long) genre)) {
                    throw new IllegalArgumentException("Введенный жанр №" + genre + " отсутствует в списке");
                }
            }
            PreparedStatement preparedStatementArtist = conn.prepareStatement("SELECT id FROM app.genres;");
            ResultSet resultSetArtist = preparedStatementArtist.executeQuery();
            Set<Long> setArtist = new HashSet<>();
            while (resultSetArtist.next()) {
                setArtist.add(resultSetArtist.getLong("id"));
            }
            if (!setArtist.contains((long) singer)) {
                throw new IllegalArgumentException("Введенный исполнитель №" + singer + " отсутствует в списке");
            }
            dbPool.putConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

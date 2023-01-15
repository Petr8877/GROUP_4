package groupwork.dao.DB;

import groupwork.DBPool;
import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VotingDaoDB implements IVotingDao {
    private final String GET_ALL_VOTES = "SELECT id, dt_create, about FROM app.votes;";
    private final String GET_ID_ARTIST_WHERE = "SELECT id_artist FROM app.voteartists WHERE id_user = (?);";
    private final String GET_ID_GENRE_WHERE = "SELECT id_genre FROM app.votegenres WHERE id_user = (?);";
    private final String GET_VOTES_WITHOUT_ID = "SELECT dt_create, about FROM app.votes WHERE id = (?);";
    private final String INSERT_VOICE = "INSERT INTO app.votes(about, dt_create) VALUES (?, ?);";
    private final String INSERT_ARTIST = "INSERT INTO app.voteartists(id_artist, id_user) VALUES (?, ?);";
    private final String INSERT_GENRE = "INSERT INTO app.votegenres(id_genre, id_user) VALUES (?, ?);";
    private final String GET_ID_GENRE = "SELECT id FROM app.genres;";
    private final String GET_ID_ARTIST = "SELECT id FROM app.artists;";
    private final DBPool dbPool;

    {
        try {
            dbPool = new DBPool();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SavedVoiceDTO> getVoiceList() {
        List<SavedVoiceDTO> list = new ArrayList<>();
        try {
            List<Long> listID = new ArrayList<>();
            Connection conn = dbPool.getConnection();
            PreparedStatement psVotes = conn.prepareStatement(GET_ALL_VOTES);
            ResultSet rsVotes = psVotes.executeQuery();
            while (rsVotes.next()) {
                listID.add(rsVotes.getLong("id"));
            }
            for (Long aLong : listID) {
                long sing = 0;
                List<Long> genre = new ArrayList<>();
                String about = "";
                Timestamp date = null;
                PreparedStatement psArtistVotes = conn.prepareStatement(GET_ID_ARTIST_WHERE);
                psArtistVotes.setLong(1, aLong);
                ResultSet rsArtistVotes = psArtistVotes.executeQuery();
                if (rsArtistVotes.next()) {
                    sing = rsArtistVotes.getLong("id_artist");
                }
                PreparedStatement psGenreVotes = conn.prepareStatement(GET_ID_GENRE_WHERE);
                psGenreVotes.setLong(1, aLong);
                ResultSet rsGenreVotes = psGenreVotes.executeQuery();
                while (rsGenreVotes.next()) {
                    genre.add(rsGenreVotes.getLong("id_genre"));
                }
                int[] tmp = new int[genre.size()];
                for (int i = 0; i < genre.size(); i++) {
                    tmp[i] = Math.toIntExact(genre.get(i));
                }

                PreparedStatement psVotes1 = conn.prepareStatement(GET_VOTES_WITHOUT_ID);
                psVotes1.setLong(1, aLong);
                ResultSet rsVotes1 = psVotes1.executeQuery();
                if (rsVotes1.next()) {
                    date = rsVotes1.getTimestamp("dt_create");
                    about = rsVotes1.getString("about");

                }
                list.add(new SavedVoiceDTO(new VoiceDTO((int) sing, tmp, about), date));
            }
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
        //Timestamp date = Timestamp.valueOf(voice.getCreationTime());
        try {
            Connection conn = dbPool.getConnection();
            long newId = -1;

            PreparedStatement preparedStatement1 = conn.prepareStatement(INSERT_VOICE, new String[]{"id"});
            preparedStatement1.setString(1, about);
            //preparedStatement1.setTimestamp(2, date);
            preparedStatement1.setObject(2, voice.getCreationTime());
            preparedStatement1.executeUpdate();
            ResultSet resultSet = preparedStatement1.getGeneratedKeys();
            if (resultSet.next()) {
                newId = resultSet.getLong("id");
            }

            PreparedStatement preparedStatement2 = conn.prepareStatement(INSERT_ARTIST);
            preparedStatement2.setLong(1, singer);
            preparedStatement2.setLong(2, newId);
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3 = conn.prepareStatement(INSERT_GENRE);
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
            PreparedStatement preparedStatementGenre = conn.prepareStatement(GET_ID_GENRE);
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
            PreparedStatement preparedStatementArtist = conn.prepareStatement(GET_ID_ARTIST);
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

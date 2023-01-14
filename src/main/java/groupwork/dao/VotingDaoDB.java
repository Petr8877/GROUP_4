package groupwork.dao;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VotingDaoDB implements IVotingDao {

    private static final String INSERT_INTO_APP_VOTES = "INSERT INTO app.votes (date_created, message) VALUES (?,?) RETURNING id_voting;";
    public static final String INSERT_INTO_APP_GENRE_VOTES = "insert into app.genre_votes (id_voting, id_genre) values (?,?)";
    public static final String INSERT_INTO_APP_ARTIST_VOTES = "insert into app.artist_votes (id_voting, id_artist) values (?,?)";


    @Override
    public List<SavedVoiceDTO> getAll() {
        return null;
    }

    @Override
    public void save(SavedVoiceDTO voice) {

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement psInsertVote = connection.prepareStatement(INSERT_INTO_APP_VOTES);
             PreparedStatement psInsertArtists = connection.prepareStatement(INSERT_INTO_APP_ARTIST_VOTES);
             PreparedStatement psInsertGenres = connection.prepareStatement(INSERT_INTO_APP_GENRE_VOTES);
             ) {
            connection.setAutoCommit(false);

            psInsertVote.setObject(1,voice.getCreationTime());
            psInsertVote.setString(2,voice.getVoice().getMessage());
            ResultSet resultSet = psInsertVote.executeQuery();
            int id_voting = 0;
            while (resultSet.next()) {
             id_voting =resultSet.getInt("id_voting");
            }

            psInsertArtists.setInt(1,id_voting);
            psInsertArtists.setInt(2, voice.getVoice().getSinger());
            psInsertArtists.execute();

            int[] genre = voice.getVoice().getGenre();
            for (int val:genre) {
                psInsertGenres.setInt(1, id_voting);
                psInsertGenres.setInt(2,val);
                psInsertGenres.execute();
            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

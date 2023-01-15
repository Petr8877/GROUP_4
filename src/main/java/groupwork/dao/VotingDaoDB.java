package groupwork.dao;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VotingDaoDB implements IVotingDao {

    private static final String INSERT_INTO_APP_VOTES = "INSERT INTO app.votes (date_created, message) VALUES (?,?) RETURNING id_voting;";
    private static final String INSERT_INTO_APP_GENRE_VOTES = "insert into app.genre_votes (id_voting, id_genre) values (?,?)";
    private static final String INSERT_INTO_APP_ARTIST_VOTES = "insert into app.artist_votes (id_voting, id_artist) values (?,?)";
    private static final String SELECT_ALL =
            "SELECT  votes.id_voting, date_created, message, STRING_AGG(app.genre_votes.id_genre::character varying::text, ',') , STRING_AGG(DISTINCT app.artist_votes.id_artist::character varying::text, ',')  FROM app.votes "
                    + " JOIN app.genre_votes ON app.genre_votes.id_voting = app.votes.id_voting "
                    + " JOIN app.artist_votes ON app.artist_votes.id_voting = app.votes.id_voting "
                    + " GROUP BY votes.id_voting";

    private static final String FIND_ALL_ID_DATE_MESSAGE_ARTIST = "SELECT  app.votes.id_voting, date_created, message, id_artist FROM app.votes "
            + "JOIN app.artist_votes ON app.artist_votes.id_voting = app.votes.id_voting";
    private static final String FIND_ALL_GENRE = "SELECT id_genre FROM app.genre_votes "
            + "WHERE app.genre_votes.id_voting = (?)";

           @Override
    public List<SavedVoiceDTO> getAll() {
           List<SavedVoiceDTO> list = new ArrayList<>();

           try (Connection connection = ConnectionBuilder.getConnection();
                PreparedStatement prconnection = connection.prepareStatement(FIND_ALL_ID_DATE_MESSAGE_ARTIST);
                PreparedStatement prconnectionGenre = connection.prepareStatement(FIND_ALL_GENRE)) {
                connection.setAutoCommit(false);
               ResultSet resultSet = prconnection.executeQuery();
               while (resultSet.next()){
                   SavedVoiceDTO savedVoiceDTO = new SavedVoiceDTO();
                   VoiceDTO voiceDTO = new VoiceDTO();
                   savedVoiceDTO.setCreationTime(resultSet.getTimestamp("date_created").toLocalDateTime());
                   voiceDTO.setMessage(resultSet.getString("message"));
                   voiceDTO.setSinger(resultSet.getInt("id_artist"));
                   int id = resultSet.getInt("id_voting");
                   prconnectionGenre.setInt(1,id);
                   prconnectionGenre.execute();
                   ResultSet resultSetGenre = prconnectionGenre.executeQuery();
                   List<Integer> listGenre = new ArrayList<>();
                   while (resultSetGenre.next()){
                         listGenre.add(resultSetGenre.getInt("id_genre"));
                   }

                   int[]genres = listGenre.stream()
                                           .mapToInt(Integer::intValue)
                                           .toArray();

                   voiceDTO.setGenre(genres);
                   savedVoiceDTO.setVoice(voiceDTO);
                   list.add(savedVoiceDTO);
               }
               connection.commit();
               connection.setAutoCommit(true);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
           return list;
    }
    @Override
    public void save(SavedVoiceDTO voice) {

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement psInsertVote = connection.prepareStatement(INSERT_INTO_APP_VOTES);
             PreparedStatement psInsertArtists = connection.prepareStatement(INSERT_INTO_APP_ARTIST_VOTES);
             PreparedStatement psInsertGenres = connection.prepareStatement(INSERT_INTO_APP_GENRE_VOTES);
        ) {
            connection.setAutoCommit(false);

            psInsertVote.setObject(1, voice.getCreationTime());
            psInsertVote.setString(2, voice.getVoice().getMessage());
            ResultSet resultSet = psInsertVote.executeQuery();
            int id_voting = 0;
            while (resultSet.next()) {
                id_voting = resultSet.getInt("id_voting");
            }

            psInsertArtists.setInt(1, id_voting);
            psInsertArtists.setInt(2, voice.getVoice().getSinger());
            psInsertArtists.execute();

            int[] genre = voice.getVoice().getGenre();
            for (int val : genre) {
                psInsertGenres.setInt(1, id_voting);
                psInsertGenres.setInt(2, val);
                psInsertGenres.execute();
            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
         // вариант с одним запросом и вычиткой массива жанров
//    @Override
//    public List<SavedVoiceDTO> getAll() {
//        List<SavedVoiceDTO> list = new ArrayList<>();
//        try (Connection connection = ConnectionBuilder.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                SavedVoiceDTO savedVoiceDTO = new SavedVoiceDTO();
//                VoiceDTO voiceDTO = new VoiceDTO();
//                voiceDTO.setMessage(resultSet.getString("message"));
//                String id_artist = resultSet.getString("id_artist");
//                voiceDTO.setSinger(Integer.parseInt(id_artist));
//
//                Array id_genre = resultSet.getArray("id_genre");
//                String[] id_genres = (String[]) id_genre.getArray();
//
//                int[] genres = new int[id_genres.length];
//                for (int i = 0; i < genres.length; i++) {
//                    genres[i] = Integer.parseInt(id_genres[i]);
//                }
//                voiceDTO.setGenre(genres);
//                savedVoiceDTO.setVoice(voiceDTO);
//                savedVoiceDTO.setCreationTime(resultSet.getTimestamp("date_created").toLocalDateTime());
//                list.add(savedVoiceDTO);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("error of DB" + e.getMessage());
//        }
//        return list;
//    }
}
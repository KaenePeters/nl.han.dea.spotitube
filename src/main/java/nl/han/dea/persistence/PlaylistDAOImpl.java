package nl.han.dea.persistence;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.exceptions.PersistenceException;
import nl.han.dea.persistence.database.ConnectionFactory;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAOImpl implements IPlaylistDAO {

    private TrackDaoImpl trackDaoImpl;

    public PlaylistDAOImpl() {
    }

    @Inject
    public PlaylistDAOImpl(TrackDaoImpl trackDaoImpl) {
        this.trackDaoImpl = trackDaoImpl;
    }

    @Override
    public PlaylistsDTO getPlaylistsForUsername(String username) {
        List<PlaylistDTO> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist1";
        int playlistLength = 0;
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlaylistDTO playlistDTO = new PlaylistDTO();
                playlistDTO.setId(resultSet.getInt("id"));
                playlistDTO.setName(resultSet.getString("name"));
                playlistDTO.setOwner(resultSet.getString("owner").equals(username));
                playlistDTO.setTracks(trackDaoImpl.getAllTracksForPlaylist(resultSet.getInt("id")).getTracks());
                playlists.add(playlistDTO);
                playlistLength += playlistDTO.getDuration();

            }
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
        return new PlaylistsDTO(playlists, playlistLength);
    }

    @Override
    public void deletePlaylist(int playlistId) {
        //Eerst alle afhankelijke tabellen verwijderen
        deletePlaylistFromPlaylistTracks(playlistId);
        String query = "DELETE FROM playlist1 WHERE id = ?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO, String username) {
        String query = "INSERT INTO playlist1 (name, owner) VALUES (?,?)";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
    }

    @Override
    public void editPlaylistById(String name, int playlistId) {
        String query = "UPDATE playlist1 SET name =?  WHERE id=?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
    }

    private void deletePlaylistFromPlaylistTracks(int playlistId) {
        String query = "DELETE FROM playlist_track WHERE playlistId=?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
    }

    //Indien de Id van de playlist geen increment heeft kan dit gebruikt worden om een nieuw id te maken.

//    private int getNewPlaylistId() {
//        int newPlaylistId = 0;
//        String query = "SELECT count(*) AS newPlaylistId FROM playlist1";
//        try (
//                Connection connection = new ConnectionFactory().getConnecion();
//                PreparedStatement preparedStatement = connection.prepareStatement(query)
//        ) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                newPlaylistId = resultSet.getInt("newPlaylistId") + 1;
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return newPlaylistId;
//    }
}

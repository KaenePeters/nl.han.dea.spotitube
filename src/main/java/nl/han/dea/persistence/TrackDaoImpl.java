package nl.han.dea.persistence;

import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.exceptions.PersistenceException;
import nl.han.dea.persistence.database.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackDaoImpl implements ITrackDAO {

    @Override
    public TracksDTO getAllTracksNotInPlaylist(int playlistId) {
        String query = "SELECT * FROM track WHERE id NOT IN (SELECT trackId FROM playlist_track WHERE playlistId = ?)";
        return getTracks(playlistId, query);
    }

    private TracksDTO getTracks(int playlistId, String query) {
        List<TrackDTO> tracks = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tracks.add(new TrackDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("performer"),
                        resultSet.getInt("duration"),
                        resultSet.getString("album"),
                        resultSet.getInt("playcount"),
                        resultSet.getString("publicationDate"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("offlineAvailable")
                ));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
        return new TracksDTO(tracks);
    }

    @Override
    public TracksDTO getAllTracksForPlaylist(int playlistId) {
        String query = "SELECT * FROM track WHERE id IN (SELECT trackId FROM playlist_track WHERE playlistId = ?)";
        return getTracks(playlistId, query);
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        String query = "DELETE FROM playlist_track WHERE playlistId = ? AND trackId = ?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO trackDTO) {
        String query = "INSERT INTO playlist_track (playlistId ,trackId) VALUES (?,?)";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackDTO.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
    }
}

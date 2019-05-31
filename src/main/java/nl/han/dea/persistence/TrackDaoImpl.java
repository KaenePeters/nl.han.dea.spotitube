package nl.han.dea.persistence;

import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Default
public class TrackDaoImpl implements ITrackDAO {

    @Override
    public TracksDTO getAllTracks(int playlistId) {
        String query = "SELECT * FROM track T INNER JOIN playlist_track PT ON T.id = PT.trackId WHERE playlistId=?";
        TracksDTO tracksDTO = new TracksDTO();
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrackDTO trackDTO = new TrackDTO();
                trackDTO.setId(resultSet.getInt("id"));
                trackDTO.setTitle(resultSet.getString("title"));
                trackDTO.setPreformer(resultSet.getString("performer"));
                trackDTO.setDuration(resultSet.getInt("duration"));
                trackDTO.setAlbum(resultSet.getString("album"));
                trackDTO.setPlaycount(resultSet.getInt("playcount"));
                trackDTO.setPublicationDate(resultSet.getString("publicationDate"));
                trackDTO.setDescription(resultSet.getString("description"));
                trackDTO.setOfflineAvailable(resultSet.getBoolean("offline"));
                tracksDTO.setTracksDTO(trackDTO);
            }

            return tracksDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

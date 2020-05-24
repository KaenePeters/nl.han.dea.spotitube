package nl.han.dea.persistence;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;

import javax.enterprise.inject.Default;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAOImpl implements IPlaylistDAO {


    @Override
    public PlaylistsDTO getPlaylists(String username) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        List<PlaylistDTO> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist1";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                playlists.add(new PlaylistDTO(
                        resultSet.getInt("Id"),
                        resultSet.getString("name"),
                        resultSet.getString("owner").equals(username)

                ));
            }
            System.out.println(playlists.get(0).getClass().getDeclaredFields());
            playlistsDTO.setPlaylists(playlists);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlistsDTO;
    }

//                TracksDTO tracks = new TracksDTO();
//                tracks.setTracksDTO(new TrackDTO(1,"temp","performer", 100, "album",10,"10-10-1900","description",false));
//                playlistDTO.setTrackDTOS((ArrayList<TrackDTO>) tracks.getTracksDTO());


    @Override
    public void deletePlaylist(int playlistId) {
        //eerst alle afhankelijke tabellen verwijderen
        deleteFromPlaylistTracks(playlistId);
        String query = "DELETE FROM playlist1 WHERE id = ?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editPlaylist(String name, int playlistId) {
        String query = "UPDATE playlist1 SET name=? WHERE id=?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFromPlaylistTracks(int playlistId) {
        String query = "DELETE FROM playlist_track WHERE playlistId=?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getNewPlaylistId() {
        int newPlaylistId = 0;
        String query = "SELECT count(*) as newPlaylistId FROM playlist1";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                newPlaylistId = resultSet.getInt("newPlaylistId") + 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newPlaylistId;
    }
}

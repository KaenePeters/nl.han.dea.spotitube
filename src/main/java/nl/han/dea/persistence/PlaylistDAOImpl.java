package nl.han.dea.persistence;

import nl.han.dea.DTO.Playlist;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.exceptions.ItemNotFoundException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {

    private TrackDAO trackDAO;

    public PlaylistDAOImpl() {

    }

    @Inject
    public PlaylistDAOImpl(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public List<Playlist> getAllPlayLists(String user) {
        List<Playlist> playlists = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Playlist playlist = getPlaylistByID(resultSet.getInt("id"));
                playlist.setOwner(user.equals(playlist.getUser()));
                playlists.add(playlist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }

    @Override
    public PlaylistsDTO getPlaylistsDTO(String currentUser) {
        return new PlaylistsDTO(getAllPlayLists(currentUser));
    }

    @Override
    public Playlist getPlaylistByID(int id) {
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement playlistStatement = connection.prepareStatement("SELECT * FROM playlist WHERE id = ?");
                PreparedStatement playlist_TrackStatement = connection.prepareStatement("SELECT * FROM playlist_track WHERE playlistId = ?");
        ) {
            playlistStatement.setInt(1, id);
            ResultSet playlistResultSet = playlistStatement.executeQuery();
            if (playlistResultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(id);
                playlist.setName(playlistResultSet.getString("name"));
                playlist.setUser(playlistResultSet.getString("owner"));
                playlist.setTracks(null);
                return playlist;
            }
            throw new ItemNotFoundException("Playlist " + id + " not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
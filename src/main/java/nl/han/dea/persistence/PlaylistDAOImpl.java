package nl.han.dea.persistence;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAOImpl implements IPlaylistDAO {


    public PlaylistDAOImpl() {

    }


    @Override
    public PlaylistsDTO getAllPlaylists(String username) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        String query = "SELECT * FROM playlist WHERE owner=?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlaylistDTO playlistDTO = new PlaylistDTO();
                playlistDTO.setId(resultSet.getInt("id"));
                playlistDTO.setName(resultSet.getString("name"));
                playlistDTO.setOwner(resultSet.getString("owner"), username);
                playlistsDTO.setPlaylists(playlistDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistsDTO;
    }





    @Override
    public void deletePlaylist(int playlistId) {
        //eerst alle afhankelijke tabellen verwijderen
        deleteFromPlaylistTracks(playlistId);
        String query = "DELETE FROM playlist WHERE id = ?";
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
        String query = "INSERT INTO playlist (name, owner) VALUES (?,?)";
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
        String query = "UPDATE playlist SET name=? WHERE id=?";
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
}
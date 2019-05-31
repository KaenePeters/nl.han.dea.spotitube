package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.persistence.ConnectionFactory;
import nl.han.dea.persistence.IPlaylistDAO;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.TokenDaoImpl;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Default
public class PlaylistServiceImpl implements IPlaylistService {

    @Inject
    private ITokenDAO iTokenDAO;
    @Inject
    private IPlaylistDAO playlistDAO;

    public PlaylistServiceImpl() {
    }

//    @Inject
    public PlaylistServiceImpl(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public PlaylistsDTO getPlaylistsDTO(String token) {
        String username = iTokenDAO.tokenIsValid(token);
        if (username != null) {
            return playlistDAO.getAllPlaylists(username);
        }
       else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }

    @Override
    public void deletePlaylist(String token, int playlistId){
        String username = iTokenDAO.tokenIsValid(token);
        if (username != null) {
                playlistDAO.deletePlaylist(playlistId);
            } else {
                throw new InvalidTokenException("Wrong Token.");
            }

    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO, String token) {
        String username = iTokenDAO.tokenIsValid(token);
        if (username != null) {
            playlistDAO.addPlaylist(playlistDTO, username);
        }
        else {
            throw new InvalidTokenException("Wrong Token.");
        }

    }
@Override
public void editPlaylist(PlaylistDTO playlistDTO, String token, int id){
    String username = iTokenDAO.tokenIsValid(token);
    if (username != null) {
        playlistDAO.editPlaylist(playlistDTO.getName(), id);
    }
    else {
        throw new InvalidTokenException("Wrong Token.");
    }


}

}

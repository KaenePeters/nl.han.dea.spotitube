package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.persistence.*;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class PlaylistServiceImpl implements IPlaylistService {


    private ITokenDAO iTokenDAO = new TokenDaoImpl();

    private IPlaylistDAO playlistDAO = new PlaylistDAOImpl();

    public PlaylistServiceImpl() {
    }

    @Inject
    public PlaylistServiceImpl(IPlaylistDAO playlistDAO, ITokenDAO iTokenDAO) {
        this.playlistDAO = playlistDAO;
        this.iTokenDAO = iTokenDAO;
    }

    @Override
    public PlaylistsDTO getPlaylistsDTO(String token) {
        String username = iTokenDAO.tokenIsValid(token);
        if (username != null) {
            return playlistDAO.getPlaylists(username);
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

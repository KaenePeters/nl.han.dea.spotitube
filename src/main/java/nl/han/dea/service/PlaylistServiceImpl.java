package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.persistence.PlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistDAO playlistDAO;

    public PlaylistServiceImpl() {
    }

    @Inject
    public PlaylistServiceImpl(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public PlaylistsDTO getPlaylistsDTO(String username) {
        return playlistDAO.getPlaylistsDTO(username);
    }


}

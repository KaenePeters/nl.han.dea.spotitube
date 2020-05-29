package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.exceptions.MissingTokenException;
import nl.han.dea.persistence.IPlaylistDAO;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.ITrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class PlaylistServiceImpl implements IPlaylistService {


    private ITokenDAO iTokenDAO;
    private IPlaylistDAO playlistDAO;
    private ITrackDAO trackDAO;

    public PlaylistServiceImpl() {
    }

    @Inject
    public PlaylistServiceImpl(IPlaylistDAO playlistDAO, ITokenDAO iTokenDAO, ITrackDAO trackDAO) {
        this.playlistDAO = playlistDAO;
        this.iTokenDAO = iTokenDAO;
        this.trackDAO = trackDAO;
    }

    @Override
    public PlaylistsDTO getPlaylistsForUsername(String token) {
        String username = iTokenDAO.getUsernameFromToken(token);
        if (username != null) {
            return playlistDAO.getPlaylistsForUsername(username);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("Missing Token.");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }

    @Override
    public void deletePlaylistById(int playlistId, String token) {
        if (iTokenDAO.getUsernameFromToken(token) != null) {
            playlistDAO.deletePlaylist(playlistId);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("Missing Token.");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO, String token) {
        String username = iTokenDAO.getUsernameFromToken(token);
        if (username != null) {
            playlistDAO.addPlaylist(playlistDTO, username);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("Missing Token.");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }

    @Override
    public void editPlaylistById(PlaylistDTO playlistDTO, int id, String token) {
        if (iTokenDAO.getUsernameFromToken(token) != null) {
            playlistDAO.editPlaylistById(playlistDTO.getName(), id);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("Missing Token.");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }

    @Override
    public TracksDTO getAllTracksForPlaylist(int playlistId, String token) {
        if (iTokenDAO.getUsernameFromToken(token) != null) {
            return trackDAO.getAllTracksForPlaylist(playlistId);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("Missing Token.");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId, String token) {
        if (iTokenDAO.getUsernameFromToken(token) != null) {
            trackDAO.removeTrackFromPlaylist(playlistId, trackId);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("Missing Token.");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO trackDTO, String token) {
        if (iTokenDAO.getUsernameFromToken(token) != null) {
            trackDAO.addTrackToPlaylist(playlistId, trackDTO);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("Missing Token.");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }


}

package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;

public interface IPlaylistService {

    PlaylistsDTO getPlaylistsForUsername(String token);

    void addPlaylist(PlaylistDTO playlistDTO, String token);

    void deletePlaylistById(int playlistId, String token);

    void editPlaylistById(PlaylistDTO playlistDTO, int playlistID, String token);

    TracksDTO getAllTracksForPlaylist(int playlistId, String token);

    void removeTrackFromPlaylist(int playlistId, int trackId, String token);

    void addTrackToPlaylist(int playlistId, TrackDTO trackDTO, String token);
}

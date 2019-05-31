package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;

public interface IPlaylistService {

    PlaylistsDTO getPlaylistsDTO(String token);

    void addPlaylist(PlaylistDTO playlistDTO, String token);

    void deletePlaylist(String token, int playlistId);

    void editPlaylist(PlaylistDTO playlistDTO, String token, int id);
}

package nl.han.dea.persistence;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;



public interface IPlaylistDAO {

    PlaylistsDTO getPlaylistsForUsername(String username);

    void editPlaylistById(String playlistName, int playlistId);

    void deletePlaylist(int playlistId);

    void addPlaylist(PlaylistDTO playlistDTO, String username);
}

package nl.han.dea.persistence;

import nl.han.dea.DTO.Playlist;
import nl.han.dea.DTO.PlaylistsDTO;

import java.util.List;

public interface PlaylistDAO {

    List<Playlist> getAllPlayLists(String currentUser);

    Playlist getPlaylistByID(int id);

    PlaylistsDTO getPlaylistsDTO(String currentUser);
}

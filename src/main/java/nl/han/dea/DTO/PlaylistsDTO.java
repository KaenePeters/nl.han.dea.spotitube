package nl.han.dea.DTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsDTO {

    private List<PlaylistDTO> playlistsDTO;
    private int length;

    public PlaylistsDTO() {
        playlistsDTO = new ArrayList<>();
    }

    public List<PlaylistDTO> getPlaylistsDTO() {
        return playlistsDTO;
    }

    public void setPlaylists(PlaylistDTO playlistDTO) {
        playlistsDTO.add(playlistDTO);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}

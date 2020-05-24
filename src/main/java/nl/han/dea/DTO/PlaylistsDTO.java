package nl.han.dea.DTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsDTO {

    private List<PlaylistDTO> playlists;
    private int length;


    public PlaylistsDTO() {
        playlists = new ArrayList<>();
    }

    public List<PlaylistDTO> getPlaylistsDTO() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


//    @Override
//    public String toString() {
//        for (PlaylistDTO playlist : playlists)
//        {
//            playlist.getName();
//
//        }
//        return ;
//    }
}

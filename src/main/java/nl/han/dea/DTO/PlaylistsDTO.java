package nl.han.dea.DTO;

import java.util.List;

public class PlaylistsDTO {

    private List<Playlist> playlists;


    public PlaylistsDTO(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }


//    public int getLength() {
//        int length = 0;
//        for (Playlist playlist : playlists) {
//            length += playlist.getLength();
//
//        }
//        return length;
//    }

}

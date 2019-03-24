package nl.han.dea.DTO;


import java.util.ArrayList;

public class PlaylistsDTO {
    private ArrayList<Playlist> playlists;
    private int length;

    public PlaylistsDTO() {
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

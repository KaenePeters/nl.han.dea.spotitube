package nl.han.dea.DTO;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name;
    private boolean owner;
    private int length;

    private List<TrackDTO> tracks = new ArrayList<TrackDTO>();

    public Playlist(String name, boolean owner, int length, List<TrackDTO> tracks) {
        this.name = name;
        this.owner = owner;
        this.length = length;
        this.tracks = tracks;
    }

    public void setTrack(TrackDTO track) {
        tracks.add(track);
    }
    public Playlist getPlaylist(){
        return this;
    }
    public int getLength(){
        return this.length;
    }

}


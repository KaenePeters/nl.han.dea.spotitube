package nl.han.dea.DTO;

import java.util.ArrayList;

public class Playlist {

    private int id;
    private String name;
    private boolean owner;
    private ArrayList<TrackDTO> tracks;

    //    public Playlist(String name, boolean owner, ArrayList<TrackDTO> tracks) {
//        this.name = name;
//        this.owner = owner;;
//        this.tracks = tracks;
//    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
    }

    public Playlist getPlaylist() {
        return this;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTrack(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }


}


package nl.han.dea.DTO;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private String user;
    private boolean owner;
    private ArrayList<Track> tracks;

    public Playlist() {
        tracks = new ArrayList<>();
    }

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

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }


    public void addTrack(Track track) {
        tracks.add(track);
    }


//    public int getLength() {
//        int length = 0;
//        for (Track track : tracks) {
//            length += track.getDuration();
//        }
//        return length;
//    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}


package nl.han.dea.DTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean Owner;
    private List<TrackDTO> tracks;


    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, boolean owner) {
        this.id = id;
        this.name = name;
        this.Owner = owner;
        this.tracks = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOwner() {
        return Owner;
    }

    public void setOwner(boolean owner) {
        this.Owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        int duration = 0;
        if (tracks.size() > 0) {
            for (TrackDTO track : tracks) {
                duration += track.getDuration();
            }
            return duration;
        }
        return 0;
    }

    public List<TrackDTO> getTrack() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

}


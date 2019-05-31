package nl.han.dea.DTO;

import java.util.ArrayList;

public class PlaylistDTO {

    private int id;
    private String name;
    private boolean isOwner;
    private ArrayList<TrackDTO> TrackDTOS;

    public PlaylistDTO() {

        TrackDTOS = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
    }

    public PlaylistDTO getPlaylist() {
        return this;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(String owner, String user) {
        isOwner = owner.equals(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TrackDTO> getTrackDTOS() {
        return TrackDTOS;
    }

    public void setTrackDTOS(ArrayList<TrackDTO> trackDTOS) {
        this.TrackDTOS = trackDTOS;
    }


    public void addTrack(TrackDTO TrackDTO) {
        TrackDTOS.add(TrackDTO);
    }





}


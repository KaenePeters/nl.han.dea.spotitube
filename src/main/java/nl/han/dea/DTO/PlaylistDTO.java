package nl.han.dea.DTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean Owner;
    private List<TrackDTO> tracks;

    public PlaylistDTO(int id, String name, boolean owner) {
        this.id = id;
        this.name = name;
        this.Owner = owner;
        //this.tracks = new ArrayList<>();
    }

    public PlaylistDTO() {
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

//        public ArrayList<TrackDTO> getTrackDTOS () {
//            return TrackDTOS;
//        }
//
//        public void setTrackDTOS (ArrayList < TrackDTO > trackDTOS) {
//            this.TrackDTOS = trackDTOS;
//        }
//
//
//        public void addTrack (TrackDTO TrackDTO){
//            TrackDTOS.add(TrackDTO);
//        }


}


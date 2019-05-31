package nl.han.dea.DTO;

import java.util.ArrayList;
import java.util.List;

public class TracksDTO {

    private List<TrackDTO> tracksDTO;

    public TracksDTO() {
        tracksDTO = new ArrayList<>();
    }

    public List<TrackDTO> getTracksDTO() {
        return tracksDTO;
    }

    public void setTracksDTO(TrackDTO track) {
        tracksDTO.add(track);
    }
}

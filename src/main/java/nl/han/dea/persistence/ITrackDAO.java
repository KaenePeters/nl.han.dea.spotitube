package nl.han.dea.persistence;

import nl.han.dea.DTO.TracksDTO;

public interface ITrackDAO {

    TracksDTO getAllTracks(int playlistId);
}

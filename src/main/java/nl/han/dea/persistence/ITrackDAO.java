package nl.han.dea.persistence;

import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;

public interface ITrackDAO {

    TracksDTO getAllTracksNotInPlaylist(int playlistId);

    TracksDTO getAllTracksForPlaylist(int playlistId);

    void removeTrackFromPlaylist(int playlistId, int trackId);

    void addTrackToPlaylist(int playlistId, TrackDTO trackDTO);
}

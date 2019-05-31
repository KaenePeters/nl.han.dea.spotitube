package nl.han.dea.service;

import nl.han.dea.DTO.TracksDTO;

public interface ITrackService {

    TracksDTO getAllTracks(String token, int playlistId);
}

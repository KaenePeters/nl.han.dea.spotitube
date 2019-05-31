package nl.han.dea.service;

import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.ITrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class TrackServiceImpl implements ITrackService{

    @Inject
    private ITokenDAO iTokenDAO;

    @Inject
    private ITrackDAO iTrackDAO;

    @Override
    public TracksDTO getAllTracks(String token, int playListId) {

        String username = iTokenDAO.tokenIsValid(token);
        if (username != null) {
            return iTrackDAO.getAllTracks(playListId);
        }
        else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }
}

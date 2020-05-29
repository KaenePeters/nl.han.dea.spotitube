package nl.han.dea.service;

import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.exceptions.MissingTokenException;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.ITrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class TrackServiceImpl implements ITrackService {


    private ITokenDAO iTokenDAO;
    private ITrackDAO iTrackDAO;

    public TrackServiceImpl() {

    }

    @Inject
    public TrackServiceImpl(ITokenDAO iTokenDAO, ITrackDAO iTrackDAO) {
        this.iTokenDAO = iTokenDAO;
        this.iTrackDAO = iTrackDAO;
    }


    @Override
    public TracksDTO getAllTracksNotInPlaylist(int playListId, String token) {
        if (iTokenDAO.getUsernameFromToken(token) != null) {
            return iTrackDAO.getAllTracksNotInPlaylist(playListId);
        } else if (token != null && !token.isEmpty()) {
            throw new MissingTokenException("MissingToken");
        } else {
            throw new InvalidTokenException("Wrong Token.");
        }
    }


}

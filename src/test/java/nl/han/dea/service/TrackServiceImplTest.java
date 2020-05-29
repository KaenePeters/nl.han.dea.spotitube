package nl.han.dea.service;

import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.exceptions.MissingTokenException;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.ITrackDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {

    private final int PLAYLIST_ID = 1;

    @Mock
    private ITrackDAO trackDAO;

    @Mock
    private ITokenDAO tokenDAO;

    @InjectMocks
    private TrackServiceImpl sut;

    @Test
    void getAllTracksNotInPlaylistSucess() {
        String USERNAME = "kaene";
        String TOKEN = "1234";
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(USERNAME);
        sut.getAllTracksNotInPlaylist(PLAYLIST_ID, TOKEN);

        verify(trackDAO).getAllTracksNotInPlaylist(PLAYLIST_ID);
    }

    @Test
    void throwMissingTokenExceptionWhenWhenGettingTracksForPlaylist() {
        MissingTokenException missingTokenException = assertThrows(MissingTokenException.class, () -> sut.getAllTracksNotInPlaylist(PLAYLIST_ID, null));

        assertEquals("MissingToken", missingTokenException.getMessage());
    }

    @Test
    void throwInvalidTokenExceptionWhenWhenGettingTracksForPlaylist() {
        InvalidTokenException invalidTokenException = assertThrows(InvalidTokenException.class, () -> sut.getAllTracksNotInPlaylist(PLAYLIST_ID, "WRONGTOKEN"));

        assertEquals("Wrong Token.", invalidTokenException.getMessage());
    }

    @Test
    void testConstructor() {
        TrackServiceImpl trackService = new TrackServiceImpl();
    }


}

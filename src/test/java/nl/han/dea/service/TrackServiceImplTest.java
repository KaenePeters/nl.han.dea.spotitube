package nl.han.dea.service;

import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.ITrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {

    private final int PLAYLIST_ID = 1;
    @Mock
    ITrackDAO trackDAOMock;
    @Mock
    ITokenDAO tokenDAO;
    @InjectMocks
    TrackServiceImpl sut;

    @BeforeEach
    void setUp() {

        TrackDTO testTrack = new TrackDTO(1, "", "", 60, "", PLAYLIST_ID, "", "", true);
    }

    @Test
    void getAllTracksNotInPlaylistSucess() {
        String TOKEN = "1234";
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(TOKEN);
        sut.getAllTracksNotInPlaylist(PLAYLIST_ID, TOKEN);

        verify(trackDAOMock).getAllTracksNotInPlaylist(PLAYLIST_ID);
    }

    @Test
    void testConstructor() {
        TrackServiceImpl trackService = new TrackServiceImpl();
    }


}

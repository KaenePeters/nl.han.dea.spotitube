package nl.han.dea.resources;

import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.service.ITrackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrackResourceTest {

    private List<TrackDTO> tracks = new ArrayList<>();
    private TracksDTO tracklist = new TracksDTO(tracks);

    @Mock
    private ITrackService trackService;


    @InjectMocks
    private TrackResource sut;

    @Test
    public void checksIfReturnAreCorrectTheTrackssByCorrectToken() {
        String TOKEN = "1234";
        int PLAYLIST_ID = 1;
        when(trackService.getAllTracksNotInPlaylist(PLAYLIST_ID, TOKEN)).thenReturn(tracklist);

        Response actualResult = sut.getAllTracksNotInPlaylist(PLAYLIST_ID, TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        verify(trackService).getAllTracksNotInPlaylist(PLAYLIST_ID, TOKEN);
        assertEquals(tracklist, actualResult.getEntity());
    }

    @Test
    public void testConstructor() {
        TrackResource trackResource = new TrackResource();
    }
}

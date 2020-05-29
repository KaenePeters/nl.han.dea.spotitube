package nl.han.dea.resources;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.persistence.IPlaylistDAO;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.ITrackDAO;
import nl.han.dea.service.IPlaylistService;
import nl.han.dea.service.ITrackService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PlaylistResourceTest {

    private final String TOKEN = "1234";
    private PlaylistDTO playlist;
    private TrackDTO track;

    @Mock
    ITokenDAO tokenDAO;
    @Mock
    IPlaylistDAO playlistDAO;

    @Mock
    private ITrackDAO trackDAO;

    @Mock
    private IPlaylistService playlistService;

    @Mock
    private ITrackService trackServiceMock;

    @InjectMocks
    private PlaylistResource sut;


    @BeforeEach
    void setUp() {


        track = new TrackDTO();
        track.setId(1);
        track.setDuration(60);

        TracksDTO tracklist = new TracksDTO(new ArrayList<>(Collections.singletonList(track)));
        playlist = new PlaylistDTO();
        playlist.setId(1);
        playlist.setOwner(true);
        playlist.setTracks(tracklist.getTracks());
    }

    @Test
    public void getPlaylistsForUsername() {
        IPlaylistService playlistService = mock(IPlaylistService.class);
        ITokenDAO tokenDAO = mock(ITokenDAO.class);
        IPlaylistDAO playlistDAO = mock(IPlaylistDAO.class);

        List<PlaylistDTO> playlists = new ArrayList<>(Collections.singletonList(playlist));
        PlaylistsDTO testPlaylists = new PlaylistsDTO(playlists, playlist.getDuration());

        Mockito.when(playlistService.getPlaylistsForUsername(TOKEN))
                .thenReturn(testPlaylists);

        Response actualResult = sut.getPlaylistsForUsername(TOKEN);

        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        Mockito.verify(playlistService).getPlaylistsForUsername(TOKEN);
        assertEquals(testPlaylists, actualResult.getEntity());
    }

    @Test
    public void deletePlaylistById() {
    }

    @Test
    public void addPlaylist() {
    }

    @Test
    public void editPlaylistById() {
    }

    @Test
    public void getAllTracksForPlaylist() {
    }

    @Test
    public void removeTrackFromPlaylist() {
    }

    @Test
    public void addTrackToPlaylist() {
    }
}

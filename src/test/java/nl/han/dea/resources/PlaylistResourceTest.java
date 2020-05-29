package nl.han.dea.resources;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.service.IPlaylistService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
public class PlaylistResourceTest {

    private final int PLAYLIST_ID = 1;
    private final int TRACK_ID = 2;
    private final String TOKEN = "1234";
    private PlaylistsDTO playlistsDTO;
    private TrackDTO track;
    private TracksDTO tracklist;

    @Mock
    private IPlaylistService playlistService;

    @InjectMocks
    private PlaylistResource sut;


    @BeforeEach
    void setUp() {
        track = new TrackDTO(TRACK_ID, "", "", 60, "", PLAYLIST_ID, "", "", true);
        List<TrackDTO> tracks = new ArrayList<>();
        tracklist = new TracksDTO(tracks);

        PlaylistDTO playlist = new PlaylistDTO();
        playlist.setId(PLAYLIST_ID);
        playlist.setOwner(true);
        playlist.setTracks(tracklist.getTracks());
        List<PlaylistDTO> playlists = new ArrayList<>();
        playlistsDTO = new PlaylistsDTO(playlists, 0);
    }

    @Test
    public void checksIfReturnAreCorrectThePlaylistsByCorrectToken() {
        when(playlistService.getPlaylistsForUsername(TOKEN)).thenReturn(playlistsDTO);

        Response actualResult = sut.getPlaylistsForUsername(TOKEN);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        verify(playlistService).getPlaylistsForUsername(TOKEN);
        assertEquals(playlistsDTO, actualResult.getEntity());
    }

    @Test
    public void deletePlaylistById() {
        sut.deletePlaylistById(PLAYLIST_ID, TOKEN);
        verify(playlistService).deletePlaylistById(PLAYLIST_ID, TOKEN);
    }

    @Test
    public void addPlaylist() {
        sut.deletePlaylistById(PLAYLIST_ID, TOKEN);
        verify(playlistService).deletePlaylistById(PLAYLIST_ID, TOKEN);
    }

    @Test
    public void editPlaylistById() {
        String PLAYLIST_NAME = "testPlaylist";
        PlaylistDTO playlist = new PlaylistDTO(PLAYLIST_ID, PLAYLIST_NAME, true);
        sut.editPlaylistById(playlist, PLAYLIST_ID, TOKEN);
        verify(playlistService).editPlaylistById(playlist, PLAYLIST_ID, TOKEN);
    }

    @Test
    public void getAllTracksForPlaylist() {
        when(playlistService.getAllTracksForPlaylist(PLAYLIST_ID, TOKEN)).thenReturn(tracklist);
        sut.getAllTracksForPlaylist(PLAYLIST_ID, TOKEN);
        verify(playlistService).getAllTracksForPlaylist(PLAYLIST_ID, TOKEN);
    }

    @Test
    public void removeTrackFromPlaylist() {
        sut.removeTrackFromPlaylist(PLAYLIST_ID, TRACK_ID, TOKEN);
        verify(playlistService).removeTrackFromPlaylist(PLAYLIST_ID, TRACK_ID, TOKEN);
    }

    @Test
    public void addTrackToPlaylist() {
        sut.addTrackToPlaylist(PLAYLIST_ID, TOKEN, track);
        verify(playlistService).addTrackToPlaylist(PLAYLIST_ID, track, TOKEN);
    }

    @Test
    public void testConstructor() {
        PlaylistResource playlistResource = new PlaylistResource();
    }
}

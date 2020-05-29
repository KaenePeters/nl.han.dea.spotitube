package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.exceptions.MissingTokenException;
import nl.han.dea.persistence.IPlaylistDAO;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.ITrackDAO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceImplTest {

    private final int PLAYLIST_ID = 1;
    private final int TRACK_ID = 2;
    private final String TOKEN = "1234";
    private final String USERNAME = "kaene";
    private final String PLAYLIST_NAME = "testPlaylist";

    private PlaylistDTO playlist;
    private PlaylistsDTO playlistsDTO;
    private TrackDTO track;
    private TracksDTO tracklist;

    @Mock
    private ITrackDAO trackDAO;

    @Mock
    private ITokenDAO tokenDAO;

    @Mock
    private IPlaylistDAO playlistsDAO;

    @InjectMocks
    private PlaylistServiceImpl sut;

    @BeforeEach
    void setUp() {
        track = new TrackDTO(TRACK_ID, "", "", 60, "", PLAYLIST_ID, "", "", true);
        List<TrackDTO> tracks = new ArrayList<>();
        tracklist = new TracksDTO(tracks);

        playlist = new PlaylistDTO();
        playlist.setId(PLAYLIST_ID);
        playlist.setOwner(true);
        playlist.setName(PLAYLIST_NAME);
        playlist.setTracks(tracklist.getTracks());
        List<PlaylistDTO> playlists = new ArrayList<>();
        playlistsDTO = new PlaylistsDTO(playlists, 0);
    }

    @Test
    public void getPlaylistsForUsernameSucess() {
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(USERNAME);

        when(playlistsDAO.getPlaylistsForUsername(USERNAME)).thenReturn(playlistsDTO);
        sut.getPlaylistsForUsername(TOKEN);

        verify(playlistsDAO).getPlaylistsForUsername(USERNAME);
    }

//Deze en de volgende test kunnen herhaalt worden voor elke methode om 100% code coverage te krijgen maar dat voegt eigenlijk niks toe.
    @Test
    public void checksIfReturnsCorrectPlaylistsByMissingToken() {
        MissingTokenException missingTokenException = assertThrows(MissingTokenException.class, () -> sut.getPlaylistsForUsername(""));

        assertEquals("Missing Token.", missingTokenException.getMessage());
    }

    @Test
    public void checksIfReturnsCorrectPlaylistsByIncorrectToken() {
        InvalidTokenException invalidTokenException = assertThrows(InvalidTokenException.class, () -> sut.getPlaylistsForUsername("Wrong token"));

        assertEquals("Wrong Token.", invalidTokenException.getMessage());
    }

    @Test
    public void deletePlaylistById() {
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(TOKEN);
        sut.deletePlaylistById(PLAYLIST_ID, TOKEN);
        verify(playlistsDAO).deletePlaylist(PLAYLIST_ID);
    }

    @Test
    public void addPlaylist() {
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(TOKEN);
        sut.addPlaylist(playlist, TOKEN);
        verify(playlistsDAO).addPlaylist(playlist, TOKEN);
    }

    @Test
    public void editPlaylistById() {
        PlaylistDTO playlist = new PlaylistDTO(PLAYLIST_ID, PLAYLIST_NAME,true);
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(TOKEN);
        sut.editPlaylistById(playlist, PLAYLIST_ID,TOKEN);
        verify(playlistsDAO).editPlaylistById(PLAYLIST_NAME, PLAYLIST_ID);
    }

    @Test
    public void getAllTracksForPlaylist() {
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(USERNAME);

        when(trackDAO.getAllTracksForPlaylist(PLAYLIST_ID)).thenReturn(tracklist);
        sut.getAllTracksForPlaylist(PLAYLIST_ID,TOKEN);

        verify(trackDAO).getAllTracksForPlaylist(PLAYLIST_ID);
    }

    @Test
    public void removeTrackFromPlaylist() {
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(USERNAME);

        sut.removeTrackFromPlaylist(PLAYLIST_ID,TRACK_ID,TOKEN);

        verify(trackDAO).removeTrackFromPlaylist(PLAYLIST_ID,TRACK_ID);

    }

    @Test
    public void addTrackToPlaylist() {
        when(tokenDAO.getUsernameFromToken(TOKEN)).thenReturn(USERNAME);

        sut.addTrackToPlaylist(PLAYLIST_ID,track,TOKEN);

        verify(trackDAO).addTrackToPlaylist(PLAYLIST_ID,track);
    }
}

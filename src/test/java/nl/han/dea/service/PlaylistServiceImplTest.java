package nl.han.dea.service;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.persistence.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceImplTest {

    private final int PLAYLIST_ID = 1;
    private final String TOKEN = "1234";
    private PlaylistDTO playlist;
    private PlaylistsDTO playlists;
    private TrackDTO track;
    private TracksDTO tracklist;

    private ITrackDAO trackDAO;


    private ITokenDAO tokenDAO;


    private IPlaylistDAO playlistsDAO;

    @InjectMocks
    private PlaylistServiceImpl sut;

    @BeforeEach
    void setUp() {
        trackDAO = new TrackDaoImpl();
        tokenDAO = new TokenDaoImpl();
        playlistsDAO = new PlaylistDAOImpl();
        sut = new PlaylistServiceImpl();

        track = new TrackDTO();
        track.setId(1);
        track.setDuration(60);

        tracklist = new TracksDTO(new ArrayList<>(Collections.singletonList(track)));
        playlist = new PlaylistDTO();
        playlist.setId(PLAYLIST_ID);
        playlist.setOwner(true);
        playlist.setTracks(tracklist.getTracks());

        playlists = new PlaylistsDTO(new ArrayList<>(Collections.singletonList(playlist)), 0);
    }

    @Test
    public void getPlaylistsForUsername() {
        when(trackDAO.getAllTracksForPlaylist(PLAYLIST_ID)).thenReturn(tracklist);
        when(tokenDAO.getUsernameFromToken(TOKEN))
                .thenReturn("kaene");
        when(playlistsDAO.getPlaylistsForUsername(anyString()))
                .thenReturn(playlists);

        PlaylistsDTO actualResult = sut.getPlaylistsForUsername(TOKEN);
        verify(playlistsDAO).getPlaylistsForUsername("kaene");
        assertEquals(playlists, actualResult);
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

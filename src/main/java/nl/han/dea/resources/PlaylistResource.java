package nl.han.dea.resources;

import nl.han.dea.service.PlaylistService;
import nl.han.dea.service.PlaylistTracksService;
import nl.han.dea.service.TokenService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    private PlaylistService playlistService;
    private PlaylistTracksService playlistTracksService;
    private TokenService tokenService;


    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(PlaylistService playlistService, PlaylistTracksService playlistTracksService, TokenService tokenService) {
        this.playlistService = playlistService;
        this.playlistTracksService = playlistTracksService;
        this.tokenService = tokenService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        //  tokenService.validateToken(token);
        return Response.ok(playlistService.getPlaylistsDTO("kaene")).build();

        //tokenService.getUserWithToken(token))
    }
}

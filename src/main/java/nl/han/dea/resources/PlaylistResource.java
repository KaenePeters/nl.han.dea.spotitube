package nl.han.dea.resources;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.TrackDTO;
import nl.han.dea.service.IPlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistResource {


    private IPlaylistService playlistService;

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistsForUsername(@QueryParam("token") String token) {
        return Response.ok(playlistService.getPlaylistsForUsername(token)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylistById(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        playlistService.deletePlaylistById(playlistId, token);
        return Response.ok(playlistService.getPlaylistsForUsername(token)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        playlistService.addPlaylist(playlistDTO, token);
        return Response.status(Response.Status.CREATED).entity(playlistService.getPlaylistsForUsername(token)).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistById(PlaylistDTO playlistDTO, @PathParam("id") int playlistId, @QueryParam("token") String token) {
        playlistService.editPlaylistById(playlistDTO, playlistId, token);
        return Response.ok(playlistService.getPlaylistsForUsername(token)).build();
    }


    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksForPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        return Response.ok(playlistService.getAllTracksForPlaylist(playlistId, token)).build();
    }

    @Path("{playlistId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        playlistService.removeTrackFromPlaylist(playlistId, trackId, token);
        return Response.ok(playlistService.getAllTracksForPlaylist(playlistId, token)).build();
    }

    @Path("{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token, TrackDTO trackDTO) {
        playlistService.addTrackToPlaylist(playlistId, trackDTO, token);
        return Response.ok(playlistService.getAllTracksForPlaylist(playlistId, token)).build();
    }
}

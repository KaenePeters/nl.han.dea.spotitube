package nl.han.dea.resources;

import nl.han.dea.DTO.PlaylistDTO;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.service.IPlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/playlists")
public class PlaylistResource {

    @Inject
    private IPlaylistService playlistService;

    public PlaylistResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        PlaylistsDTO playlistsDTO = playlistService.getPlaylistsDTO(token);
        return Response.status(Response.Status.OK).entity(playlistsDTO).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        playlistService.deletePlaylist(token, playlistId);
        PlaylistsDTO playlistsDTO = playlistService.getPlaylistsDTO(token);
        return Response.status(Response.Status.OK).entity(playlistsDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        playlistService.addPlaylist(playlistDTO, token);
        PlaylistsDTO playlistsDTO = playlistService.getPlaylistsDTO(token);
        return Response.status(Response.Status.OK).entity(playlistsDTO).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlistDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.editPlaylist(playlistDTO, token, id);
        PlaylistsDTO playlistsDTO = playlistService.getPlaylistsDTO(token);
        return Response.status(Response.Status.OK).entity(playlistsDTO).build();
    }


//
//    @Path("{id}/tracks")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllTracksForPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
//        TracksDTO tracksDTO = playlistService.getAllTracksForPlaylist(token, playlistId);
//        return Response.ok(tracksDTO).build();
//    }
//
//    @Path("{playlistId}/tracks/{trackId}")
//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
//        playlistService.deleteTrackFromPlaylist(token, playlistId, trackId);
//        TracksDTO tracksDTO = playlistService.getAllTracksForPlaylist(token, playlistId);
//        return Response.ok(tracksDTO).build();
//    }
//
//    @Path("{id}/tracks")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addTrackToPlaylist(TrackDTO trackDTO, @PathParam("id") int playlistId, @QueryParam("token") String token) {
//        playlistService.addTrackToPlaylist(token, playlistId, trackDTO);
//        TracksDTO tracksDTO = playlistService.getAllTracksForPlaylist(token, playlistId);
//        return Response.ok(tracksDTO).build();
//    }
}

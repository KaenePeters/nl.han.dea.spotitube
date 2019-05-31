package nl.han.dea.resources;

import nl.han.dea.DTO.TracksDTO;
import nl.han.dea.service.ITrackService;
import nl.han.dea.service.TrackServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    @Inject
    private ITrackService trackService = new TrackServiceImpl();

    public TrackResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int forPlaylist, @QueryParam("token") String token) {
        TracksDTO tracksDTO = trackService.getAllTracks(token, forPlaylist);
        return Response.status(Response.Status.OK).entity(tracksDTO).build();
    }


}

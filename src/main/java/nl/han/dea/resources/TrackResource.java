package nl.han.dea.resources;

import nl.han.dea.service.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {


    private ITrackService trackService;

    public TrackResource() {
    }

    @Inject
    public TrackResource(ITrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksNotInPlaylist(@QueryParam("forPlaylist") int forPlaylist, @QueryParam("token") String token) {
        return Response.ok(trackService.getAllTracksNotInPlaylist(forPlaylist, token)).build();
    }


}

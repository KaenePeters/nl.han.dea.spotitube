package nl.han.dea.DTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistsDTO {
    private List<Playlist> playlists = new ArrayList<Playlist>();

    public PlaylistsDTO() {

        Playlist playlist1 = new Playlist("test", true, 120, null);
        playlists.add(playlist1);
        Playlist playlist2 = new Playlist("test", true, 120, null);
        playlists.add(playlist2);
    }

    @GET
    @Path("/playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@PathParam("playlists") TokenDTO token) {
        return null;
    }


}

package nl.han.dea.resources;

import nl.han.dea.DTO.Playlist;
import nl.han.dea.DTO.PlaylistsDTO;
import nl.han.dea.persistence.PlaylistDao;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("playlists")
public class PlaylistResource {

    private PlaylistDao playlistDao = new PlaylistDao();

    public PlaylistResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        ArrayList<Playlist> playlists = playlistDao.playlists(token);

        if (playlists.get(0) == null) {
            return Response.status(403).build();
        }
        PlaylistsDTO response = new PlaylistsDTO();
        response.setPlaylists(playlists);
        response.setLength(11111);

        return Response.ok().entity(response).build();
    }
}

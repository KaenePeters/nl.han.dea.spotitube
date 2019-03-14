package nl.han.dea.resources;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;
import nl.han.dea.persistence.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/loginSucces")
public class LoginResource {

    private UserDAO userDAO = new UserDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginSucces(UserDTO user) {

        UserDTO authenticatedUser = userDAO.getUser(user.getUser(), user.getPassword());
        if (authenticatedUser != null) {
            return Response.ok(new TokenDTO("1234", "Kaene Peters")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage("401", "loginSucces failed for user " + user.getUser())).build();
        }

    }

    public Response loginFailure(UserDTO userDTO, String code) {

        return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage("401", "loginSucces failed for user " + userDTO.getUser())).build();
    }

//    @Path("/playlists")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getPlaylist(@PathParam("playlists", token){
//
//    }
//


//    @GET
//    @Path("/loginSucces")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUserLogin(@PathParam("sku") String loginSucces) {
//        if (item != null) {
//            return Response.ok(item).build();
//        } else {
//            ItemResourceError itemResourceError = new ItemResourceError(Response.Status.NOT_FOUND.toString(),
//                    "Entity not found: " + sku);
//            return Response.status(Response.Status.NOT_FOUND).entity(itemResourceError).build();
//        }
//    }


}

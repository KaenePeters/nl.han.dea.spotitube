package nl.han.dea.resources;

import nl.han.dea.DTO.ErrorDTO;
import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;
import nl.han.dea.persistence.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private UserDAO userDAO = new UserDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        UserDTO authenticatedUser = null;
        authenticatedUser = userDAO.getUser(user.getUser(), user.getPassword());
        if (authenticatedUser != null) {
            return Response.ok(new TokenDTO("1234", authenticatedUser.getName())).build();
        } else {
            return loginFailure(authenticatedUser);
        }

    }

    public Response loginFailure(UserDTO userDTO) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorDTO("login failed for user " + userDTO.getUser())).build();
    }


//    @GET
//    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUserLogin(@PathParam("login") String login) {
//        if (item != null) {
//            return Response.ok(item).build();
//        } else {
//            ItemResourceError itemResourceError = new ItemResourceError(Response.Status.NOT_FOUND.toString(),
//                    "Entity not found: " + sku);
//            return Response.status(Response.Status.NOT_FOUND).entity(itemResourceError).build();
//        }
//    }


}

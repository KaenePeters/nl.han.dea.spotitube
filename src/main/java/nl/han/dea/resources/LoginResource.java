package nl.han.dea.resources;

import nl.han.dea.TokenDTO;
import nl.han.dea.UserDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        if (userDTO.getUser().equals("meron") && userDTO.getPassword().equals("MySuperSecretPassword12341")) {
            return Response.ok(new TokenDTO("1234-1234-1234", "Meron Brouwer")).build();
        } else {
            return Response.status(401).build();
        }

    }


//    @GET
//    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUserLogin(@PathParam("sku") String login) {
//        if (item != null) {
//            return Response.ok(item).build();
//        } else {
//            ItemResourceError itemResourceError = new ItemResourceError(Response.Status.NOT_FOUND.toString(),
//                    "Entity not found: " + sku);
//            return Response.status(Response.Status.NOT_FOUND).entity(itemResourceError).build();
//        }
//    }


}

package nl.han.dea.resources;

import nl.han.dea.TokenDTO;
import nl.han.dea.UserDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/loginSucces")
public class LoginResource {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginSucces(UserDTO userDTO) {
        if (userDTO.getUser().equals("meron") && userDTO.getPassword().equals("MySuperSecretPassword12341")) {
            return Response.ok(new TokenDTO("1234", "Kaene Peters")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorDTO("401","loginSucces failed for user "+ userDTO.getUser())).build();
        }

    }

    public Response loginFailure(UserDTO userDTO){

        return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorDTO("401","loginSucces failed for user "+ userDTO.getUser())).build();
    }


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

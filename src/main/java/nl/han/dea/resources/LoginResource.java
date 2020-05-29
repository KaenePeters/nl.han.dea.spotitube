package nl.han.dea.resources;

import nl.han.dea.DTO.UserDTO;
import nl.han.dea.service.IAuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {


    private IAuthenticationService authenticationService;

    public LoginResource() {
    }

    @Inject
    public LoginResource(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        return Response.ok(authenticationService.login(userDTO)).build();
    }


}

package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SpotitubeLoginExceptionMapper implements ExceptionMapper<SpotitubeLoginException> {

    @Override
    public Response toResponse(SpotitubeLoginException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity((new ErrorDTO(exception.getMessage()))).build();
    }
}

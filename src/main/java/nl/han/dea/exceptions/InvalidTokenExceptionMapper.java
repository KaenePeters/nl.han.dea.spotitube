package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTokenExceptionMapper implements ExceptionMapper<InvalidTokenException> {

    @Override
    public Response toResponse(InvalidTokenException exception) {
        return Response.status(Response.Status.FORBIDDEN).entity((new ErrorDTO(exception.getMessage()))).build();
    }
}

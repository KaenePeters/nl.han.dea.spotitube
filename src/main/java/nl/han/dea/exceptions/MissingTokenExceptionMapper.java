package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingTokenExceptionMapper implements ExceptionMapper<MissingTokenException> {

    @Override
    public Response toResponse(MissingTokenException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity((new ErrorDTO(exception.getMessage()))).build();
    }
}

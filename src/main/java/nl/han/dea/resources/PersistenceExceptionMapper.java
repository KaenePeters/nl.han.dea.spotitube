package nl.han.dea.resources;

import nl.han.dea.DTO.ErrorDTO;
import nl.han.dea.persistence.SpotitubePersistenceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class PersistenceExceptionMapper implements ExceptionMapper<SpotitubePersistenceException> {

    @Override
    public Response toResponse(SpotitubePersistenceException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO("Database connection error. Please try again later.")).build();
    }
}

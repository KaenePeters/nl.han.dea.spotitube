package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class PersistenceExceptionMapperTest {
    private PersistenceException persistenceException = new PersistenceException("testString");
    private PersistenceExceptionMapper sut = new PersistenceExceptionMapper();


    @Test
    public void responsePersistenceExceptionSucces() {
        Response actualResult = sut.toResponse(persistenceException);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("testString", errorDTO.getMessage());
    }
}

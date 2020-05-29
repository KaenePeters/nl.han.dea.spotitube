package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class InvalidTokenExceptionMapperTest {

    private InvalidTokenException invalidTokenException = new InvalidTokenException("testString");
    private InvalidTokenExceptionMapper sut = new InvalidTokenExceptionMapper();


    @Test
    public void responseInvalidTokenExceptionSucces() {
        Response actualResult = sut.toResponse(invalidTokenException);
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("testString", errorDTO.getMessage());
    }
}

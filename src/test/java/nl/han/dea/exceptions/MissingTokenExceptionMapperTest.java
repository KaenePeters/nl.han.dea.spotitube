package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class MissingTokenExceptionMapperTest {

    private MissingTokenException missingTokenException = new MissingTokenException("testString");
    private MissingTokenExceptionMapper sut = new MissingTokenExceptionMapper();


    @Test
    public void responseMissingTokenExceptionSucces() {
        Response actualResult = sut.toResponse(missingTokenException);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("testString", errorDTO.getMessage());
    }
}

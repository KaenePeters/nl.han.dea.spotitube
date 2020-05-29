package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class SpotitubeLoginExceptionMapperTest {


    private SpotitubeLoginException spotitubeLoginException = new SpotitubeLoginException("testString");
    private SpotitubeLoginExceptionMapper sut = new SpotitubeLoginExceptionMapper();


    @Test
    public void responseLoginExceptionExceptionSucces() {
        Response actualResult = sut.toResponse(spotitubeLoginException);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("testString", errorDTO.getMessage());
    }
}

package nl.han.dea.exceptions;

import nl.han.dea.DTO.ErrorDTO;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class LoginExceptionMapperTest {


    private LoginException loginException = new LoginException("testString");
    private LoginExceptionMapper sut = new LoginExceptionMapper();


    @Test
    public void responseLoginExceptionExceptionSucces() {
        Response actualResult = sut.toResponse(loginException);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("testString", errorDTO.getMessage());
    }
}

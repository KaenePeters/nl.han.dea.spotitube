package nl.han.dea.resources;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;
import nl.han.dea.exceptions.SpotitubeLoginException;
import nl.han.dea.service.AuthenticationServiceImpl;
import nl.han.dea.service.IAuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginResourceTest {


    private String token = "1234";
    private UserDTO testUser = new UserDTO("testuser", "testpassword");

    @Mock
    private IAuthenticationService authenticationService;

    @Mock
    private SpotitubeLoginException loginException;

    @InjectMocks
    private LoginResource sut;

    @Test
    public void loginSucces() {
        when(authenticationService.login(any())).thenReturn(new TokenDTO(token, testUser.getUsername()));

        Response actualResult = sut.login(testUser);

        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
        assertEquals("testuser", actualToken.getUsername());
        assertEquals("1234", actualToken.getToken());
    }

    @Test
    public void loginFailure()  {
        when(authenticationService.login(any())).thenThrow(new SpotitubeLoginException("Login failed for user."));

        loginException = assertThrows(SpotitubeLoginException.class, () -> sut.login(new UserDTO("testuser", "")));

        assertEquals("Login failed for user.", loginException.getMessage());

    }

    @Test
    public void testConstructor() {
        LoginResource loginResource = new LoginResource();
    }


}

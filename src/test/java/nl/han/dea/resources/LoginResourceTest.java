package nl.han.dea.resources;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.IUserDAO;
import nl.han.dea.persistence.TokenDaoImpl;
import nl.han.dea.persistence.UserDAO;
import nl.han.dea.service.IAuthenticationService;
import nl.han.dea.util.TokenGenerator;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginResourceTest {

    private String token = "1234";

    @Mock
    private IAuthenticationService authenticationService;


    @Mock
    private ITokenDAO tokenDAO;
    @Mock
    private IUserDAO userDAO;
    @Mock
    private TokenGenerator tokenGenerator;

    @InjectMocks
    private LoginResource sut;

    @BeforeEach
    void setUp() {
        sut = new LoginResource();
        tokenDAO = new TokenDaoImpl();
        userDAO = new UserDAO();
        tokenGenerator = new TokenGenerator();
    }

    @Test
    public void loginSucces() {
        UserDTO testUser = new UserDTO("testuser", "testpassword");
        when(tokenGenerator.generateToken()).thenReturn(token);
        when(userDAO.getUser(testUser)).thenReturn(testUser);
        when(tokenDAO.saveAndReturnNewToken(testUser.getUsername(), token)).thenReturn(token);
        when(authenticationService.login(testUser)).thenReturn(new TokenDTO(token, testUser.getUsername()));

        Response actualResult = sut.login(testUser);

        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
        assertEquals("testuser", actualToken.getUsername());
        assertEquals("1234", actualToken.getToken());
    }

    @Test
    public void loginFailure() {
        UserDTO user = new UserDTO("testuser", "testpassword");
        when(authenticationService.login(user)).thenThrow(new LoginException("Login failed for user."));

        LoginException loginException = assertThrows(LoginException.class, () -> sut.login(new UserDTO("testuser", "testpassword")));

        assertEquals("Login failed for user.", loginException.getMessage());
    }

    @Test
    public void testConstructor() {
        LoginResource loginResource = new LoginResource();
    }


}

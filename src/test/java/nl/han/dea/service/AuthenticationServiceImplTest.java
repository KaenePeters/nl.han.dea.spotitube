package nl.han.dea.service;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;
import nl.han.dea.exceptions.SpotitubeLoginException;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.UserDAO;
import nl.han.dea.util.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @Mock
    private TokenGenerator tokenGenerator;

    @Mock
    private ITokenDAO tokenDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private AuthenticationServiceImpl sut;

    @Test
    void loginSucces() {
        UserDTO userDTO = new UserDTO("Test", "password");

        Mockito.when(userDAO.getUser(userDTO))
                .thenReturn(userDTO);
        Mockito.when(tokenGenerator.generateToken()).thenReturn("TOKEN");
        TokenDTO tokenDTO = new TokenDTO("TOKEN", userDTO.getUsername());

        sut.login(userDTO);

        Mockito.verify(tokenDAO).saveAndReturnNewToken(tokenDTO.getUsername(), tokenDTO.getToken());
    }

    @Test
    void loginFailure() {
        Mockito.when(userDAO.getUser(any())).thenReturn(null);

        SpotitubeLoginException loginException = assertThrows(SpotitubeLoginException.class, () -> sut.login(any()));

        assertEquals("Login failed for user.", loginException.getMessage());
    }


    @Test
    void testConstructor() {
        IAuthenticationService authenticationService = new AuthenticationServiceImpl();
    }
}

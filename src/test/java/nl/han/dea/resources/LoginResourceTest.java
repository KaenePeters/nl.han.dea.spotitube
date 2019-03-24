//package nl.han.dea.resources;
//
//import nl.han.dea.DTO.ErrorDTO;
//import nl.han.dea.DTO.TokenDTO;
//import nl.han.dea.DTO.UserDTO;
//import nl.han.dea.persistence.UserDAO;
//import nl.han.dea.util.TokenGenerator;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.ws.rs.core.Response;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class LoginResourceTest {
//
//    @Mock
//    private UserDAO userDAOStub;
//    private TokenGenerator tokenGenerator;
//
//    @InjectMocks
//    private LoginResource sut;
//
//
//    @Test
//    public void login() {
//
//        UserDTO mockedUser = new UserDTO();
//        mockedUser.setName("Testuser");
//        mockedUser.setPassword("testpassword");
//        mockedUser.setUser("testuser");
//        Mockito.when(userDAOStub.getUser("testuser", "testpassword")).thenReturn(mockedUser);
//
//        Mockito.when(tokenGenerator.generateUUID()).thenReturn("1234");
//
//        UserDTO userDTO = new UserDTO("testuser", "testpassword");
//        Response actualResult = sut.login(userDTO);
//
//        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
//
//        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
//        assertEquals("Test Testuser", actualToken.getUser());
//        assertEquals("1234", actualToken.getToken());
//    }
//
//    @Test
//    public void loginFailure() {
//        UserDTO userDTO = new UserDTO("kaene", "12354");
//
//        Response actualResult = sut.loginFailure(userDTO);
//        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());
//
//        ErrorDTO actualDTO = (ErrorDTO) actualResult.getEntity();
//        assertEquals("Login failed for user kaene", actualDTO.getMessage());
//    }
//
//    @Test
//    public void generateUUID() {
//        System.out.println(UUID.randomUUID());
//    }
//}
//package nl.han.dea.resources;
//
//import nl.han.dea.DTO.TokenDTO;
//import nl.han.dea.DTO.UserDTO;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//import javax.ws.rs.core.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class LoginResourceTest {
//
//    private LoginResource sut;
//
//    @BeforeEach
//    void setUp() {
//        sut = new LoginResource();
//    }
//
//    @Test
//    public void loginTry() {
//        UserDTO userDTO = new UserDTO();
//
//        Response actualResult = sut.loginSucces(userDTO);
//        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
//
//        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
//        assertEquals("Kaene Peters", actualToken.getUser());
//        assertEquals("1234", actualToken.getToken());
//    }
//
//    @Test
//    void loginFailure() {
//        UserDTO wrongUserDto = new UserDTO("Kaene Peters", "12354");
//        Response actualResult = sut.loginFailure(wrongUserDto);
//        assertEquals(401,actualResult.getStatus());
//
//        TokenDTO wrongTokenDto2 = new TokenDTO("12345","Kaene Pters");
//        Response actualResult2 = sut.loginFailure();
//        assertEquals(403,actualResult2);
//    }
//}
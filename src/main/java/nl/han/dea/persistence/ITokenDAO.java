package nl.han.dea.persistence;



public interface ITokenDAO {

    String getUsernameFromToken(String token);


    String saveAndReturnNewToken(String username, String generateToken);


}

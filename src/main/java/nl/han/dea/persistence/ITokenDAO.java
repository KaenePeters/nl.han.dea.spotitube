package nl.han.dea.persistence;



public interface ITokenDAO {

    String tokenIsValid(String token);


    String saveAndReturnNewToken(String username, String generateToken);


}

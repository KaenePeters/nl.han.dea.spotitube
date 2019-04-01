package nl.han.dea.persistence;

public interface TokenDAO {
    String getUserWithToken(String token);

    //String getTokenOfUser(String username);

    String createNewTokenForUser(String username);

    boolean tokenIsValid(String token);


}

package nl.han.dea.service;

public interface TokenService {

    public boolean validateToken(String token);

    String getUserWithToken(String token);
}

package nl.han.dea.service;

import nl.han.dea.exceptions.InvalidTokenException;
import nl.han.dea.persistence.TokenDAO;

import javax.inject.Inject;

public class TokenServiceImpl implements TokenService {

    private TokenDAO tokenDAO;

    public TokenServiceImpl() {
    }

    @Inject
    public TokenServiceImpl(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public boolean validateToken(String token) {
        if (!tokenDAO.tokenIsValid(token)) {
            throw new InvalidTokenException("");
        }
        return true;
    }

    @Override
    public String getUserWithToken(String token) {
        return null;
    }
}

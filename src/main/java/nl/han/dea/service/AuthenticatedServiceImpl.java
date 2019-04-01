package nl.han.dea.service;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;
import nl.han.dea.exceptions.SpotitubeLoginException;
import nl.han.dea.persistence.TokenDAO;
import nl.han.dea.persistence.UserDAO;
import nl.han.dea.util.TokenGenerator;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticatedServiceImpl implements AuthenticationService {
    private TokenDAO tokenDAO;
    private UserDAO userDAO;
    private TokenGenerator tokenGenerator = new TokenGenerator();

    @Inject
    public AuthenticatedServiceImpl(UserDAO userDAO, TokenDAO tokenDAO) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
    }


    @Override
    public TokenDTO login(String username, String password) {
        UserDTO user = userDAO.getUser(username, password);
        if (user != null) {
            return new TokenDTO(tokenGenerator.generateToken(), user.getName());
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username);
        }
    }
}

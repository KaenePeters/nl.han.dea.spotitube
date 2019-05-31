package nl.han.dea.service;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;
import nl.han.dea.exceptions.SpotitubeLoginException;
import nl.han.dea.persistence.ITokenDAO;
import nl.han.dea.persistence.IUserDAO;
import nl.han.dea.persistence.UserDAO;
import nl.han.dea.util.TokenGenerator;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticatedServiceImpl implements IAuthenticationService {
    @Inject
    private ITokenDAO iTokenDAO;

    private IUserDAO userDAO;
    private TokenGenerator tokenGenerator = new TokenGenerator();

    @Inject
    public AuthenticatedServiceImpl(UserDAO userDAO, ITokenDAO ITokenDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public TokenDTO login(String username, String password) {
        UserDTO user = userDAO.getUser(username, password);
        if (user != null) {
            String newToken = iTokenDAO.saveAndReturnNewToken(username, tokenGenerator.generateToken());
            return new TokenDTO(newToken, user.getName());
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username);
        }
    }
}

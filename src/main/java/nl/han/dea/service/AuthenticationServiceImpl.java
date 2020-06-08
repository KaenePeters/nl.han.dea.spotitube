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
public class AuthenticationServiceImpl implements IAuthenticationService {

    private ITokenDAO iTokenDAO;
    private IUserDAO userDAO;
    private TokenGenerator tokenGenerator;

    public AuthenticationServiceImpl() {
    }

    @Inject
    public AuthenticationServiceImpl(UserDAO userDAO, TokenGenerator tokenGenerator, ITokenDAO iTokenDAO) {
        this.userDAO = userDAO;
        this.tokenGenerator = tokenGenerator;
        this.iTokenDAO = iTokenDAO;
    }

    @Override
    public TokenDTO login(UserDTO userDTO) {
        UserDTO user = userDAO.getUser(userDTO);
        if (user != null) {
            return new TokenDTO(iTokenDAO.saveAndReturnNewToken(userDTO.getUsername(), tokenGenerator.generateToken()), user.getUsername());
        } else {
            throw new SpotitubeLoginException("Login failed for user.");
        }
    }
}

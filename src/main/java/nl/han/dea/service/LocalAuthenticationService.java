package nl.han.dea.service;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;

import javax.enterprise.inject.Alternative;

@Alternative
public class LocalAuthenticationService implements IAuthenticationService {

    @Override
    public TokenDTO login(UserDTO userDTO) {
        return new TokenDTO("12345", userDTO.getUsername());
    }
}

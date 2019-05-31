package nl.han.dea.service;

import nl.han.dea.DTO.TokenDTO;

public interface IAuthenticationService {

    TokenDTO login(String username, String password);

}

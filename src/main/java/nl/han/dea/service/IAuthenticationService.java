package nl.han.dea.service;

import nl.han.dea.DTO.TokenDTO;
import nl.han.dea.DTO.UserDTO;

public interface IAuthenticationService {

    TokenDTO login(UserDTO userDTO);

}

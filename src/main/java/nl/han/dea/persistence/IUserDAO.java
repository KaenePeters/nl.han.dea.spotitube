package nl.han.dea.persistence;

import nl.han.dea.DTO.UserDTO;

public interface IUserDAO {

    UserDTO getUser(UserDTO userDTO);
}

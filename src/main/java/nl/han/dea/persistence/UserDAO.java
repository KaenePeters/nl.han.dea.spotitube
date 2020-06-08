package nl.han.dea.persistence;

import nl.han.dea.DTO.UserDTO;
import nl.han.dea.exceptions.PersistenceException;
import nl.han.dea.persistence.database.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Default
public class UserDAO implements IUserDAO {

    public UserDTO getUser(UserDTO userDTO) {
        UserDTO user = null;
        String query = "SELECT account.user, account.password, account.name from account where user = ? and password = ?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userDTO.getUsername());
            preparedStatement.setString(2, userDTO.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserDTO();
                user.setName(resultSet.getString("name"));
                user.setUsername(userDTO.getUsername());
                user.setPassword(userDTO.getPassword());

            }
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later");
        }
        return user;
    }


}

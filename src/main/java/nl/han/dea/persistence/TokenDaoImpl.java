package nl.han.dea.persistence;

import nl.han.dea.exceptions.PersistenceException;
import nl.han.dea.persistence.database.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class TokenDaoImpl implements ITokenDAO {

    @Override
    public String getUsernameFromToken(String token) {
        String query = "SELECT username FROM token WHERE token=?";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("username");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
        return null;
    }

    @Override
    public String saveAndReturnNewToken(String username, String generateToken) {
        String query = "insert into token (username, token, expiredate) value (?,?,now() + interval 7 day)";
        try (
                Connection connection = new ConnectionFactory().getConnecion();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, generateToken);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database connection error. Please try again later.");
        }
        return generateToken;
    }

}

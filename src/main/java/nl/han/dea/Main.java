package nl.han.dea;

import java.sql.*;

//eerst de dependency toevoegen
//dan de klasnaam opzoeken en in forName invullen
//dan connection maken met try catch
//een prepared statement maken met query
//resultSet is een pointer
//dan met bijv een while loop de data ophalen
public class Main {


    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotitube?useSSL=false&serverTimezone=UTC",
                "root", "");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE user = ?");
        ) {
            //?useSSL=false achter de getConnection url;
            preparedStatement.setString(1, "kaene");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("user"));
                resultSet.getString("password");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

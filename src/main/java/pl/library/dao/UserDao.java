package pl.library.dao;

import pl.ConnectionDB;
import pl.library.mapper.UserMapper;
import pl.library.model.User;
import pl.library.service.password.PasswordCoder;

import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {

    private final PasswordCoder passwordCoder = new PasswordCoder();

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM reader WHERE email = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();



            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("password")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePassword(int id, String newPassword) {
        String sql = "UPDATE reader SET password = ? WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newPassword);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User createUser(String password, String login, String firstName, String lastName) {

        String hash = passwordCoder.hashPassword(password);

        String sql = " INSERT INTO reader (first_name, last_name, email, role, password) VALUES (?, ?, ?, ?, ?) ";

        String role = "STANDARD";


        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, login);
            ps.setString(4, role);
            ps.setString(5, hash);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);

                return new User(
                        id,
                        firstName,
                        lastName,
                        login,
                        role,
                        hash
                );

            }


        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return null;

    }

}

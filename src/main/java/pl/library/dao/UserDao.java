package pl.library.dao;

import pl.ConnectionDB;
import pl.library.model.User;

import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {

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

}

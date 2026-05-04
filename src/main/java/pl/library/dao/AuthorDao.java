package pl.library.dao;

import pl.ConnectionDB;
import pl.library.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    public List<Author> authorsGetAll() {

        String sql = "Select * from author";
        List<Author> findAuthor = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("first_name");
                String surname = rs.getString("last_name");

                Author temp = new Author(id, name, surname);
                findAuthor.add(temp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findAuthor;
    }

    public void deleteAuthorById(int id) {

        String sql = "Delete from author where id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

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

    public void updateAuthorByID(int id, String firstName, String lastName) {
        String sql = "Update author set first_name = ?, last_name = ? where id = ?";

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAuthor(String name, String surname) {

        String sql = "Insert into author (first_name, last_name) values (?, ?)";

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

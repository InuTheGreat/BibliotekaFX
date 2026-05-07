package pl.library.dao;

import pl.ConnectionDB;
import pl.library.model.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao {

    public List<Publisher> publishersGetAll() {
        String sql = "Select * from publisher";
        List<Publisher> finds = new ArrayList<>();
        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Publisher publisher = new Publisher(id, name);
                finds.add(publisher);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return finds;
    }

    public void addPublisher(String name) {

        String sql = "Insert into publisher (name) values (?)";

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deletePublisher(int id, String name) {

        String sql = "Delete from publisher where id = ? and name = ?";

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, name);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePublisherByID(int id, String name) {
        String sql = "Update publisher set name = ? where id = ?";

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

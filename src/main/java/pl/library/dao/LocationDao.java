package pl.library.dao;

import pl.ConnectionDB;
import pl.library.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LocationDao {

    public Location addLocation(String section, String shelf, String rack) {

        String sql = "Insert into location (section, shelf, rack) values (?, ?, ?)";

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, section);
            preparedStatement.setString(2, shelf);
            preparedStatement.setString(3, rack);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                return new Location(id, section, shelf, rack);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            return null;
    }
}

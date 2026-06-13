package pl.library.dao.utility;

import pl.ConnectionDB;
import pl.library.dao.help.MonthView;
import pl.library.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticDao {

    public int getUsersValue() {

        String sql = "SELECT COUNT(*) AS users_count FROM reader;";

        int count = 0;

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                count = rs.getInt("users_count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int getBorrowValue() {

        String sql = "SELECT COUNT(*) AS borrow_count FROM borrowing;";

        int count = 0;

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                count = rs.getInt("borrow_count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public List<MonthView> getBorrowStatsByMonth() {

        String sql = """
            SELECT MONTH(borrow_date) AS month, COUNT(*) AS total
            FROM borrowing
            GROUP BY MONTH(borrow_date)
            ORDER BY month
         """;

        List<MonthView> listMonthTotal = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listMonthTotal.add(new MonthView(
                        rs.getInt("month"),
                        rs.getInt("total")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listMonthTotal;
    }
}

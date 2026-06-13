package pl.library.dao;

import pl.ConnectionDB;
import pl.library.dto.BorrowingView;
import pl.library.model.Borrowing;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDao {

    public void addBorrowing(int readerId, List<Integer> bookIds) {

        String sqlCheck = "SELECT available FROM book WHERE id = ? AND available = 1";
        String sqlBorrowing = "INSERT INTO borrowing (reader_id, borrow_date) VALUES (?, ?)";
        String sqlUpdateBook = "UPDATE book SET available = 0 WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection()) {

            connection.setAutoCommit(false);

            try {
                try (PreparedStatement psCheck = connection.prepareStatement(sqlCheck)) {
                    for (Integer bookId : bookIds) {
                        psCheck.setInt(1, bookId);
                        ResultSet rs = psCheck.executeQuery();
                        if (!rs.next()) {
                            throw new RuntimeException("Książka o ID " + bookId + " jest niedostępna!");
                        }
                    }
                }

                try (PreparedStatement ps = connection.prepareStatement(sqlBorrowing, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, readerId);
                    ps.setDate(2, Date.valueOf(LocalDate.now()));
                    ps.executeUpdate();

                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int borrowingId = rs.getInt(1);
                        saveBooks(connection, borrowingId, bookIds);
                    }
                }

                try (PreparedStatement psUpdate = connection.prepareStatement(sqlUpdateBook)) {
                    for (Integer bookId : bookIds) {
                        psUpdate.setInt(1, bookId);
                        psUpdate.addBatch();
                    }
                    psUpdate.executeBatch();
                }

                connection.commit();

            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveBooks(Connection connection, int borrowingId, List<Integer> bookIds) throws SQLException {

        String sql = "INSERT INTO borrowing_book (borrowing_id, book_id) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Integer bookId : bookIds) {
                ps.setInt(1, borrowingId);
                ps.setInt(2, bookId);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public List<Borrowing> getUserBorrowings(int readerId) {

        String sql = """
                SELECT b.id, b.borrow_date, bb.book_id, bb.return_date,
                       bk.title, bk.isbn
                FROM borrowing b
                JOIN borrowing_book bb ON b.id = bb.borrowing_id
                JOIN book bk ON bk.id = bb.book_id
                WHERE b.reader_id = ?
                """;

        List<Borrowing> borrowings = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, readerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                borrowings.add(new Borrowing(
                        rs.getInt("id"),
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("isbn"),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return borrowings;
    }

    public List<BorrowingView> getActiveBorrowings() {

        String sql = """
                SELECT bb.borrowing_id, bb.book_id,
                       b.borrow_date,
                       bk.title, bk.isbn,
                       r.first_name, r.last_name, r.email
                FROM borrowing_book bb
                JOIN borrowing b ON b.id = bb.borrowing_id
                JOIN book bk ON bk.id = bb.book_id
                JOIN reader r ON r.id = b.reader_id
                WHERE bb.return_date IS NULL
                """;

        List<BorrowingView> list = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new BorrowingView(
                        rs.getInt("borrowing_id"),
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("isbn"),
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getString("first_name") + " " + rs.getString("last_name"),
                        rs.getString("email")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void returnBook(int borrowingId, int bookId) {

        String sqlReturn = "UPDATE borrowing_book SET return_date = ? WHERE borrowing_id = ? AND book_id = ?";
        String sqlAvailable = "UPDATE book SET available = 1 WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection()) {

            connection.setAutoCommit(false);

            try {
                try (PreparedStatement ps = connection.prepareStatement(sqlReturn)) {
                    ps.setDate(1, Date.valueOf(LocalDate.now()));
                    ps.setInt(2, borrowingId);
                    ps.setInt(3, bookId);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = connection.prepareStatement(sqlAvailable)) {
                    ps.setInt(1, bookId);
                    ps.executeUpdate();
                }

                connection.commit();

            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
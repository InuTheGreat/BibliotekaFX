package pl.library.dao;

import pl.ConnectionDB;
import pl.library.dto.BookView;
import pl.library.mapper.BookMapper;
import pl.library.model.Book;
import pl.library.model.Genre;
import pl.library.model.Location;
import pl.library.model.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public List<BookView> getAllBooks() {

        String sql = """
                SELECT
                            b.id,
                            b.title,
                            b.isbn,
                            b.publication_year,
                            b.available,
                            b.pages,
               \s
                            p.id AS publisher_id,
                            p.name AS publisher_name,
                           \s
                            l.id AS location_id,
                                l.section,
                                l.shelf,
                                l.rack,
                               \s
                            g.id AS genre_id,
                            g.name AS genre_name
               \s
                        FROM book b
                        LEFT JOIN publisher p ON p.id = b.publisher_id
                        LEFT JOIN location l ON l.id = b.location_id
                        LEFT JOIN genre g ON g.id = b.genre_id
               \s
               \s""";
        List<BookView> list = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){

                Book foundBook = new Book (

                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("isbn"),
                        rs.getInt("publication_year"),
                        rs.getInt("pages"),
                        rs.getInt("publisher_id"),
                        rs.getInt("location_id"),
                        rs.getInt("genre_id"),
                        null
                );


                Publisher foundPublisher = new Publisher (
                        rs.getInt("publisher_id"),
                        rs.getString("publisher_name")
                );

                Location foundLocation = new Location (
                        rs.getInt("location_id"),
                        rs.getString("section"),
                        rs.getString("shelf"),
                        rs.getString("rack")
                );

                Genre foundGenre = new Genre(rs.getInt("genre_id"), rs.getString("genre_name"));

                BookView bookView = BookMapper.toBookView(foundBook, foundLocation, foundGenre, foundPublisher);

                list.add(bookView);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return list;
    }
}

package pl.library.mapper;

import pl.library.dto.BookView;
import pl.library.model.Book;
import pl.library.model.Genre;
import pl.library.model.Location;
import pl.library.model.Publisher;

public class BookMapper {

    public static BookView toBookView(Book book, Location location, Genre genre, Publisher publisher) {

        return new BookView (

                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.isAvailable(),
                book.getPages(),
                publisher.getName(),
                location.getSection() + " / " + location.getShelf() + " / " + location.getRack(),
                genre.getName()
        );
    }
}

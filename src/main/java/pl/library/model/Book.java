package pl.library.model;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String isbn;
    private int publicationYear;
    private boolean available;
    private int pages;

    private int publisherId;
    private int locationId;
    private int genreId;

    public Book(int id, String title, String isbn, int publicationYear, int pages, int publisherId, int locationId, int genreId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = true;
        this.pages = pages;
        this.publisherId = publisherId;
        this.locationId = locationId;
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getPages() {
        return pages;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

}

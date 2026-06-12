package pl.library.model;

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

    public Book(int id, String title, String isbn, int publicationYear, int pages, boolean available, int publisherId, int locationId, int genreId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = available;
        this.pages = pages;
        this.publisherId = publisherId;
        this.locationId = locationId;
        this.genreId = genreId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public int getPublisherId() { return publisherId; }
    public void setPublisherId(int publisherId) { this.publisherId = publisherId; }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }

    public int getGenreId() { return genreId; }
    public void setGenreId(int genreId) { this.genreId = genreId; }
}
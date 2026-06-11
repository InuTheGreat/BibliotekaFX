package pl.library.dto;

public class BookView {

    private int id;
    private String title;
    private String isbn;
    private int publicationYear;
    private boolean available;
    private int pages;

    private String publisherName;
    private String location;
    private String genreName;

    public BookView(int id, String title, String isbn, int publicationYear, boolean available, int pages, String publisherName, String location, String genreName) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = available;
        this.pages = pages;
        this.publisherName = publisherName;
        this.location = location;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPublisherName() {
        return publisherName;
    }

    public String getGenreName() {
        return genreName;
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

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }


    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

}

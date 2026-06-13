package pl.library.dto;

import java.time.LocalDate;

public class BorrowingView {

    private int borrowingId;
    private int bookId;
    private String title;
    private String isbn;
    private LocalDate borrowDate;
    private String readerName;
    private String readerEmail;

    public BorrowingView(int borrowingId, int bookId, String title, String isbn, LocalDate borrowDate, String readerName, String readerEmail) {
        this.borrowingId = borrowingId;
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.readerName = readerName;
        this.readerEmail = readerEmail;
    }

    public int getBorrowingId() { return borrowingId; }
    public void setBorrowingId(int borrowingId) { this.borrowingId = borrowingId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public String getReaderName() { return readerName; }
    public void setReaderName(String readerName) { this.readerName = readerName; }

    public String getReaderEmail() { return readerEmail; }
    public void setReaderEmail(String readerEmail) { this.readerEmail = readerEmail; }
}
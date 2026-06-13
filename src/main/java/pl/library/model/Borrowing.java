package pl.library.model;

import java.time.LocalDate;

public class Borrowing {

    private int id;
    private LocalDate borrowDate;
    private int bookId;
    private String title;
    private String isbn;
    private LocalDate returnDate;

    public Borrowing(int id, LocalDate borrowDate, int bookId, String title, String isbn, LocalDate returnDate) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.returnDate = returnDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public boolean isReturned() {
        return returnDate != null;
    }
}
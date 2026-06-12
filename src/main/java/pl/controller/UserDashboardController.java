package pl.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.library.dao.BookDao;
import pl.library.dao.BorrowingDao;
import pl.library.dto.BookView;
import pl.library.model.Borrowing;
import pl.session.UserSession;

import java.util.List;

public class UserDashboardController {

    private final BookDao bookDao = new BookDao();
    private final BorrowingDao borrowingDao = new BorrowingDao();

    @FXML
    private TableView<BookView> booksTableView;

    @FXML
    private TableColumn<BookView, Integer> bookIdColumn;

    @FXML
    private TableColumn<BookView, String> titleColumn;

    @FXML
    private TableColumn<BookView, String> isbnColumn;

    @FXML
    private TableColumn<BookView, String> publisherColumn;

    @FXML
    private TableColumn<BookView, String> genreColumn;

    @FXML
    private TableColumn<BookView, Boolean> availableColumn;

    @FXML
    private TableView<Borrowing> borrowingsTableView;

    @FXML
    private TableColumn<Borrowing, String> borTitleColumn;

    @FXML
    private TableColumn<Borrowing, String> borIsbnColumn;

    @FXML
    private TableColumn<Borrowing, String> borDateColumn;

    @FXML
    private TableColumn<Borrowing, String> borReturnColumn;

    @FXML
    private Button borrowBtn;

    @FXML
    public void initialize() {

        // konfiguracja tabeli książek
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genreName"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        // konfiguracja tabeli wypożyczeń
        borTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        borIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        borDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        borReturnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        borrowBtn.setDisable(true);

        // odblokuj przycisk tylko gdy zaznaczona dostępna książka
        booksTableView.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null && selected.isAvailable()) {
                borrowBtn.setDisable(false);
            } else {
                borrowBtn.setDisable(true);
            }
        });

        borrowBtn.setOnAction(e -> borrowBook());

        loadData();
    }

    private void loadData() {
        // załaduj wszystkie książki
        List<BookView> books = bookDao.getAllBooks();
        booksTableView.setItems(FXCollections.observableArrayList(books));

        // załaduj wypożyczenia zalogowanego użytkownika
        List<Borrowing> borrowings = borrowingDao.getUserBorrowings(UserSession.getUser().getId());
        borrowingsTableView.setItems(FXCollections.observableArrayList(borrowings));
    }

    private void borrowBook() {
        BookView selected = booksTableView.getSelectionModel().getSelectedItem();
        if (selected == null || !selected.isAvailable()) return;

        borrowingDao.addBorrowing(
                UserSession.getUser().getId(),
                List.of(selected.getId())
        );

        loadData();
        booksTableView.getSelectionModel().clearSelection();
    }
}
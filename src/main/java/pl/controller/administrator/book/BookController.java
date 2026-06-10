package pl.controller.administrator.book;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.library.dao.BookDao;
import pl.library.dto.BookView;

import java.util.List;

public class BookController {

    private ObservableList<BookView> bookList;
    private BookDao bookDao = new BookDao();

    @FXML
    private Button addBookBtn;

    @FXML
    private ListView<String> authorsListView;

    @FXML
    private TableColumn<BookView, Boolean> availableColumn;

    @FXML
    private Label availableLabel;

    @FXML
    private TableColumn<BookView, Integer> bookIdColumn;

    @FXML
    private Label genreLabel;

    @FXML
    private Label idLabel;

    @FXML
    private TableColumn<BookView, String> isbnColumn;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label pagesLabel;

    @FXML
    private TableColumn<BookView, String> publisherColumn;

    @FXML
    private Label publisherLabel;

    @FXML
    private TableView<BookView> tableViewId;

    @FXML
    private TableColumn<BookView, String> titleColumn;

    @FXML
    private Label titleLabel;

    @FXML
    private TableColumn<BookView, Integer> yearColumn;

    @FXML
    private Label yearLabel;

    @FXML
    public void initialize() {

        bookList = FXCollections.observableArrayList();

        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisherName"));

        tableViewId.setItems(bookList);

        loadsData();

    }



    private void loadsData() {
        List<BookView> temp = bookDao.getAllBooks();
        tableViewId.getItems().setAll(temp);
    }

}


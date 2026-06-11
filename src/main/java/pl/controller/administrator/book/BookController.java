package pl.controller.administrator.book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.library.dao.AuthorDao;
import pl.library.dao.BookDao;
import pl.library.dto.BookView;
import pl.library.model.Author;

import java.util.List;

public class BookController {

    private ObservableList<BookView> bookList;
    private BookDao bookDao = new BookDao();
    private AuthorDao authorDao = new AuthorDao();

    @FXML
    private Button addBookBtn;

    @FXML
    private ListView<Author> authorsListView;

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

        tableViewId.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {

            if(selected == null) {
               return;
            }

            String isAvailable = String.valueOf(selected.isAvailable());
            availableLabel.setText(isAvailable);

            idLabel.setText(String.valueOf(selected.getId()));
            genreLabel.setText(selected.getGenreName());
            isbnLabel.setText(selected.getIsbn());
            locationLabel.setText(selected.getLocation());
            publisherLabel.setText(selected.getPublisherName());
            titleLabel.setText(selected.getTitle());
            yearLabel.setText(String.valueOf(selected.getPublicationYear()));
            pagesLabel.setText(String.valueOf(selected.getPages()));
            authorsListView.getItems().setAll(authorDao.findByBookId(selected.getId()));

        });

        addBookBtn.setOnAction(e -> openAddWindow());

    }



    private void loadsData() {
        List<BookView> temp = bookDao.getAllBooks();
        tableViewId.getItems().setAll(temp);
    }

    private void openAddWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/administrator/book/addBooks.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Kreator Książki");
            stage.setScene(new Scene(root));

            stage.showAndWait();

            loadsData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


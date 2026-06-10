package pl.controller.administrator.book;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.library.dao.*;
import pl.library.model.Author;
import pl.library.model.Genre;
import pl.library.model.Location;
import pl.library.model.Publisher;

import java.util.List;

public class BookAddController {

    private final AuthorDao authorDao = new AuthorDao();
    private final GenreDao genreDao = new GenreDao();
    private final PublisherDao publisherDao = new PublisherDao();
    private final LocationDao locationDao = new LocationDao();
    private final BookDao bookDao = new BookDao();

    @FXML
    private ListView<Author> authorsListView;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Genre> genreCombo;

    @FXML private TextField buildingField;

    @FXML private TextField roomField;

    @FXML private TextField shelfField;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField pagesField;

    @FXML
    private ComboBox<Publisher> publisherCombo;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    public void initialize() {

        cancelBtn.setOnAction(e -> close());

        saveBtn.setOnAction(e -> createBook());

        List<Publisher> publisherList = publisherDao.publishersGetAll();
        List<Genre> genreList = genreDao.genresGetAll();
        List<Author> authorList = authorDao.authorsGetAll();

        publisherCombo.setItems(FXCollections.observableArrayList(publisherList));
        genreCombo.setItems(FXCollections.observableArrayList(genreList));
        authorsListView.getItems().setAll(authorList);

        authorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }

    private void createBook() {

        Location newLocation = locationDao.addLocation(buildingField.getText(), roomField.getText(), shelfField.getText());

        Publisher publisher = publisherCombo.getValue();
        Genre genre = genreCombo.getValue();

        List<Integer> authorsId = authorsListView.getSelectionModel()
                .getSelectedItems()
                .stream()
                .map(Author::getId)
                .toList();

        bookDao.addBook(titleField.getText(), isbnField.getText(), String.valueOf(
                    yearField.getText()),
                    String.valueOf(pagesField.getText()),
                    String.valueOf(publisher.getId()),
                    String.valueOf(newLocation.getId()),
                    String.valueOf(genre.getId()),
                    authorsId
                );

        close();
    }

    private void close() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }


}

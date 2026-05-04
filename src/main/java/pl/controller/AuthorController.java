package pl.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.library.dao.AuthorDao;
import pl.library.model.Author;
import pl.view.ViewModel;

import java.util.List;

public class AuthorController {

    AuthorDao authorDao = new AuthorDao();
    ViewModel viewModel = new ViewModel();

    private ObservableList<Author> authorsList;

    @FXML
    private TableColumn<Author, Integer> autId;

    @FXML
    private TableColumn<Author, String> autNamId;

    @FXML
    private TableColumn<Author, String> autSurnId;

    @FXML
    private TableView<Author> tableViewId;

    @FXML
    private CheckBox checkId;

    @FXML
    private Button deleteBtn;

    @FXML
    public void initialize() {
        autId.setCellValueFactory(new PropertyValueFactory<>("id"));
        autNamId.setCellValueFactory(new PropertyValueFactory<>("name"));
        autSurnId.setCellValueFactory(new PropertyValueFactory<>("surname"));
        authorsList = FXCollections.observableArrayList();
        tableViewId.setItems(authorsList);

        checkId.selectedProperty().bindBidirectional(viewModel.confirmPropertyProperty());
        deleteBtn.disableProperty().bind(viewModel.confirmPropertyProperty().not());

        deleteBtn.addEventHandler(ActionEvent.ACTION, e ->{
            deleteAuthor();
        });
        loadDataAuthors();
    }

    private void loadDataAuthors()
    {
        List<Author> displayAuthors = authorDao.authorsGetAll();

        tableViewId.getItems().setAll(displayAuthors);
    }

    private void deleteAuthor() {
        Author author = tableViewId.getSelectionModel().getSelectedItem();
        authorDao.deleteAuthorById(author.getId());
        authorsList.remove(author);
        viewModel.setConfirmProperty(false);
    }
}

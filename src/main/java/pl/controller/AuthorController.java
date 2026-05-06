package pl.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.library.dao.AuthorDao;
import pl.library.model.Author;
import pl.view.ViewModel;

import java.util.List;

public class AuthorController {

    AuthorDao authorDao = new AuthorDao();
    ViewModel viewModel = new ViewModel();

    private String originalName;
    private String originalSurname;

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
    private Label idLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField surnameField;


    @FXML
    public void initialize() {
        autId.setCellValueFactory(new PropertyValueFactory<>("id"));
        autNamId.setCellValueFactory(new PropertyValueFactory<>("name"));
        autSurnId.setCellValueFactory(new PropertyValueFactory<>("surname"));
        authorsList = FXCollections.observableArrayList();
        tableViewId.setItems(authorsList);

        checkId.selectedProperty().bindBidirectional(viewModel.confirmPropertyProperty());
        deleteBtn.disableProperty().bind(viewModel.confirmPropertyProperty().not());
        nameField.textProperty().addListener((obs, old, val) -> updateSaveButton());
        surnameField.textProperty().addListener((obs, old, val) -> updateSaveButton());

        deleteBtn.addEventHandler(ActionEvent.ACTION, e ->{
            deleteAuthor();
        });
        loadDataAuthors();

        tableViewId.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) ->{

            if(selected == null){
                idLabel.setText("-");
                nameField.clear();
                surnameField.clear();
                originalName = null;
                originalSurname = null;
                saveBtn.setDisable(true);
                return;
            }

            idLabel.setText(String.valueOf(selected.getId()));
            nameField.setText(String.valueOf(selected.getName()));
            surnameField.setText(String.valueOf(selected.getSurname()));

            originalName = selected.getName();
            originalSurname = selected.getSurname();

            updateSaveButton();

        });



        saveBtn.addEventHandler(ActionEvent.ACTION, e -> {

            if (saveBtn.isDisabled()) return;

            saveBtn.setDisable(true);
            authorDao.updateAuthorByID(Integer.parseInt(idLabel.getText()), nameField.getText(), surnameField.getText());
            Author selected = tableViewId.getSelectionModel().getSelectedItem();

            if (selected != null) {
                selected.setName(nameField.getText());
                selected.setSurname(surnameField.getText());

                tableViewId.refresh();
            }

            idLabel.setText("-");
            nameField.clear();
            surnameField.clear();
            originalName = null;
            originalSurname = null;
            saveBtn.setDisable(true);

        });
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

    private void updateSaveButton() {

        if (originalName == null || originalSurname == null) {
            saveBtn.setDisable(true);
            return;
        }

        boolean changed = (!nameField.getText().equals(originalName) || !surnameField.getText().equals(originalSurname));

        saveBtn.setDisable(!changed);
    }
}

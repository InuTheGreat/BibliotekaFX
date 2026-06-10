package pl.controller.administrator.genre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.library.dao.GenreDao;
import pl.library.dao.PublisherDao;
import pl.library.model.Genre;
import pl.library.model.Publisher;
import pl.view.ViewModel;

import java.util.List;

public class GenreController {

    private final GenreDao genreDao = new GenreDao();
    private final ViewModel viewModel = new ViewModel();
    private ObservableList<Genre> genreList;
    private String originalName;

    @FXML
    private Button addGenreBtn;

    @FXML
    private CheckBox checkId;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Genre, Integer> genId;

    @FXML
    private TableColumn<Genre, Integer> genNameId;

    @FXML
    private Label idLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<Genre> tableViewId;

    @FXML
    public void initialize() {

        addGenreBtn.setDisable(true);
        saveBtn.setDisable(true);
        genNameId.setCellValueFactory(new PropertyValueFactory<>("name"));
        genId.setCellValueFactory(new PropertyValueFactory<>("id"));
        genreList = FXCollections.observableArrayList();
        tableViewId.setItems(genreList);

        checkId.selectedProperty().bindBidirectional(viewModel.confirmPropertyProperty());
        deleteBtn.disableProperty().bind(viewModel.confirmPropertyProperty().not());

        tableViewId.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) ->{

            if(selected == null) return;

            nameField.setText(String.valueOf(selected.getName()));
            idLabel.setText(String.valueOf(selected.getId()));

            originalName = nameField.getText();

            updateButton();
        });

        nameField.textProperty().addListener((obs, old, selected) -> updateButton());

        loadsData();

        saveBtn.setOnAction(e -> updatePublisher());

        deleteBtn.setOnAction(e -> deletePublisher());

        addGenreBtn.setOnAction(e -> addPublisher());
    }

    private void loadsData() {
        List<Genre> temp = genreDao.genresGetAll();
        tableViewId.getItems().setAll(temp);
    }

    private void updateButton() {

        if(tableViewId.getItems().isEmpty()) {
            addGenreBtn.setDisable(nameField.getText().trim().isEmpty());
            return;
        }

        if (originalName == null) {
            saveBtn.setDisable(true);
            addGenreBtn.setDisable(true);
            return;
        }

        boolean changed = (!nameField.getText().trim().equals(originalName.trim()));

        saveBtn.setDisable(!changed);
        addGenreBtn.setDisable(!changed);

    }

    private void addPublisher() {

        String name = String.valueOf(nameField.getText());

        genreDao.addGenre(name);

        loadsData();

        nameField.clear();

        tableViewId.getSelectionModel().clearSelection();

        originalName = null;

        updateButton();
    }

    private void updatePublisher() {
        Genre selected = tableViewId.getSelectionModel().getSelectedItem();

        if (saveBtn.isDisabled()) return;

        if(selected != null) {
            genreDao.updateGenreByID(Integer.parseInt(idLabel.getText()), nameField.getText());

            selected.setName(nameField.getText());

            tableViewId.refresh();
        }

        originalName = nameField.getText();
        updateButton();
    }

    private void deletePublisher()
    {
        Genre selected = tableViewId.getSelectionModel().getSelectedItem();
        if(selected != null) {
            genreDao.deleteGenre(selected.getId(), selected.getName());
            genreList.remove(selected);
            viewModel.setConfirmProperty(false);

            nameField.clear();
            idLabel.setText("-");
            originalName = null;
            tableViewId.getSelectionModel().clearSelection();
            updateButton();
        }
    }

}

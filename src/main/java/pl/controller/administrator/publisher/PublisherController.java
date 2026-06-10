package pl.controller.administrator.publisher;

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
import pl.library.dao.PublisherDao;
import pl.library.model.Publisher;
import pl.view.ViewModel;

import java.util.List;

public class PublisherController {

    private final PublisherDao publisherDao = new PublisherDao();
    private final ViewModel viewModel = new ViewModel();
    private ObservableList<Publisher> publisherList;
    private String originalName;

    @FXML
    private Button addPublisherBtn;

    @FXML
    private CheckBox checkId;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label idLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<Publisher, Integer> pubId;

    @FXML
    private TableColumn<Publisher, String> pubNameId;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<Publisher> tableViewId;

    @FXML
    public void initialize() {

        addPublisherBtn.setDisable(true);
        saveBtn.setDisable(true);
        pubNameId.setCellValueFactory(new PropertyValueFactory<>("name"));
        pubId.setCellValueFactory(new PropertyValueFactory<>("id"));
        publisherList = FXCollections.observableArrayList();
        tableViewId.setItems(publisherList);

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

        addPublisherBtn.setOnAction(e -> addPublisher());
    }

    private void loadsData() {
        List<Publisher> temp = publisherDao.publishersGetAll();
        tableViewId.getItems().setAll(temp);
    }

    private void updateButton() {

        if(tableViewId.getItems().isEmpty()) {
            addPublisherBtn.setDisable(nameField.getText().trim().isEmpty());
            return;
        }

        if (originalName == null) {
            saveBtn.setDisable(true);
            addPublisherBtn.setDisable(true);
            return;
        }

        boolean changed = (!nameField.getText().trim().equals(originalName.trim()));

        saveBtn.setDisable(!changed);
        addPublisherBtn.setDisable(!changed);
    }

    private void addPublisher() {

        String name = String.valueOf(nameField.getText());

        publisherDao.addPublisher(name);

        loadsData();

        nameField.clear();

        tableViewId.getSelectionModel().clearSelection();

        originalName = null;

        updateButton();
    }

    private void updatePublisher() {
        Publisher selected = tableViewId.getSelectionModel().getSelectedItem();

        if (saveBtn.isDisabled()) return;

        if(selected != null) {
            publisherDao.updatePublisherByID(Integer.parseInt(idLabel.getText()), nameField.getText());

            selected.setName(nameField.getText());

            tableViewId.refresh();
        }

        originalName = nameField.getText();
        updateButton();
    }

    private void deletePublisher()
    {
        Publisher selected = tableViewId.getSelectionModel().getSelectedItem();
        if(selected != null) {
            publisherDao.deletePublisher(selected.getId(), selected.getName());
            publisherList.remove(selected);
            viewModel.setConfirmProperty(false);

            nameField.clear();
            idLabel.setText("-");
            originalName = null;
            tableViewId.getSelectionModel().clearSelection();
            updateButton();
        }
    }
}

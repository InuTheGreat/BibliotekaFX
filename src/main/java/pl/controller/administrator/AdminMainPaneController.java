package pl.controller.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import pl.controller.MainController;

public class AdminMainPaneController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private Button authorBtnId;

    @FXML
    private Button publisherBtnId;

    @FXML
    private Button genreBtnId;

    @FXML
    private Button anotherBtnId;

    @FXML
    private GridPane gridPaneId;

    @FXML
    private Button bookBtnId;

    @FXML
    private Button borrowingBtnId;

    @FXML
    public void initialize() {
        authorBtnId.addEventHandler(ActionEvent.ACTION, e ->{
            mainController.loadViewCenter("/fxml/administrator/author/authors.fxml");
        });

        publisherBtnId.addEventHandler(ActionEvent.ACTION, e -> {
            mainController.loadViewCenter("/fxml/administrator/publisher/publishers.fxml");
        });

        genreBtnId.addEventHandler(ActionEvent.ACTION, e -> {
            mainController.loadViewCenter("/fxml/administrator/genre/genres.fxml");
        });

        bookBtnId.addEventHandler(ActionEvent.ACTION, e -> {
            mainController.loadViewCenter("/fxml/administrator/book/books.fxml");
        });

        borrowingBtnId.addEventHandler(ActionEvent.ACTION, e -> {
            mainController.loadViewCenter("/fxml/administrator/borrowings.fxml");
        });

        anotherBtnId.addEventHandler(ActionEvent.ACTION, e -> {
            mainController.loadViewCenter("/fxml/administrator/another.fxml");
        });
    }
}

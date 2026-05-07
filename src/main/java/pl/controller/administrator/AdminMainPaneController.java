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
    private GridPane gridPaneId;

    @FXML
    public void initialize() {
        authorBtnId.addEventHandler(ActionEvent.ACTION, e ->{
            mainController.loadViewCenter("/fxml/administrator/author/authors.fxml");
        });

        publisherBtnId.addEventHandler(ActionEvent.ACTION, e ->{
            mainController.loadViewCenter("/fxml/administrator/publisher/publishers.fxml");
        });
    }
}

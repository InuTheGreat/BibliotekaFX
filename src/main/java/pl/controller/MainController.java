package pl.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import pl.library.model.User;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane rootPane;

    private User currentUser;

    public void initUser(User user) {
        if(user != null) {
            this.currentUser = user;

            if (user.getRole().equals("ADMINISTRATOR")) {
                loadViewCenter("/fxml/adminDashboard.fxml");
            } else {
                loadViewCenter("/fxml/userDashboard.fxml");
            }

        } else {
            System.out.println("Pusta referencja");
        }
    }

    public void loadViewCenter(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent view = loader.load();

            Object controller = loader.getController();

            if(controller instanceof AdminMainPaneController){
                AdminMainPaneController adminMainPaneController = (AdminMainPaneController) controller;
                adminMainPaneController.setMainController(this);
            }


            BorderPane.setAlignment(view, javafx.geometry.Pos.CENTER);

            view.setStyle("-fx-max-width: Infinity; -fx-max-height: Infinity;");
            rootPane.setCenter(view);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

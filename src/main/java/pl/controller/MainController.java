package pl.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import pl.controller.administrator.AdminMainPaneController;
import pl.controller.pane.bottom.PaneBottomPaneController;
import pl.controller.pane.top.PaneTopController;
import pl.library.model.User;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane rootPane;

    private PaneBottomPaneController paneBottomPaneController;
    private PaneTopController paneTopController;
    private User currentUser;

    public void initUser(User user) {
        if(user != null) {
            this.currentUser = user;

            loadFooter("/fxml/pane/footer.fxml");
            paneBottomPaneController.setupFooter(currentUser);

            loadTop("/fxml/pane/top.fxml");
            paneTopController.setupMainController(this);
            paneTopController.setupUserFromController(currentUser);

            if (user.getRole().equals("ADMINISTRATOR")) {
                loadViewCenter("/fxml/administrator/adminDashboard.fxml");
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

    public void loadFooter(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
            Parent footer = loader.load();

            paneBottomPaneController = loader.getController();

            rootPane.setBottom(footer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTop(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
            Parent top = loader.load();
            paneTopController = loader.getController();

            rootPane.setTop(top);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToDashboard() {
        if(currentUser.getRole().equals("ADMINISTRATOR")) {
            loadViewCenter("/fxml/administrator/adminDashboard.fxml");
        } else {
            loadViewCenter("/fxml/userDashboard.fxml");
        }
    }
}

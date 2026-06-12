package pl.controller.pane.top;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.controller.MainController;
import pl.controller.pane.top.setup.SetupController;
import pl.library.model.User;
import pl.session.UserSession;

import java.io.IOException;

public class PaneTopController {

    private MainController mainController;
    private SetupController setupController;

    public void setupMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private Button logOutBtn;

    @FXML
    private Button setupBtn;

    @FXML
    private Label nameLabelId;

    @FXML
    private Button startBoardBtn;

    @FXML
    private Button tunOffBtn;

    @FXML
    public void initialize() {
        startBoardBtn.setOnAction(e -> mainController.goToDashboard());
        tunOffBtn.setOnAction(e -> Platform.exit());
        setupBtn.setOnAction(e ->{
            openPasswordWindow();
        });
        logOutBtn.setOnAction(e -> logout());

        nameLabelId.setText("Witaj, " + UserSession.getUser().getName());
    }

    private void openPasswordWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pane/setup/changePassword.fxml"));
            Parent root = loader.load();

            setupController = loader.getController();

            setupController.setupUser(UserSession.getUser());

            Stage stage = new Stage();
            stage.setTitle("Zmiana hasła");
            stage.setScene(new Scene(root));

            stage.setWidth(350);
            stage.setHeight(250);
            stage.setResizable(false);


            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logout() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/loginPane.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logOutBtn.getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setMaximized(false);
            stage.setWidth(265);
            stage.setHeight(360);
            stage.centerOnScreen();
            stage.setTitle("Login");

            UserSession.clear();
            mainController = null;
            setupController = null;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

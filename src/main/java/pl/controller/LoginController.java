package pl.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.library.model.User;
import pl.library.service.UserService;
import pl.session.UserSession;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;


public class LoginController {

    private UserService userService = new UserService();

    @FXML
    private Button loginButtonId;

    @FXML
    private TextField loginFieldId;

    @FXML
    private HBox loginId;

    @FXML
    private Button registerButtonId;

    @FXML
    private Pane paneId;

    @FXML
    private PasswordField passwordFieldId;

    @FXML
    public void initialize() {
        loginButtonId.addEventHandler(ActionEvent.ACTION, e ->{
            loginUser();
        });

        registerButtonId.addEventHandler(ActionEvent.ACTION, e -> {
            openRegisterWindow();
        });
    }
    public void loginUser() {

        User user = userService.login(loginFieldId.getText().trim(), passwordFieldId.getText().trim());

        if(user != null)
        {
            openMain(user);
        } else {
            System.out.println("Błąd logowania");

            popWindow("Błąd logowania");

        }


    }

    public void openMain(User user) {
        try {

            UserSession.setUser(user);

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            MainController mainController = loader.getController();

            mainController.initUser();

            Stage stage = (Stage) loginFieldId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.setTitle("Library v3.2.8");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openRegisterWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/pane/setup/register.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Register account");

            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void popWindow(String error) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirm action");

        alert.setContentText(error);
        Optional<ButtonType> result = alert.showAndWait();
//
    }

    private void close() {
        Stage stage = (Stage) loginFieldId.getScene().getWindow();
        stage.close();
    }

}

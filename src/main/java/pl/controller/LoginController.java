package pl.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.library.model.User;
import pl.library.service.UserService;

import java.io.IOException;

public class LoginController {

    private UserService userService = new UserService();

    @FXML
    private Button loginButtonId;

    @FXML
    private TextField loginFieldId;

    @FXML
    private HBox loginId;

    @FXML
    private Pane paneId;

    @FXML
    private TextField passwordFieldId;

    @FXML
    public void initialize() {
        loginButtonId.addEventHandler(ActionEvent.ACTION, e ->{
            loginUser();
        });
    }
    public void loginUser() {

        User user = userService.login(loginFieldId.getText().trim(), passwordFieldId.getText().trim());

        if(user != null)
        {
            openMain(user);
        } else {
            System.out.println("Błąd logowania");
        }

    }

    public void openMain(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            MainController mainController = loader.getController();

            mainController.initUser(user);

            Stage stage = (Stage) loginFieldId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.setTitle("Library v0.1.1");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

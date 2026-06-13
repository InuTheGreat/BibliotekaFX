package pl.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.library.dto.UserView;
import pl.library.service.UserService;

public class RegisterController {

    private final UserService userService = new UserService();

    @FXML private TextField firstNameField;

    @FXML private TextField lastNameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    public void initialize() {
        registerButton.setOnAction(e -> register());
    }

    private void register() {

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String login = loginField.getText().trim();
        String pass = passwordField.getText().trim();
        String repeat = repeatPasswordField.getText().trim();

        if(firstName == null || firstName.isBlank() ||
                lastName == null || lastName.isBlank() ||
                login == null || login.isBlank() ||
                pass == null || pass.isBlank()) {

            System.out.println("Uzupełnij wszystkie pola");
            return;
        }

        if (!pass.equals(repeat)) {
            System.out.println("Hasła się nie zgadzają");
            return;
        }

        if(userService.isUserExistByEmail(login)) {

            System.out.println("Błąd utworzenia konta");
            return;
        }

        UserView newUser = userService.createUser(pass, login, firstName, lastName);

        if(newUser == null) {

            System.out.println("Błąd utworzenia konta");
            return;
        }

        System.out.println("Konto utworzone");
        close();
    }

    private void close() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
package pl.controller.pane.top.setup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import pl.library.model.User;
import pl.library.service.password.UserPassword;

public class SetupController {

    private final UserPassword userPassword = new UserPassword();
    private User currentUser;

    public void setupUser(User user) {
        this.currentUser = user;
    }


    @FXML
    private Button cancelBtn;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Button saveBtn;

    @FXML
    public void initialize() {
        cancelBtn.setOnAction(e -> {
            Stage stage = (Stage) newPasswordField.getScene().getWindow();
            stage.close();
        });

        saveBtn.setOnAction(e -> changePassword());
    }

    private void changePassword() {
        String pass1 = newPasswordField.getText();
        String pass2 = repeatPasswordField.getText();

        if(pass1 == null || pass2 == null) {
            System.out.println("Błąd ogólny");
            return;
        }

        if(pass1.isEmpty() || pass2.isEmpty()) {
            System.out.println("Field jest pusty");
            return;
        }

        if(!(pass1.trim().equals(pass2.trim()))) {
            System.out.println("Hasła nie są takie same");
            return;
        }
        Stage stage = (Stage) newPasswordField.getScene().getWindow();

        try {
            if (userPassword.isValid(currentUser, pass1)) {
                userPassword.changePassword(currentUser, pass1);
                stage.close();
            } else {
                System.out.println("Błąd podczas zmiany hasła");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

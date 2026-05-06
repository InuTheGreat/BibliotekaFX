package pl.controller.administrator.author;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.library.dao.AuthorDao;

public class AuthorAddController {

    private final AuthorDao authorDao = new AuthorDao();

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    public void initialize() {
        confirmBtn.setOnAction(e -> addAuthor());
        cancelBtn.setOnAction(e -> {
            Stage stage = (Stage) surnameField.getScene().getWindow();
            stage.close();
        });
    }

    private void addAuthor() {
        String name = nameField.getText();
        String surname = surnameField.getText();

        Stage stage = (Stage) surnameField.getScene().getWindow();

        if(name == null || surname == null) return;

        try {
            authorDao.addAuthor(name, surname);
            System.out.println("Wykonano pomyślnie!");
        } catch (Exception e) {
            e.printStackTrace();
        } {
            stage.close();
        }
    }

}
package pl.controller.bottom;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.library.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaneBottomPaneController {


    @FXML
    private Label dateLabel;

    @FXML
    private Label dbLabel;

    @FXML
    private Label loginLabel;

    public void setupFooter(User user) {
        loginLabel.setText("Zalogowany jako: " + user.getRole());
        dateLabel.setText("Data zalogowania: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        dbLabel.setText("Baza: MySQL");
    }
}

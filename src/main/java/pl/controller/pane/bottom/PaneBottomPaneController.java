package pl.controller.pane.bottom;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import pl.library.model.User;

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
        dbLabel.setText("Baza: MySQL");
        startClock();
    }

    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            dateLabel.setText(LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
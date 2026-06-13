package pl.controller.administrator.another;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.controller.administrator.another.animated.AnimateCounterLabel;
import pl.library.service.StatisticService;

public class AnotherController {

    private final StatisticService statisticService = new StatisticService();
    private final AnimateCounterLabel animateCounterLabel = new AnimateCounterLabel();

    @FXML
    private Label usersCountLabel;

    @FXML
    private Label rentalsCountLabel;

    @FXML
    public void initialize() {

//        int usersCount = statisticService.getAllUserCount();
//        int rentalsCount = statisticService.getAllBorrowCount();
//
//        usersCountLabel.setText(String.valueOf(usersCount));
//        rentalsCountLabel.setText(String.valueOf(rentalsCount));

        animateCounterLabel.animateCounter(usersCountLabel, statisticService.getAllUserCount());
        animateCounterLabel.animateCounter(rentalsCountLabel, statisticService.getAllBorrowCount());
    }
}
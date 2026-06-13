package pl.controller.administrator.another;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import pl.controller.administrator.another.animated.AnimateCounterLabel;
import pl.library.dao.help.MonthView;
import pl.library.service.StatisticService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnotherController {

    private final StatisticService statisticService = new StatisticService();
    private final AnimateCounterLabel animateCounterLabel = new AnimateCounterLabel();

    @FXML
    private Label usersCountLabel;

    @FXML
    private Label rentalsCountLabel;

    @FXML
    private LineChart<String, Number> rentalsChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {

        animateCounterLabel.animateCounter(usersCountLabel, statisticService.getAllUserCount());
        animateCounterLabel.animateCounter(rentalsCountLabel, statisticService.getAllBorrowCount());

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(String.valueOf(LocalDate.now().getYear()));

        for(MonthView monthView : statisticService.getMonthResumeBorrow()) {

            String monthName = switch (monthView.getMonth()) {

                case 1 -> "Sty";
                case 2 -> "Lut";
                case 3 -> "Mar";
                case 4 -> "Kwi";
                case 5 -> "Maj";
                case 6 -> "Cze";
                case 7 -> "Lip";
                case 8 -> "Sie";
                case 9 -> "Wrz";
                case 10 -> "Paź";
                case 11 -> "Lis";
                case 12 -> "Gru";
                default -> "";
            };

            series.getData().add(new XYChart.Data<>(monthName, monthView.getTotal()));
        }
        rentalsChart.getData().add(series);
    }
}
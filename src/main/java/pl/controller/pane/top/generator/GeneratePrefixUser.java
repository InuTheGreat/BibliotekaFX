package pl.controller.pane.top.generator;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class GeneratePrefixUser {

    private static final List<String> data = List.of("Witaj, ",
            "Miło Cię widzieć, ",
            "Dzień dobry, ",
            "Cześć, ",
            "Witaj ponownie, ",
            "Fajnie, że jesteś, ",
            "Miłego dnia, ",
            "Zalogowano: "

    );

    private static final List<String> evening = List.of(
            "Dobry wieczór, ",
            "Witaj wieczorem, ",
            "Miłego wieczoru, ",
            "Cześć, "
    );

    private static final List<String> morning = List.of(
            "Dzień dobry, ",
            "Dobrego poranka, ",
            "Miłego poranka, ",
            "Witaj o poranku, "
    );


    private final static Random random = new Random();

    public static String generatePrefixForTopPanel() {

        int hour = LocalTime.now().getHour();

        if (hour >= 4 && hour <= 11) {
            return morning.get(random.nextInt(morning.size()));
        }

        if (hour >= 12 && hour <= 17) {
            return data.get(random.nextInt(data.size()));
        }

        return evening.get(random.nextInt(evening.size()));
    }
}

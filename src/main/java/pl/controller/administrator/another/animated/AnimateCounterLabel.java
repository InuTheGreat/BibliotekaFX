package pl.controller.administrator.another.animated;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class AnimateCounterLabel {


    public void animateCounter(Label label, int target) {

        IntegerProperty value = new SimpleIntegerProperty(0);

        value.addListener((obs, oldVal, newVal) ->
                label.setText(newVal.toString()));

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0.5),
                        new KeyValue(value, target, Interpolator.EASE_BOTH)
                )
        );

        timeline.play();
    }
}

package pl.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ViewModel {

    BooleanProperty confirmProperty = new SimpleBooleanProperty(false);

    public BooleanProperty confirmPropertyProperty() {
        return confirmProperty;
    }

    public void setConfirmProperty(boolean confirmProperty) {
        this.confirmProperty.set(confirmProperty);
    }
}

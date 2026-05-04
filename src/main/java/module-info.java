module biblioteka.projekt {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    exports pl;
    exports pl.library;
    opens pl.controller to javafx.fxml;
    opens pl.library.model to javafx.base;
}
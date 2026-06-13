module biblioteka.projekt {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    //requires biblioteka.projekt;
    requires javafx.base;
    requires javafx.graphics;
    requires jbcrypt;
    //requires biblioteka.projekt;
    //requires biblioteka.projekt;

    exports pl;
    exports pl.library;
    opens pl.controller to javafx.fxml;
    opens pl.library.model to javafx.base;
    opens pl.controller.pane.bottom to javafx.fxml;
    opens pl.controller.administrator.author to javafx.fxml;
    opens pl.controller.administrator to javafx.fxml;
    opens pl.controller.pane.top to javafx.fxml;
    opens pl.controller.pane.top.setup to javafx.fxml;
    opens pl.controller.administrator.publisher to javafx.fxml;
    opens pl.controller.administrator.genre to javafx.fxml;
    opens pl.controller.administrator.book to javafx.fxml;
    opens pl.library.dto to javafx.base;
}
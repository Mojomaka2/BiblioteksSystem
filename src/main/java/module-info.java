module com.javagrupp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens com.javagrupp to javafx.fxml;
    exports com.javagrupp;
}

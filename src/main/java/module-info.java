module com.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.json;
    opens com.application to javafx.fxml;
    opens com.modulo_aluno to javafx.fxml;
    opens com.models to javafx.base;
    opens com.modulo_professor to javafx.fxml;
    exports com.application;
}

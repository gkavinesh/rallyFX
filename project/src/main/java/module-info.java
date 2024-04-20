module com.mycompany.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.mycompany.project to javafx.fxml;
    exports com.mycompany.project;
}

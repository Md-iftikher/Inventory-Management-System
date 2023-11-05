module com.example.demologin_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires pdfbox.app;

    opens com.example.project to javafx.fxml;
    exports com.example.project;

}
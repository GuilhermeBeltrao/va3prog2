module com.example.va3prog2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.va3prog2 to javafx.fxml;
    exports com.example.va3prog2;
    exports com.example.va3prog2.controllers;
}

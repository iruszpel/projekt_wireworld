module projekt.wireworld {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires javafx.swing;
    opens GUI to javafx.graphics;
    opens GUI.simulationPlayer to javafx.graphics;

}
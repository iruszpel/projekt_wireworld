module projekt.wireworld {
    requires javafx.fxml;
    requires javafx.controls;
    opens GUI to javafx.graphics;
    opens GUI.simulationPlayer to javafx.graphics;

}
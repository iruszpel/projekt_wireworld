package GUI;

import Cell.Cell;
import WireWorld.ReadFromFile;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import WireWorld.Map;

public class Main extends Application {

    public static int h = Map.height;
    public static int w = Map.width;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wireworld simulator");
        primaryStage.show();


        Canvas simCanvas = (Canvas) scene.lookup("#simcanvas");
        MapDrawer canvasDrawer = new MapDrawer(simCanvas);

        //Default map
        ReadFromFile.read(getClass().getResource("/WireWorld/test.txt").getPath());

        canvasDrawer.drawEdges();
        canvasDrawer.drawMap(Map.maps.size()-1);

        SettingsController settingsController = new SettingsController(scene);
        settingsController.enableListeners();
        IOButtonsController ioController = new IOButtonsController(scene, primaryStage, canvasDrawer);
        ioController.enableListeners();

        SimulationStateController simStateController = new SimulationStateController(scene);
        simStateController.enableListeners();




    }


    public static void main(String[] args) {
        launch(args);
    }
}

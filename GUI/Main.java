package GUI;

import Cell.Cell;
import WireWorld.Iteration;
import WireWorld.ReadFromFile;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import WireWorld.Map;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public static int h = Map.height;
    public static int w = Map.width;
    public static int currentIteration = 0;
    public static int howManyIterations = 30;
    public static int animationSpeed = 4;
    public static MapDrawer canvasDrawer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wireworld simulator");
        primaryStage.show();


        Canvas simCanvas = (Canvas) scene.lookup("#simCanvas");
        canvasDrawer = new MapDrawer(simCanvas);

        //Default map
        ReadFromFile.read(getClass().getResource("/WireWorld/test.txt").getPath());

        generateIterations(howManyIterations);

        canvasDrawer.drawEdges();
        canvasDrawer.drawMap(currentIteration);


        SettingsController settingsController = new SettingsController(scene);
        settingsController.enableListeners();
        IOButtonsController ioController = new IOButtonsController(scene, primaryStage);
        ioController.enableListeners();

        AddButtonsController addButtonsController = new AddButtonsController(scene);
        addButtonsController.enableListeners();

        SimulationStateController simStateController = new SimulationStateController(scene);
        simStateController.enableListeners();




    }

    public static void generateIterations(int n) {
        for(int i=0; i< n; i++){
            Iteration.iterate();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

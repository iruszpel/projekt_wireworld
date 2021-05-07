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


        ReadFromFile.read("C:/Users/jakub/Desktop/sem2/jimp2/proj_II/proj_WW/src/WireWorld/test.txt");
        //daj sobie swoją ścieżkę do pliku w module WireWorld

        canvasDrawer.drawEdges();

        canvasDrawer.drawMap(0);


    }


    public static void main(String[] args) {
        launch(args);
    }
}

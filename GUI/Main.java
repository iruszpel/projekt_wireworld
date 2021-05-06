package GUI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wireworld symulator");
        primaryStage.show();


        Canvas simCanvas = (Canvas) scene.lookup("#simcanvas");
        MapDrawer canvasDrawer = new MapDrawer(simCanvas);
        canvasDrawer.drawEdges();
        canvasDrawer.drawAtXY(0,1, Color.RED);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

package GUI;

import GUI.simulationPlayer.Player;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SimulationStateController implements GUIController {
    private Scene scene;
    protected static Player simPlayer;

    public SimulationStateController(Scene scene) {
        this.scene = scene;

    }
    @Override
    public void enableListeners() {

        Slider iterSlider = (Slider) scene.lookup("#iterSlider");
        iterSlider.setMax(Main.howManyIterations);
        iterSlider.setMajorTickUnit(Main.howManyIterations/5);
        iterSlider.setOnMouseReleased(event -> {
            simPlayer.clickSlider(iterSlider);
        });

        ImageView playPauseButton = (ImageView) scene.lookup("#playPauseButton");
        simPlayer = new Player(playPauseButton,iterSlider);
        playPauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            simPlayer.clickPlay();
        });

        ImageView nextButton = (ImageView) scene.lookup("#nextButton");
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            simPlayer.clickNext();
            event.consume();
        });

        ImageView previousButton = (ImageView) scene.lookup("#previousButton");
        previousButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            simPlayer.clickPrevious();
            event.consume();
        });





    }


}

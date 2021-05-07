package GUI;

import GUI.simulationPlayer.Player;
import GUI.simulationPlayer.SimulationState;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SimulationStateController implements GUIController {
    private Scene scene;

    public SimulationStateController(Scene scene) {
        this.scene = scene;
    }
    @Override
    public void enableListeners() {

        ImageView playPauseButton = (ImageView) scene.lookup("#playpausebutton");
        Player simPlayer = new Player(playPauseButton);
        playPauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            simPlayer.clickPlay();
        });
    }


}

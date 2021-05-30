package GUI.simulationPlayer;

import GUI.Main;
import WireWorld.Map;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;

public abstract class SimulationState {
    protected Player player;
    public SimulationState(Player player){
        this.player = player;
    }
    public abstract void play();

    public void nextIteration() {
        if(Main.currentIteration < Map.iteration){
            Main.currentIteration++;
            Main.canvasDrawer.drawIteration(Main.currentIteration);
            player.iterSlider.setValue(Main.currentIteration);
        }

    }


    public void previousIteration() {
        if(Main.currentIteration > 0) {
            Main.currentIteration--;
            Main.canvasDrawer.drawIteration(Main.currentIteration);
            player.iterSlider.setValue(Main.currentIteration);
        }
    }

    public void sliderChange(Slider iterSlider){
        Main.currentIteration = (int)iterSlider.getValue();
        Main.canvasDrawer.drawIteration(Main.currentIteration);
    }

    protected void changeToPauseIcon() {
        Image playImage = new Image(getClass().getResource("/GUI/resources/outline_pause_circle_black_24dp.png").toExternalForm());
        player.playButton.setImage(playImage);
    }

    protected void changeToPlayIcon() {
        Image playImage = new Image(getClass().getResource("/GUI/resources/outline_play_circle_black_24dp.png").toExternalForm());
        player.playButton.setImage(playImage);
    }

}

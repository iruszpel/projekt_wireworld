package GUI.simulationPlayer;

import GUI.Main;
import GUI.MapDrawer;
import WireWorld.Map;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

//Wzorzec projektowy Stan - pomijanie krótkiego switch case milionem klas które trudniej zrozumieć
public class Player {
    private SimulationState state;
    protected ImageView playButton;
    protected Timer t;
    protected Slider iterSlider;
    public Player(ImageView playButton, Slider iterSlider) {
        this.playButton = playButton;
        this.iterSlider = iterSlider;
        this.state = new PausedState(this); //Default state is paused
        this.t = new Timer();
    }
    public void changeState(SimulationState state) {
        this.state = state;
    }
    public void clickPlay(){
        state.play();
    }
    public void clickNext(){
        state.nextIteration();
    }
    public void clickPrevious(){
        state.previousIteration();
    }

}
class PlayingState extends SimulationState {
    public PlayingState(Player player) {
        super(player);
    }

    @Override
    public void play() {
        player.t.cancel();
        player.changeState(new PausedState(player));
        this.changeIcon();
    }
    private void changeIcon() {
        Image playImage = new Image(getClass().getResource("/GUI/resources/outline_play_circle_black_24dp.png").toExternalForm());
        player.playButton.setImage(playImage);
    }
}
class PausedState extends SimulationState {

    public PausedState(Player player) {
        super(player);
    }

    @Override
    public void play() {
        player.t = new Timer();
        player.t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(Main.currentIteration == Main.howManyIterations-1)
                    Main.currentIteration = 0;
                player.clickNext();
            }
        }, 0, 1000/Main.animationSpeed);

        player.changeState(new PlayingState(player));
        this.changeIcon();


    }



    private void changeIcon() {
        Image playImage = new Image(getClass().getResource("/GUI/resources/outline_pause_circle_black_24dp.png").toExternalForm());
        player.playButton.setImage(playImage);
    }
}


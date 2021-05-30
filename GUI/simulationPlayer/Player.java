package GUI.simulationPlayer;

import GUI.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


//Wzorzec projektowy Stan - pomijanie krótkiego switch case milionem klas które trudniej zrozumieć
public class Player {
    private SimulationState state;
    protected ImageView playButton;
    protected Slider iterSlider;
    protected Timeline animLoop;
    public Player(ImageView playButton, Slider iterSlider) {
        this.playButton = playButton;
        this.iterSlider = iterSlider;
        this.state = new PausedState(this); //Default state is paused
        this.animLoop = new Timeline();
        this.animLoop.setCycleCount( Timeline.INDEFINITE );
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
        player.animLoop.stop();
        player.animLoop.getKeyFrames().remove(0);
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

        KeyFrame kf = new KeyFrame(
                Duration.seconds(1/(double)Main.animationSpeed),
                ae -> {
                    if(Main.currentIteration == Main.howManyIterations)
                        Main.currentIteration = -1;
                    player.clickNext();
                });

        player.animLoop.getKeyFrames().add( kf );
        player.animLoop.play();

        player.changeState(new PlayingState(player));
        this.changeIcon();


    }



    private void changeIcon() {
        Image playImage = new Image(getClass().getResource("/GUI/resources/outline_pause_circle_black_24dp.png").toExternalForm());
        player.playButton.setImage(playImage);
    }
}


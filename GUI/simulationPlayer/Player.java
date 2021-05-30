package GUI.simulationPlayer;

import GUI.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
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
    public void clickSlider(Slider iterSlider) {state.sliderChange(iterSlider);};

    public void lock(){
        animLoop.stop();
        this.state.changeToPlayIcon();
        this.state = new LockedState(this);
    };
    public void unlock(){
        this.state = new PausedState(this);
    };


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
        this.changeToPlayIcon();
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
        this.changeToPauseIcon();


    }

}
class LockedState extends SimulationState {
    public LockedState(Player player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("W trybie edycji zablokowane jest używanie animacji!");
    }
    @Override
    public void nextIteration(){
        System.out.println("W trybie edycji zablokowane jest używanie animacji!");
    }
    @Override
    public void previousIteration(){
        System.out.println("W trybie edycji zablokowane jest używanie animacji!");
    }
    @Override
    public void sliderChange(Slider iterSlider){
        iterSlider.setValue(0);
        System.out.println("W trybie edycji zablokowane jest używanie animacji!");
    }
}


package GUI.simulationPlayer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Wzorzec projektowy Stan - pomijanie krótkiego switch case milionem klas które trudniej zrozumieć
public class Player {
    private SimulationState state;
    protected ImageView playButton;
    public Player(ImageView playButton) {
        this.playButton = playButton;
        this.state = new PausedState(this); //Default state is paused
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
        player.changeState(new PausedState(player));
        this.changeIcon();
    }
    @Override
    public void nextIteration(){

    }

    @Override
    public void previousIteration() {

    }
    private void changeIcon() {
        Image playImage = new Image(getClass().getResource("/GUI/resources/outline_pause_circle_black_24dp.png").toExternalForm());
        player.playButton.setImage(playImage);
    }
}
class PausedState extends SimulationState {

    public PausedState(Player player) {
        super(player);
    }

    @Override
    public void play() {
        player.changeState(new PlayingState(player));
        this.changeIcon();
    }

    @Override
    public void nextIteration() {

    }

    @Override
    public void previousIteration() {

    }

    private void changeIcon() {
        Image playImage = new Image(getClass().getResource("/GUI/resources/outline_play_circle_black_24dp.png").toExternalForm());
        player.playButton.setImage(playImage);
    }
}


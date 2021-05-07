package GUI.simulationPlayer;

public abstract class SimulationState {
    protected Player player;
    public SimulationState(Player player){
        this.player = player;
    }
    public abstract void play();
    public abstract void nextIteration();
    public abstract void previousIteration();



}

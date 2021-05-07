package GUI.simulationPlayer;

import GUI.Main;
import WireWorld.Map;

public abstract class SimulationState {
    protected Player player;
    public SimulationState(Player player){
        this.player = player;
    }
    public abstract void play();

    public void nextIteration() {
        if(Main.currentIteration < Map.iteration){
            Main.canvasDrawer.clearMap();
            Main.canvasDrawer.drawEdges();
            Main.currentIteration++;
            Main.canvasDrawer.drawMap(Main.currentIteration);
            player.iterSlider.setValue(Main.currentIteration);
        }

    }


    public void previousIteration() {
        if(Main.currentIteration > 0) {
            Main.canvasDrawer.clearMap();
            Main.canvasDrawer.drawEdges();
            Main.currentIteration--;
            Main.canvasDrawer.drawMap(Main.currentIteration);
            player.iterSlider.setValue(Main.currentIteration);
        }
    }



}

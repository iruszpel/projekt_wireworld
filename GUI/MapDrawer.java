package GUI;

import Cell.Cell;
import WireWorld.Map;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;


public class MapDrawer {
    private GraphicsContext gc;
    private Canvas canvas;

    private int computedHeight;
    private int computedWidth;

    private int offsetX;
    private int offsetY;

    private int boxSize;
    private int lineWidth;

    private int width;
    private int height;

    private int c = Main.w;
    private int r = Main.h;

    public static Stack<ArrayList<Map>> historyStack = new Stack<>();

    public static HashMap<Integer, Color> colorMap = new HashMap<>();

    public MapDrawer(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        this.canvas = canvas;

        updateInternalValues();

        colorMap.put(0, Color.rgb(0,0,0, 0.1));
        colorMap.put(1, Color.rgb(255, 215, 0));
        colorMap.put(2, Color.RED);
        colorMap.put(3, Color.BLUE);


    }
    private void updateInternalValues(){
        c = Main.w;
        r = Main.h;

        width = (int) canvas.getWidth();
        height = (int) canvas.getHeight();

        lineWidth = Math.min(2+(10/r)*2,2+(10/c)*2); //To durne ale zapewnia parzystą grubość linii, która jest potrzebna żeby kwadraty były faktycznie kwadratami
        boxSize = ((double) c/(double)r) < ((double) width/(double)height) ?  (height-3*lineWidth)/r : (width-3*lineWidth)/ c;

        computedHeight = boxSize*r;
        computedWidth = boxSize*c;

        offsetX= (width-computedWidth)/2+lineWidth;
        offsetY= (height-computedHeight)/2+lineWidth;

    }
    private void drawEdges( ) {

        gc.setLineWidth(lineWidth);
        gc.setStroke(Color.WHITE);

        //Horizontal lines
        for (int i=0;i<r+1; i++) {
            gc.strokeLine(offsetX, i*boxSize+offsetY, computedWidth+offsetX, i*boxSize+offsetY);
        }
        //Vertical lines
        for (int i=0;i<c+1; i++) {
            gc.strokeLine(i*boxSize+offsetX, offsetY, i*boxSize+offsetX, computedHeight+offsetY);
        }



    }
    public void drawAtXY(int x, int y, Color color) {
        gc.setFill(color);
        gc.fillRect(boxSize*x+offsetX+lineWidth/2,boxSize*y+offsetY+lineWidth/2,boxSize-lineWidth,boxSize-lineWidth);

    }
    public Pair<Integer, Integer> getCoordsFromXY(int x, int y) {
        int nx = (x-offsetX-lineWidth/2)/boxSize;
        int ny = (y-offsetY-lineWidth/2)/boxSize;

        return new Pair<>(nx, ny); //Te pary z javafx są słabe bo to jest key-value, ale jakoś działają
    }
    public void getColorFromXY(int x, int y) {

    }
    //Undo i redo do stanu mapy
    public void saveMapState() {
        ArrayList<Map> historyList = (ArrayList<Map>) Map.maps.clone(); //Make a copy of map list
        historyStack.push(historyList);
    }
    public void restoreMapState() {
        Map.maps = (ArrayList<Map>) historyStack.pop().clone();
        drawIteration(0);

    }
    public void removeMapStates(){
        historyStack.clear();
    }
    public void saveMapToImage(File file){
        WritableImage writableImage = new WritableImage(width, height);
        canvas.snapshot(null, writableImage);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void clearMap() {
        gc.clearRect(0, 0, width, height);
    }
    public void drawIteration(int iteration){
        updateInternalValues();
        clearMap();
        drawEdges();
        drawMap(iteration);

    }
    private void drawMap(int iteration){
        for(int y = 0; y < Map.height; y++){
            for(int x = 0; x < Map.width; x++){
                int c = Map.maps.get(iteration).getCell(y, x).getState();
                drawAtXY(x,y, colorMap.get(c));
            }
        }
    }
}

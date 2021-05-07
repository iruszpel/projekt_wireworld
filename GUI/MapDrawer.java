package GUI;

import Cell.Cell;
import WireWorld.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapDrawer {
    private GraphicsContext gc;
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



    public MapDrawer(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();

        width = (int) canvas.getWidth();
        height = (int) canvas.getHeight();

        lineWidth = Math.min(2+(10/r)*2,2+(10/c)*2); //To durne ale zapewnia parzystą grubość linii, która jest potrzebna żeby kwadraty były faktycznie kwadratami

        System.out.println(lineWidth);

        boxSize = ((double) c/(double)r) < ((double) width/(double)height) ?  (height-3*lineWidth)/r : (width-3*lineWidth)/ c;


        computedHeight = boxSize*r;
        computedWidth = boxSize*c;

        offsetX= (width-computedWidth)/2+lineWidth;
        offsetY= (height-computedHeight)/2+lineWidth;


    }
    public void drawEdges( ) {



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
    public void clearMap() {
        gc.clearRect(0, 0, width, height);
    }
    public void drawMap(int iteration){
        for(int y = 0; y < Map.height; y++){
            for(int x = 0; x < Map.width; x++){
                int c = Map.maps.get(iteration).getCell(y, x).getState();
                switch(c){
                    case 0: {
                        drawAtXY(x,y, Color.rgb(0,0,0, 0.1)); //transparentny kolor
                        break;
                    }
                    case 1: {
                        drawAtXY(x,y, Color.YELLOW);
                        break;
                    }
                    case 2: {
                        drawAtXY(x,y, Color.BLUE);
                        break;
                    }
                    case 3: {
                        drawAtXY(x,y, Color.RED);
                        break;
                    }


                }
            }
        }
    }
}

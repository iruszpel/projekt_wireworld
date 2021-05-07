package GUI;

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

    private int c = 40;
    private int r = 32;



    public MapDrawer(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();

        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();

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
        gc.fillRect(boxSize*x+offsetX+Math.ceil(lineWidth/2),boxSize*y+offsetY+Math.ceil(lineWidth/2),boxSize-lineWidth,boxSize-lineWidth);

    }
}

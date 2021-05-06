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

    private int c = 4;
    private int r = 3;



    public MapDrawer(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();

        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();

        lineWidth = 2+(10/r)*2; //To durne ale zapewnia parzystą grubość linii, która jest potrzebna żeby kwadraty były faktycznie kwadratami

        System.out.println(lineWidth);

        boxSize = c > r ?  ((height/c) - lineWidth) : ((height / r) - lineWidth);


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
}

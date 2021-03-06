package WireWorld;

import Cell.Cell;
import Cell.Empty;
import Cell.ElecHead;
import Cell.ElecTail;
import Cell.Conductor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    public Cell map[][];
    public static int height = 40;
    public static int width = 48;

    public static int iteration = -1;

    public static ArrayList<Map> maps = new ArrayList<Map>();

    public Map(int h, int w){
        this.height = h;
        this.width = w;
        Map.iteration++;
        Map.maps.add(this);
        map = new Cell[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++){
                map[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int y, int x){
        return map[y][x];
    }

    public void setCell(int y, int x, int state){
        Cell cell;
        switch (state) {
            case 0: {
                cell = new Empty(y, x);
                break;
            }
            case 1: {
                cell = new Conductor(y, x);
                break;
            }
            case 2: {
                cell = new ElecTail(y, x);
                break;
            }
            case 3: {
                cell = new ElecHead(y, x);
                break;
            }
            default: {
                cell = new Cell(y, x);
                break;
            }
        }
        map[y][x] = cell;
    }
    public Cell[][] deepCopyMap(){
        Cell[][] nv = new Cell[height][width];
        for (int i = 0; i < nv.length; i++)
            nv[i] = Arrays.copyOf(map[i], map[i].length);
        return nv;
    }
    //liczenie sąsiednich głów elektronów
    public int countNeighbours(Cell c){
        int count = 0;
        int x = c.getX();
        int y = c.getY();
        for(int i = x-1; i < x+2; i++) {
            for( int j = y-1; j < y+2; j++){
                if (i >= 0 && i < Map.width && j < Map.height && j >= 0){
                    if(!(i == x && j == y)){
                        if(getCell(j, i).getState() == 3){
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}

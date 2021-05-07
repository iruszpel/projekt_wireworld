package WireWorld;

import Cell.Cell;
import Cell.Empty;
import Cell.ElecHead;
import Cell.ElecTail;
import Cell.Conductor;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Map {
    public Cell map[][];
    public static int height = 20;
    public static int width = 24;

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

    public int countNeighbours(){
        int count = 0;
        // napisać logikę liczącą sąsiadów
        return count;
    }
    public static void main(String[] args) throws FileNotFoundException {
        int h = 10;
        int w = 10;

        ReadFromFile.read("/Users/jakub/Desktop/test.txt");


        Map mapa0 = Map.maps.get(0);

        for (int y = 0; y < h; y++){
            for( int x =0; x < w; x++){
                System.out.print(mapa0.map[y][x].getState() + " ");
            }
            System.out.println();
        }



    }
}

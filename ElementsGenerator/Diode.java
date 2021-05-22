package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Diode extends ElementsGenerator {
    static int[][] DiodeRightModel = {
            {0, 0, 0, 1, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 0, 0}};
    static int[][] DiodeLeftModel = {
            {0, 0, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 1, 0, 0, 0}};
    static int[][] DiodeUpModel = {
            {0, 1, 0},
            {0, 1, 0},
            {1, 1, 1},
            {1, 0, 1},
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0}};
    static int[][] DiodeDownModel = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0},
            {1, 0, 1},
            {1, 1, 1},
            {0, 1, 0},
            {0, 1, 0}};

    public void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY() - 1; //ustawienie punktu początkowego na komórkę[0][0] modelu

        switch(facing){
            case "up":{
                if(ElementsGenerator.gonnaFit(DiodeUpModel, y, x)){
                    generateModel(DiodeUpModel, y, x);
                }
                break;
            }
            case "down":{
                if(ElementsGenerator.gonnaFit(DiodeDownModel, y, x)){
                    generateModel(DiodeDownModel, y, x);
                }
                break;
            }
            case "right":{
                if(ElementsGenerator.gonnaFit(DiodeRightModel, y, x)){
                    generateModel(DiodeRightModel, y, x);
                }
                break;
            }
            case "left":{
                if(ElementsGenerator.gonnaFit(DiodeLeftModel, y, x)){
                    generateModel(DiodeLeftModel, y, x);
                }
                break;
            }
        }
    }
}



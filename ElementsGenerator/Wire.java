package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Wire extends ElementsGenerator {

    static int[][] WireHorizontalModel = {{1, 1, 1, 1, 1}};
    static int[][] WireVerticalModel = {
            {1},
            {1},
            {1},
            {1},
            {1}};

    public static void generate(Cell cell, String orientation){
        int x = cell.getX();
        int y = cell.getY();


        Map mapa = Map.maps.get(Map.iteration);
        switch(orientation){
            case "Horizontal":{
                if(ElementsGenerator.gonnaFit(WireHorizontalModel, y, x)){
                    generateModel(WireHorizontalModel, y, x);
                }
                break;
            }
            case "Vertical":{
                if(ElementsGenerator.gonnaFit(WireVerticalModel, y, x)){
                    generateModel(WireVerticalModel, y, x);
                }
                break;
            }
        }
    }
}

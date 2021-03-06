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

    public void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY();


        switch(facing){
            case "up":{
                if(ElementsGenerator.gonnaFit(WireVerticalModel, y, x)){
                    generateModel(WireVerticalModel, y, x);
                }
                break;
            }
            case "down":{
                if(ElementsGenerator.gonnaFit(WireVerticalModel, y, x)){
                    generateModel(WireVerticalModel, y, x);
                }
                break;
            }
            case "right":{
                if(ElementsGenerator.gonnaFit(WireHorizontalModel, y, x)){
                    generateModel(WireHorizontalModel, y, x);
                }
                break;
            }
            case "left":{
                if(ElementsGenerator.gonnaFit(WireHorizontalModel, y, x)){
                    generateModel(WireHorizontalModel, y, x);
                }
                break;
            }
        }
    }
}

package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Generator extends ElementsGenerator {

    static int[][] GeneratorRightModel = {
            {0, 1, 2, 3, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0}};
    static int[][] GeneratorLeftModel = {
            {0, 1, 1, 3, 2, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0}};
    static int[][] GeneratorUpModel = {
            {0, 1, 0},
            {1, 0, 1},
            {1, 0, 1},
            {3, 0, 1},
            {2, 0, 1},
            {1, 0, 1},
            {0, 1, 0}};
    static int[][] GeneratorDownModel = {
            {0, 1, 0},
            {1, 0, 1},
            {2, 0, 1},
            {3, 0, 1},
            {1, 0, 1},
            {1, 0, 1},
            {0, 1, 0}};


    public void generate(Cell cell, String facing) {
        int x = cell.getX();
        int y = cell.getY() - 1;

        switch(facing){
            case "up":{
                if(ElementsGenerator.gonnaFit(GeneratorUpModel, y, x)){
                    generateModel(GeneratorUpModel, y, x);
                }
                break;
            }
            case "down":{
                if(ElementsGenerator.gonnaFit(GeneratorDownModel, y, x)){
                    generateModel(GeneratorDownModel, y, x);
                }
                break;
            }
            case "right":{
                if(ElementsGenerator.gonnaFit(GeneratorRightModel, y, x)){
                    generateModel(GeneratorRightModel, y, x);
                }
                break;
            }
            case "left":{
                if(ElementsGenerator.gonnaFit(GeneratorLeftModel, y, x)){
                    generateModel(GeneratorLeftModel, y, x);
                }
                break;
            }
        }
    }
}

package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Generator extends ElementsGenerator {

    static int[][] GeneratorModel0 = {
            {0, 1, 2, 3, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0}};
    static int[][] GeneratorModel3 = {
            {0, 1, 1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 3, 2, 1, 0}};

    public static void generate(Cell cell, String tick) {
        int x = cell.getX();
        int y = cell.getY() - 1;

        if(ElementsGenerator.gonnaFit(GeneratorModel0, y, x)){
            switch(tick){
                case "0":{
                    generateModel(GeneratorModel0, y, x);
                    break;
                }
                case "3":{
                    generateModel(GeneratorModel3, y, x);
                    break;
                }
            }


        }
    }
}

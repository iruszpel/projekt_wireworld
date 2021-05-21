package ElementsGenerator;

import Cell.Cell;

public class AND extends ElementsGenerator {
    static int[][] AndRightModel = {
            {1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,1,0,1,0,0,0,1,0,0},
            {1,0,0,0,1,1,1,0,0,0,0,1,0,0},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,0},
            {0,1,0,1,0,0,0,0,1,1,1,0,0,1},
            {0,1,0,1,0,0,0,0,0,1,0,1,1,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0}};
    static int[][] AndLeftModel = {
            {0,0,0,0,0,0,1,1,1,1,1,1,1,1},
            {0,0,0,1,1,1,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,1,0,1,0,0,0,0,0},
            {0,0,1,0,0,0,0,1,1,1,0,0,0,1},
            {0,0,1,0,1,0,1,0,1,0,1,0,1,0},
            {1,0,0,1,1,1,0,0,0,0,1,0,1,0},
            {0,1,1,0,1,0,0,0,0,0,1,0,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,1,0,0}};
    static int[][] AndUpModel = {
            {0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,1,0},
            {0,0,1,1,1,0,1,0},
            {0,1,0,0,0,1,0,0},
            {0,1,0,0,1,1,1,0},
            {0,1,0,0,0,1,0,0},
            {1,0,0,0,1,0,0,0},
            {1,0,0,1,0,1,0,0},
            {1,0,1,1,1,0,0,0},
            {1,0,0,1,0,0,0,0},
            {1,0,0,0,1,1,1,0},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,1,1,1,0},
            {1,0,0,1,0,0,0,0}};
    static int[][] AndDownModel = {
            {1,0,0,1,0,0,0,0},
            {1,0,0,0,1,1,1,0},
            {1,0,0,0,0,0,0,1},
            {1,0,0,0,1,1,1,0},
            {1,0,0,1,0,0,0,0},
            {1,0,1,1,1,0,0,0},
            {1,0,0,1,0,0,0,0},
            {1,0,0,0,1,0,0,0},
            {0,1,0,0,0,1,0,0},
            {0,1,0,0,1,1,1,0},
            {0,1,0,0,0,1,0,0},
            {0,0,1,1,1,0,1,0},
            {0,0,0,0,0,0,1,0},
            {0,0,0,0,0,1,0,0}};

    public static void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY() - 1; //ustawienie punktu początkowego na komórkę[0][0] modelu

        switch(facing){
            case "up":{
                if(ElementsGenerator.gonnaFit(AndUpModel, y, x)){
                    generateModel(AndUpModel, y, x);
                }
                break;
            }
            case "down":{
                if(ElementsGenerator.gonnaFit(AndDownModel, y, x)){
                    generateModel(AndDownModel, y, x);
                }
                break;
            }
            case "right":{
                if(ElementsGenerator.gonnaFit(AndRightModel, y, x)){
                    generateModel(AndRightModel, y, x);
                }
                break;
            }
            case "left":{
                if(ElementsGenerator.gonnaFit(AndLeftModel, y, x)){
                    generateModel(AndLeftModel, y, x);
                }
                break;
            }
        }
    }
}



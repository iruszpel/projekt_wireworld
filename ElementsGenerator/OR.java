package ElementsGenerator;

import Cell.Cell;

public class OR extends ElementsGenerator {
    static int[][] OrRightModel = {
            {1,1,1,0,0},
            {0,0,0,1,0},
            {0,0,1,1,1},
            {0,0,0,1,0},
            {1,1,1,0,0}};
    static int[][] OrLeftModel = {
            {0,0,1,1,1},
            {0,1,0,0,0},
            {1,1,1,0,0},
            {0,1,0,0,0},
            {0,0,1,1,1}};
    static int[][] OrUpModel = {
            {0,0,1,0,0},
            {0,1,1,1,0},
            {1,0,1,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1}};
    static int[][] OrDownModel = {
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,1,0,1},
            {0,1,1,1,0},
            {0,0,1,0,0}};

    public void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY(); //ustawienie punktu początkowego na komórkę[0][0] modelu

        switch(facing){
            case "up":{
                if(ElementsGenerator.gonnaFit(OrUpModel, y, x)){
                    generateModel(OrUpModel, y, x);
                }
                break;
            }
            case "down":{
                if(ElementsGenerator.gonnaFit(OrDownModel, y, x)){
                    generateModel(OrDownModel, y, x);
                }
                break;
            }
            case "right":{
                if(ElementsGenerator.gonnaFit(OrRightModel, y, x)){
                    generateModel(OrRightModel, y, x);
                }
                break;
            }
            case "left":{
                if(ElementsGenerator.gonnaFit(OrLeftModel, y, x)){
                    generateModel(OrLeftModel, y, x);
                }
                break;
            }
        }
    }
}



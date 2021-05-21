package ElementsGenerator;

import Cell.Cell;

public class XOR extends ElementsGenerator {
    static int[][] XorRightModel = {
            {1,1,1,0,0},
            {0,0,0,1,0},
            {0,0,1,1,1},
            {0,0,0,1,0},
            {1,1,1,0,0}};
    static int[][] XorLeftModel = {
            {0,0,1,1,1},
            {0,1,0,0,0},
            {1,1,1,0,0},
            {0,1,0,0,0},
            {0,0,1,1,1}};
    static int[][] XorUpModel = {
            {0,0,1,0,0},
            {0,1,1,1,0},
            {1,0,1,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1}};
    static int[][] XorDownModel = {
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,1,0,1},
            {0,1,1,1,0},
            {0,0,1,0,0}};

    public static void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY() - 1; //ustawienie punktu początkowego na komórkę[0][0] modelu

        switch(facing){
            case "up":{
                if(ElementsGenerator.gonnaFit(XorUpModel, y, x)){
                    generateModel(XorUpModel, y, x);
                }
                break;
            }
            case "down":{
                if(ElementsGenerator.gonnaFit(XorDownModel, y, x)){
                    generateModel(XorDownModel, y, x);
                }
                break;
            }
            case "right":{
                if(ElementsGenerator.gonnaFit(XorRightModel, y, x)){
                    generateModel(XorRightModel, y, x);
                }
                break;
            }
            case "left":{
                if(ElementsGenerator.gonnaFit(XorLeftModel, y, x)){
                    generateModel(XorLeftModel, y, x);
                }
                break;
            }
        }
    }
}



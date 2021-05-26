package ElementsGenerator;

import Cell.Cell;

public class XOR extends ElementsGenerator {
    static int[][] XorRightModel = {
            {0,1,1,0,0,0,0},
            {1,0,0,1,0,0,0},
            {0,0,1,1,1,1,0},
            {0,0,1,0,1,1,1},
            {0,0,1,1,1,1,0},
            {1,0,0,1,0,0,0},
            {0,1,1,0,0,0,0}};
    static int[][] XorLeftModel = {
            {0,0,0,0,1,1,0},
            {0,0,0,1,0,0,1},
            {0,1,1,1,1,0,0},
            {1,1,0,0,1,0,0},
            {0,1,1,1,1,0,0},
            {0,0,0,1,0,0,1},
            {0,0,0,0,1,1,0}};
    static int[][] XorUpModel = {
            {0,0,0,1,0,0,0},
            {0,0,1,1,1,0,0},
            {0,0,1,0,1,0,0},
            {0,1,1,0,1,1,0},
            {1,0,1,1,1,0,1},
            {1,0,0,0,0,0,1},
            {0,1,0,0,0,1,0}};
    static int[][] XorDownModel = {
            {0,1,0,0,0,1,0},
            {1,0,0,0,0,0,1},
            {1,0,1,1,1,0,1},
            {0,1,1,0,1,1,0},
            {0,0,1,0,1,0,0},
            {0,0,1,1,1,0,0},
            {0,0,0,1,0,0,0}};

    public void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY();

        switch(facing){
            case "up":{
                x--;
                if(ElementsGenerator.gonnaFit(XorUpModel, y, x)){
                    generateModel(XorUpModel, y, x);
                }
                break;
            }
            case "down":{
                x--;
                if(ElementsGenerator.gonnaFit(XorDownModel, y, x)){
                    generateModel(XorDownModel, y, x);
                }
                break;
            }
            case "right":{
                y--;
                if(ElementsGenerator.gonnaFit(XorRightModel, y, x)){
                    generateModel(XorRightModel, y, x);
                }
                break;
            }
            case "left":{
                y--;
                if(ElementsGenerator.gonnaFit(XorLeftModel, y, x)){
                    generateModel(XorLeftModel, y, x);
                }
                break;
            }
        }
    }
}



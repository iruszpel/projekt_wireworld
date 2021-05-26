package ElementsGenerator;

import Cell.Cell;

public class NOT extends ElementsGenerator {
    static int[][] NotRightModel = {
            {0,0,1,0,0},
            {1,1,1,1,1},
            {0,0,1,0,0}};
    static int[][] NotUpModel = {
            {0,1,0},
            {0,1,0},
            {1,1,1},
            {0,1,0},
            {0,1,0}};


    public void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY();

        switch(facing){
            case "up":{
                x--;
                if(ElementsGenerator.gonnaFit(NotUpModel, y, x)){
                    generateModel(NotUpModel, y, x);
                }
                break;
            }
            case "down":{
                x--;
                if(ElementsGenerator.gonnaFit(NotUpModel, y, x)){
                    generateModel(NotUpModel, y, x);
                }
                break;
            }
            case "right":{
                y--;
                if(ElementsGenerator.gonnaFit(NotRightModel, y, x)){
                    generateModel(NotRightModel, y, x);
                }
                break;
            }
            case "left":{
                y--;
                if(ElementsGenerator.gonnaFit(NotRightModel, y, x)){
                    generateModel(NotRightModel, y, x);
                }
                break;
            }
        }
    }
}



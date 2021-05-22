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
        int y = cell.getY() - 1; //ustawienie punktu początkowego na komórkę[0][0] modelu

        switch(facing){
            case "up":{
                if(ElementsGenerator.gonnaFit(NotUpModel, y, x)){
                    generateModel(NotUpModel, y, x);
                }
                break;
            }
            case "down":{
                if(ElementsGenerator.gonnaFit(NotUpModel, y, x)){
                    generateModel(NotUpModel, y, x);
                }
                break;
            }
            case "right":{
                if(ElementsGenerator.gonnaFit(NotRightModel, y, x)){
                    generateModel(NotRightModel, y, x);
                }
                break;
            }
            case "left":{
                if(ElementsGenerator.gonnaFit(NotRightModel, y, x)){
                    generateModel(NotRightModel, y, x);
                }
                break;
            }
        }
    }
}



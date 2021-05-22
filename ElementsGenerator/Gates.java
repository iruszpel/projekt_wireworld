package ElementsGenerator;
//DEPRECATED
//DO  NOT USE
import Cell.Cell;
import WireWorld.Map;

public class Gates extends ElementsGenerator {
    static int[][] ORmodel = {
            {1,1,1,0,0},
            {0,0,0,1,0},
            {0,0,1,1,1},
            {0,0,0,1,0},
            {1,1,1,0,0}};
    static int[][] XORmodel = {
            {0,1,1,0,0,0,0},
            {1,0,0,1,0,0,0},
            {0,0,1,1,1,1,0},
            {0,0,1,0,1,1,1},
            {0,0,1,1,1,1,0},
            {1,0,0,1,0,0,0},
            {0,1,1,0,0,0,0}};
    static int[][] ANDmodel = {
            {1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,1,0,1,0,0,0,1,0,0},
            {1,0,0,0,1,1,1,0,0,0,0,1,0,0},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,0},
            {0,1,0,1,0,0,0,0,1,1,1,0,0,1},
            {0,1,0,1,0,0,0,0,0,1,0,1,1,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0}};
    static int[][] NOTmodel = {
            {0,0,1,0,0},
            {1,1,1,1,1},
            {0,0,1,0,0}};

    public void generate(Cell cell, String gate){
        int x = cell.getX();
        int y = cell.getY();

        switch(gate) {
            case "OR": {

                if(gonnaFit(ORmodel, y, x)){
                    generateModel(ORmodel, y, x);
                }
                break;
            }
                case "AND": {
                    if(gonnaFit(ANDmodel, y, x)){
                        generateModel(ANDmodel, y, x);
                    }
                    break;
                }
                case "XOR": {
                    y--;
                    if(gonnaFit(XORmodel, y, x)){
                        generateModel(XORmodel, y, x);
                    }
                    break;
                }
                case "NOT": {
                    y--;
                    if(gonnaFit(NOTmodel, y, x)){
                        generateModel(NOTmodel, y, x);
                    }
                    break;
                }
            }
        }
    }


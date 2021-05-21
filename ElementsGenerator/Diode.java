package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Diode extends ElementsGenerator {
    static int[][] DiodeNormalModel = {
            {0, 0, 0, 1, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 0, 0}};
    static int[][] DiodeReversedModel = {
            {0, 0, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 1, 0, 0, 0}};

    public static void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY() - 1; //ustawienie punktu początkowego na komórkę[0][0] modelu

        int f = facing.equals("Normal") ? 1 : -1;


        Map mapa = Map.maps.get(Map.iteration);
        if(ElementsGenerator.gonnaFit(DiodeNormalModel, y, x)){
            if(facing.equals("Normal")){
                generateModel(DiodeNormalModel, y, x);
            }
            else if(facing.equals("Reversed")){
                generateModel(DiodeReversedModel, y, x);
            }

        }
    }
}



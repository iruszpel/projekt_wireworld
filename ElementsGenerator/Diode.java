package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Diode extends ElementsGenerator {
    static int[][] NormalModel = {
            {0, 0, 0, 1, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 0, 0}};
    static int[][] ReversedModel = {
            {0, 0, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 1, 0, 0, 0}};

    public static void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY() - 1; //ustawienie punktu początkowego na komórkę[0][0] modelu

        int f = facing.equals("Normal") ? 1 : -1;


        Map mapa = Map.maps.get(Map.iteration);
        if(x + 6 >= Map.width || x < 0 || y + 2 >= Map.height || y < 0){
            System.out.println("Element nie zmieści się na mapie!");
            // Dobrze byłoby zrobić okienko w gui na komunikaty

        }else {
            if(facing.equals("Normal")){
                for (int i = 0; i < NormalModel.length; i++) {
                    for (int j = 0; j < NormalModel[0].length; j++) {
                        mapa.setCell(y + i, x + j, NormalModel[i][j]); // przekopiowanie macierzy na mape
                    }
                }
            }
            else if(facing.equals("Reversed")){
                for (int i = 0; i < ReversedModel.length; i++) {
                    for (int j = 0; j < ReversedModel[0].length; j++) {
                        mapa.setCell(y + i, x + j, ReversedModel[i][j]); // przekopiowanie macierzy na mape
                    }
                }
            }

        }
    }
}



package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Generator extends ElementsGenerator {

    static int[][] model0 = {
            {0, 1, 2, 3, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0}};
    static int[][] model3 = {
            {0, 1, 1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 3, 2, 1, 0}};

    public static void generate(Cell cell, String tick) {
        int x = cell.getX();
        int y = cell.getY() - 1;

        Map mapa = Map.maps.get(Map.iteration);
        if (x + 6 >= Generator.width || x < 0 || y + 2 >= Generator.height || y < 0) {
            System.out.println("Element nie zmieści się na mapie!");
            // Dobrze byłoby zrobić okienko w gui na komunikaty

        } else {
            switch(tick){
                case "0":{
                    for (int i = 0; i < model0.length; i++) {
                        for (int j = 0; j < model0[0].length; j++) {
                            mapa.setCell(y + i, x + j, model0[i][j]); // przekopiowanie macierzy na mape
                        }
                    }
                    break;
                }
                case "3":{
                    for (int i = 0; i < model3.length; i++) {
                        for (int j = 0; j < model3[0].length; j++) {
                            mapa.setCell(y + i, x + j, model3[i][j]); // przekopiowanie macierzy na mape
                        }
                    }
                    break;
                }
            }


        }
    }
}

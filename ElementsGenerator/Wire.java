package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Wire extends ElementsGenerator {

    static int[][] HorizontalModel = {{1, 1, 1, 1, 1}};
    static int[][] VerticalModel = {
            {1},
            {1},
            {1},
            {1},
            {1}};

    public static void generate(Cell cell, String orientation){
        int x = cell.getX();
        int y = cell.getY();


        Map mapa = Map.maps.get(Map.iteration);
        if(orientation.equals("Horizontal")) {
            if (x + 4 >= Generator.width || x < 0) {
                System.out.println("Element nie zmieści się na mapie!");
            }
            else {
                for (int i = 0; i < HorizontalModel.length; i++) {
                    for (int j = 0; j < HorizontalModel[0].length; j++) {
                        mapa.setCell(y + i, x + j, HorizontalModel[i][j]); // przekopiowanie macierzy na mape
                    }
                }
            }
        }

        else if (orientation.equals("Vertical")){
            if(y + 4 >= Generator.height || y < 0) {
                System.out.println("Element nie zmieści się na mapie!");
            }
            else {
                for (int i = 0; i < VerticalModel.length; i++) {
                    for (int j = 0; j < VerticalModel[0].length; j++) {
                        mapa.setCell(y + i, x + j, VerticalModel[i][j]); // przekopiowanie macierzy na mape
                    }
                }
            }
        }
    }
}

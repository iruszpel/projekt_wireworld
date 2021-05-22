package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class ElementsGenerator {

    static int height = Map.height;
    static int width = Map.width;

    public static boolean gonnaFit(int[][] element, int y, int x){
        if (x + element[0].length > Generator.width || x < 0 || y + element.length > Generator.height || y < 0) {
            System.err.println("Element nie zmieści się na mapie!");
            return false;
        }
      return true;
    };

    public static void generateModel(int[][] element, int y, int x){
        Map mapa = Map.maps.get(Map.iteration);
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element[0].length; j++) {
                mapa.setCell(y + i, x + j, element[i][j]); // przekopiowanie macierzy na mape
            }
        }
    }
    public void generate(Cell cell, String facing) {}
}

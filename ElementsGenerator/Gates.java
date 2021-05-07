package ElementsGenerator;

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
    //AND gate nie działa
    static int[][] ANDmodel = {
            {1,0,0,0,0,0,0},
            {0,1,0,1,0,0,0},
            {1,1,1,1,1,0,1},
            {0,1,0,1,0,1,0},
            {0,1,0,0,0,1,0},
            {0,0,1,1,1,0,0},
            {1,1,0,0,0,0,0}};

    public static void generate(Cell cell, String gate){
        System.out.println(gate);
        int x = cell.getX();
        int y = cell.getY();
        Map mapa = Map.maps.get(Map.iteration);
        switch(gate) {
            case "OR": {

                if (x + ORmodel[0].length >= Map.width || x < 0 || y + ORmodel.length >= Map.height || y < 0) {
                    System.out.println("Element nie zmieści się na mapie!");
                    // Dobrze byłoby zrobić okienko w gui na komunikaty

                } else {
                    for (int i = 0; i < ORmodel.length; i++) {
                        for (int j = 0; j < ORmodel[0].length; j++) {
                            mapa.setCell(y + i, x + j, ORmodel[i][j]); // przekopiowanie macierzy na mape
                        }
                    }

                    break;
                }
            }
                case "AND": {
                    if (x + ANDmodel[0].length >= Map.width || x < 0 || y + ANDmodel.length >= Map.height || y < 0) {
                        System.out.println("Element nie zmieści się na mapie!");
                        // Dobrze byłoby zrobić okienko w gui na komunikaty

                    } else {
                        for (int i = 0; i < ANDmodel.length; i++) {
                            for (int j = 0; j < ANDmodel[0].length; j++) {
                                mapa.setCell(y + i, x + j, ANDmodel[i][j]); // przekopiowanie macierzy na mape
                            }
                        }
                    }
                    break;
                }
                case "XOR": {
                    if (x + XORmodel[0].length >= Map.width || x < 0 || y + XORmodel.length >= Map.height || y < 0) {
                        System.out.println("Element nie zmieści się na mapie!");
                        // Dobrze byłoby zrobić okienko w gui na komunikaty

                    } else {
                        for (int i = 0; i < XORmodel.length; i++) {
                            for (int j = 0; j < XORmodel[0].length; j++) {
                                mapa.setCell(y + i, x + j, XORmodel[i][j]); // przekopiowanie macierzy na mape
                            }
                        }
                    }
                    break;
                }
            }
        }
    }


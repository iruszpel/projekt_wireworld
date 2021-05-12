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
    static int[][] ANDmodel = {
            {1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,1,0,1,0,0,0,1,0,0},
            {1,0,0,0,1,1,1,0,0,0,0,1,0,0},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,0},
            {0,1,0,1,0,0,0,0,1,1,1,0,0,1},
            {0,1,0,1,0,0,0,0,0,1,0,1,1,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0}};
    /* ten AND nie działa
    static int[][] ANDmodel = {
            {1,0,0,0,0,0,0},
            {0,1,0,1,0,0,0},
            {1,1,1,1,1,0,1},
            {0,1,0,1,0,1,0},
            {0,1,0,0,0,1,0},
            {0,0,1,1,1,0,0},
            {1,1,0,0,0,0,0}};*/
    static int[][] NOTmodel = {
            {0,0,1,0,0},
            {1,1,1,1,1},
            {0,0,1,0,0},};

    public static void generate(Cell cell, String gate){
        //System.out.println(gate);
        int x = cell.getX();
        int y = cell.getY();
        Map mapa = Map.maps.get(Map.iteration);
        switch(gate) {
            case "OR": {

                if (x + ORmodel[0].length >= Generator.width || x < 0 || y + ORmodel.length >= Generator.height || y < 0) {
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
                    if (x + ANDmodel[0].length >= Generator.width || x < 0 || y + ANDmodel.length >= Generator.height || y < 0) {
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
                    if (x + XORmodel[0].length >= Generator.width || x < 0 || y + XORmodel.length >= Generator.height || y < 0) {
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
                case "NOT": {
                    y--;
                    if (x + NOTmodel[0].length >= Generator.width || x < 0 || y + NOTmodel.length >= Generator.height || y < 0) {
                        System.out.println("Element nie zmieści się na mapie!");
                        // Dobrze byłoby zrobić okienko w gui na komunikaty

                    } else {
                        for (int i = 0; i < NOTmodel.length; i++) {
                            for (int j = 0; j < NOTmodel[0].length; j++) {
                                mapa.setCell(y + i, x + j, NOTmodel[i][j]); // przekopiowanie macierzy na mape
                            }
                        }
                    }
                    break;
                }
            }
        }
    }


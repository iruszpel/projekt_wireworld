package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Diode extends ElementsGenerator {


    public static void generate(Cell cell, String facing){
        int x = cell.getX() + 3; //środek diody nie pisać kodu dla normal/reversed 2 razy
        int y = cell.getY();

        int f = facing.equals("Normal") ? 1 : -1;


        Map mapa = Map.maps.get(Map.iteration);
        if(x + 3 >= Map.width || x - 3 < 0 || y + 1 >= Map.height || y - 1 < 0){
            System.out.println("Element nie zmieści się na mapie!");
            // Dobrze byłoby zrobić okienko w gui na komunikaty

        }else {
            for (int i = -3; i < 4; i++) {
                if (i == 0) {
                    mapa.setCell(y, x + i * f, 0); //mnożąc przez f robimy lustrzane odbicie
                } else {
                    mapa.setCell(y, x + i * f, 1);
                }
            }
            for (int j = -1; j < 1; j++) {
                mapa.setCell(y + 1, x + j * f, 1);
                mapa.setCell(y - 1, x + j * f, 1);
            }

        }
    }
}



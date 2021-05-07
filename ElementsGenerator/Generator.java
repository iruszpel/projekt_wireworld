package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Generator extends ElementsGenerator {


    public static void generate(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        Map mapa = Map.maps.get(Map.iteration);
        if(x + 6 >= Map.width || x < 0 || y + 1 >= Map.height || y - 1 < 0) {
            System.out.println("Element nie zmieści się na mapie!");
            // Dobrze byłoby zrobić okienko w gui na komunikaty

        }
        else {
            for (int i = 1; i < 6; i++) {

                mapa.setCell(y+1, x + i, 1);
                mapa.setCell(y-1, x + i, 1);

            }
            mapa.setCell(y, x, 1);
            mapa.setCell(y, x + 6, 1);

            //elektrony
            mapa.setCell(y + 1, x+3, 3);
            mapa.setCell(y - 1, x+3, 3);
            mapa.setCell(y + 1, x + 4, 2);
            mapa.setCell(y - 1, x + 2, 2);
            }

    }
}

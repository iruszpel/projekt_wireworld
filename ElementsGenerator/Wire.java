package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Wire extends ElementsGenerator {

    public static void generate(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        Map mapa = Map.maps.get(Map.iteration);
        if(x + 4 >= Map.width || x - 3 < 0) {
            System.out.println("Element nie zmieści się na mapie!");
        }
        else {
            for(int i = 0; i < 5; i++){
                mapa.setCell(y, x + i, 1);
            }
        }
        }
}

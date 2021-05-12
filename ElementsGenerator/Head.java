package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Head extends ElementsGenerator {


    void generate(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        Map mapa = Map.maps.get(Map.iteration);
        mapa.setCell(y, x, 3);

    }

}

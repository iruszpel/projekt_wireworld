package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Tail extends ElementsGenerator {


    public void generate(Cell cell, String facing){
        int x = cell.getX();
        int y = cell.getY();

        Map mapa = Map.maps.get(Map.iteration);
        mapa.setCell(y, x, 2);

    }

}

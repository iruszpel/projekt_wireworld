package ElementsGenerator;

import Cell.Cell;
import WireWorld.Map;

public class Conductor extends BasicElementsGenerator {


    public void generate(Cell cell){
        int x = cell.getX();
        int y = cell.getY();

        Map mapa = Map.maps.get(Map.iteration);
        mapa.setCell(y, x, 1);

    }

}

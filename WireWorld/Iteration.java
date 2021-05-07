package WireWorld;

import Cell.Cell;

public class Iteration {

    public static void iterate(){
        Map currentIter = Map.maps.get(Map.iteration);
        Map nextIter = new Map(Map.height, Map.width);

        for(int y = 0; y < Map.height; y++){
            for(int x = 0; x < Map.width; x++){
                Cell c = currentIter.map[y][x];
                switch(c.getState()){
                    case 1: {
                        int n = currentIter.countNeighbours(c);
                        //System.out.println("Liczba sasiadów "+ n);

                        if (n == 1 || n == 2){
                            nextIter.setCell(y, x, 3); // kabel -> glowa
                        }
                        else{
                            nextIter.setCell(y, x, 1); // kabel zostaje kablem
                        }
                        break;
                    }
                    case 0: {
                        break;
                    }
                    case 3: {
                        nextIter.setCell(y, x, 2); //glowa -> ogon
                        break;
                    }
                    case 2: {
                        nextIter.setCell(y, x, 1); // ogon -> kabel
                        break;
                    }


                }
            }
        }
    }
}

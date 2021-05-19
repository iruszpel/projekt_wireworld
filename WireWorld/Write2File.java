package WireWorld;

import Cell.Cell;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Write2File {

    public static void WriteObjectToFile(Cell[][] mapa, String path) {
        try {

            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(mapa);
            objectOut.close();
            //System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

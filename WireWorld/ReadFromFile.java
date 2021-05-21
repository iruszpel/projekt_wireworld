package WireWorld;

import Cell.Cell;
import ElementsGenerator.*;

import java.io.*;
import java.util.Scanner;


public class ReadFromFile {
    private static Scanner scan;
    static int h = Map.height;
    static int w = Map.width;
    private static Map mapa;
    private static String extension;

    public static void read(String path) throws IOException, FileNotFoundException, ClassNotFoundException {

        try {
            File file = new File(path);
            scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Nie znaleziono pliku");
        }
        extension = path.substring(path.lastIndexOf(".")+1);

        switch(extension){
            case "txt":{
                mapa = new Map(h, w);
                while (scan.hasNextLine()) {
                    // pobranie elementow z jednej lini pliku oraz pozbycie sie niechcianych znakow
                    String elem = scan.next().replaceAll(",", "").replaceAll(":", "");
                    int y = Integer.parseInt(scan.next().replaceAll(",", "").replaceAll(":", ""));
                    int x = Integer.parseInt(scan.next().replaceAll(",", "").replaceAll(":", ""));
                    String facing = "right";
                    if(!(elem.equals("ElectronHead") || elem.equals("ElectronTail") ||elem.equals("Conductor") || elem.equals("Diode"))){
                        facing = scan.next().replaceAll(",", "").replaceAll(":", "");
                    }
                    switch (elem) {
                        case "ElectronHead": {
                            mapa.setCell(y, x, 3);
                            break;
                        }
                        case "ElectronTail": {
                            mapa.setCell(y, x, 2);
                            break;
                        }
                        case "Conductor": {
                            mapa.setCell(y, x, 1);
                            break;
                        }
                        case "Diode": {
                            Diode.generate(mapa.getCell(y, x), facing);
                            break;
                        }
                        case "Generator": {
                            Generator.generate(mapa.getCell(y, x), facing);
                            break;
                        }
                        case "Wire": {
                            Wire.generate(mapa.getCell(y, x), facing);
                            break;
                        }
                        case "OR": {
                            OR.generate(mapa.getCell(y, x), facing);
                            break;
                        }
                        case "XOR": {
                            XOR.generate(mapa.getCell(y, x), facing);
                            break;
                        }
                        case "AND": {
                            AND.generate(mapa.getCell(y, x), facing);
                            break;
                        }
                        case "NOT": {
                            NOT.generate(mapa.getCell(y, x), facing);
                            break;
                        }
                    }


                }
                break;
            }
            case "ser":{
                Cell[][] map;
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);

                map = (Cell[][]) ois.readObject();
                ois.close();

                h = map.length;
                w = map[0].length;

                mapa = new Map(h, w);
                mapa.map = map;
            }
        }


    }
}

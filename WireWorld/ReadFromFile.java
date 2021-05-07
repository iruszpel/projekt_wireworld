package WireWorld;

import ElementsGenerator.Diode;
import ElementsGenerator.Gates;
import ElementsGenerator.Generator;
import ElementsGenerator.Wire;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {
    private static Scanner scan;
    static int h = Map.height;
    static int w = Map.width;
    private static Map mapa;

    public static void read(String path) throws FileNotFoundException {

        try {
            File file = new File(path);
            scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Nie znaleziono pliku");
        }

        mapa = new Map(h, w);
        while (scan.hasNextLine()) {
            // pobranie elementow z jednej lini pliku oraz pozbycie sie niechcianych znakow
            String elem = scan.next().replaceAll(",", "").replaceAll(":", "");
            int y = Integer.parseInt(scan.next().replaceAll(",", "").replaceAll(":", ""));
            int x = Integer.parseInt(scan.next().replaceAll(",", "").replaceAll(":", ""));

            switch (elem) {
                case "ElectronHead": {
                    //System.out.printf("Elem=%s, y=%s, x=%s\n", elem, y, x);
                    mapa.setCell(y, x, 3);
                    break;
                }
                case "ElectronTail": {
                    //System.out.printf("Elem=%s, y=%s, x=%s\n", elem, y, x);
                    mapa.setCell(y, x, 2);
                    break;
                }
                case "Conductor": {
                    //System.out.printf("Elem=%s, y=%s, x=%s\n", elem, y, x);
                    mapa.setCell(y, x, 1);
                    break;
                }
                case "Diode": {
                    String facing = scan.next().replaceAll(",", "").replaceAll(":", "");
                    //System.out.printf("Elem=%s, y=%s, x=%s, facing=%s\n", elem, y, x, facing);
                    Diode.generate(mapa.getCell(y, x), facing);
                    break;
                }
                case "Generator": {
                    String tick = scan.next().replaceAll(",", "").replaceAll(":", "");

                    //System.out.printf("Elem=%s, y=%s, x=%s\n", elem, y, x);
                    Generator.generate(mapa.getCell(y, x), tick);
                    break;
                }
                case "Wire": {
                    String orientation = scan.next().replaceAll(",", "").replaceAll(":", "");
                    //System.out.printf("Elem=%s, y=%s, x=%s\n", elem, y, x);
                    Wire.generate(mapa.getCell(y, x), orientation);
                    break;
                }
                case "Gate": {
                    String gate = scan.next().replaceAll(",", "").replaceAll(":", "");
                    //System.out.printf("Elem=%s, y=%s, x=%s\n", elem, y, x);
                    Gates.generate(mapa.getCell(y, x), gate);
                    break;
                }
            }


        }
    }
}

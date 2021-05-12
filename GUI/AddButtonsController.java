package GUI;

import Cell.Cell;
import ElementsGenerator.ElementsGenerator;
import ElementsGenerator.Head;
import WireWorld.Map;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AddButtonsController implements GUIController {
    private Scene scene;
    private boolean isInAddingMode = false;
    public AddButtonsController(Scene scene) {
        this.scene = scene;

    }
    @Override
    public void enableListeners() {
        Canvas simCanvas = (Canvas) scene.lookup("#simCanvas");
        Button electronHeadButton = (Button) scene.lookup("#electronHeadButton");
        Slider iterSlider = (Slider) scene.lookup("#iterSlider");

        electronHeadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            isInAddingMode = !isInAddingMode;

            Main.currentIteration = 0;
            Main.canvasDrawer.drawIteration(Main.currentIteration);
            iterSlider.setValue(Main.currentIteration);

            //If clicked again disable adding mode
            if (!isInAddingMode) {
                simCanvas.setCursor(Cursor.DEFAULT);
                return;
            }
            simCanvas.setCursor(Cursor.HAND);
            //Draw map from 0th iteration




            /*
            simCanvas.hoverProperty().addListener(eventHover -> {

            });
            */


        });
        simCanvas.setOnMouseClicked(mouseEvent -> {
            if (!isInAddingMode)
                return;

            Pair<Integer, Integer> coords = Main.canvasDrawer.getCoordsFromXY((int)mouseEvent.getX(), (int)mouseEvent.getY());
            System.out.println(coords);
            //Miałem trochę większe ambicję że jak przesuwa się myszką to wyświetla podgląd ale okazuje się że z Canvas
            //nie da się uzyskać jaki jest kolor na danym pikselu a nie chcę rerenderować całej mapy z każdym przesunięciem myszki na inne pole
            //lub wyszukiwać jaki jest stan na mapie i dopasowywać kolor - aczkolwiek to już nie jest tak okropne i może to zrobię tylko wtedy
            //będzie jakaś Mapa z kolorami

            //From 0th iteration
            Map.iteration = 0;
            //Add new element
            Cell tempCell = Map.maps.get(0).getCell(coords.getValue(), coords.getKey());
            Head elem = new Head();
            elem.generate(tempCell);
            //Remove all maps but first
            Map tempMap = Map.maps.get(0); // remember first item
            Map.maps.clear(); // clear complete list
            Map.maps.add(tempMap);
            //Generate iterations based on new 0th iteration
            Main.generateIterations(Main.howManyIterations);
            Main.canvasDrawer.drawIteration(0);
        });

        }
}


package GUI;

import Cell.Cell;
import ElementsGenerator.*;
import WireWorld.Map;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddButtonsController implements GUIController {
    private Scene scene;
    private boolean isInAddingMode = false;
    //0,1,2,3 - top,right,bottom,left
    private int selectedDirection = 1;
    private boolean undoEnabled = false;

    private ImageView undoButton;
    private Canvas simCanvas;
    private Slider iterSlider;
    private VBox addElementsBox;

    private String elementType;
    private String gateType;
    private String basicElemType;
    private String otherElemType;

    private HashMap<String, BasicElementsGenerator> elementTypesMap = new HashMap<>();


    public AddButtonsController(Scene scene) {
        this.scene = scene;
        undoButton = (ImageView) scene.lookup("#undoButton");
        simCanvas = (Canvas) scene.lookup("#simCanvas");
        iterSlider = (Slider) scene.lookup("#iterSlider");
        addElementsBox = (VBox) scene.lookup("#addElementsBox");

        this.elementTypesMap.put("electronHead", new Head());
        this.elementTypesMap.put("electronTail", new Tail());
        this.elementTypesMap.put("conductor", new Conductor());


    }
    private void onAddClick(Node tempNode){
        isInAddingMode = !isInAddingMode;

        Main.currentIteration = 0;
        Main.canvasDrawer.drawIteration(Main.currentIteration);
        iterSlider.setValue(Main.currentIteration);

        ColorAdjust tempEffect = new ColorAdjust();
        //If clicked again disable adding mode
        if (!isInAddingMode) {
            simCanvas.setCursor(Cursor.DEFAULT);
            tempEffect.setBrightness(0.0);
            tempNode.setEffect(tempEffect);
            deactivateAllButtons();
            return;
        }
        tempEffect.setBrightness(-0.25);
        tempNode.setEffect(tempEffect);
        simCanvas.setCursor(Cursor.HAND);
    }

    private void deactivateAllButtons(){

        Set<Node> allAddButtons = addElementsBox.lookupAll(".addElemButton");
        for (Node button : allAddButtons) {
            ColorAdjust tempEffect = new ColorAdjust();
            tempEffect.setBrightness(0.0);
            button.setEffect(tempEffect);
        }

    }

    @Override
    public void enableListeners() {

        Set<Node> gateButtons = addElementsBox.lookupAll(".gateButton");
        //LOGIC GATES
        for (Node button : gateButtons) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                onAddClick(button);
                elementType = "GATE";
                //Find gate type with regex
                String id = button.getId();
                Pattern r = Pattern.compile("^.*(?=(GateButton))");
                Matcher m = r.matcher(id);
                m.find();
                gateType = m.group(0);


            });
        }
        //BASIC ELEMENTS
        Set<Node> basicElements = addElementsBox.lookupAll(".basicElemButton");
        for (Node button : basicElements) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                onAddClick(button);
                elementType = "BASIC";
                //Find element type with regex
                String id = button.getId();
                Pattern r = Pattern.compile("^.*(?=(Button))");
                Matcher m = r.matcher(id);
                m.find();
                basicElemType = m.group(0);


            });
        }

        //OTHER ELEMENTS
        Set<Node> otherElements = addElementsBox.lookupAll(".otherElemButton");
        for (Node button : otherElements) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                onAddClick(button);
                elementType = "OTHER";
                //Find element type with regex
                String id = button.getId();
                Pattern r = Pattern.compile("^.*(?=(Button))");
                Matcher m = r.matcher(id);
                m.find();
                otherElemType = m.group(0);


            });
        }



        undoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(!undoEnabled)
                return;

            Main.currentIteration = 0;
            iterSlider.setValue(Main.currentIteration);

            Main.canvasDrawer.restoreMapState();
            if (Main.canvasDrawer.historyStack.isEmpty()){
                disableUndoButton();
            }
        });

        simCanvas.setOnMouseClicked(mouseEvent -> {
            if (!isInAddingMode)
                return;

            Pair<Integer, Integer> coords = Main.canvasDrawer.getCoordsFromXY((int)mouseEvent.getX(), (int)mouseEvent.getY());

            //Save current map state
            Main.canvasDrawer.saveMapState();
            //Enable undo button
            enableUndoButton();

            //Remove all maps but first
            Map deepCopyMap = new Map(Map.maps.get(0).height, Map.maps.get(0).width); //Deep copy first map
            //From 0th iteration
            Map.iteration = 0;
            deepCopyMap.map = Map.maps.get(0).deepCopyMap(); // remember first item
            Map.maps.clear(); // clear complete list
            Map.maps.add(deepCopyMap);


            //Add new element
            Cell tempCell = Map.maps.get(0).getCell(coords.getValue(), coords.getKey());
            switch(elementType) {
                case "GATE": {
                    Gates elem = new Gates();
                    elem.generate(tempCell, gateType);
                    break;
                }
                case "BASIC": {
                    BasicElementsGenerator elem = elementTypesMap.get(basicElemType);
                    elem.generate(tempCell);
                    break;
                }
                case "OTHER": {
                    if (otherElemType.equals("generator")){
                        Generator elem = new Generator();
                        elem.generate(tempCell, "0");
                        break;
                    }
                }
            }


            //Generate iterations based on new 0th iteration
            Main.generateIterations(Main.howManyIterations);
            Main.canvasDrawer.drawIteration(0);
        });

        }
        private void enableUndoButton(){
            undoEnabled = true;
            ColorAdjust tempEffect = new ColorAdjust();
            tempEffect.setBrightness(1.0);
            undoButton.setEffect(tempEffect);
            undoButton.setCursor(Cursor.HAND);
        }
        private void disableUndoButton(){
            undoEnabled = false;
            ColorAdjust tempEffect = new ColorAdjust();
            tempEffect.setBrightness(0.6);
            undoButton.setEffect(tempEffect);
            undoButton.setCursor(Cursor.DEFAULT);
        }
}


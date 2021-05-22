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

    private String elemType;
    private String facingSide = "right"; //Default facing

    private HashMap<String, ElementsGenerator> elementTypesMap = new HashMap<>();


    public AddButtonsController(Scene scene) {
        this.scene = scene;
        undoButton = (ImageView) scene.lookup("#undoButton");
        simCanvas = (Canvas) scene.lookup("#simCanvas");
        iterSlider = (Slider) scene.lookup("#iterSlider");
        addElementsBox = (VBox) scene.lookup("#addElementsBox");

        this.elementTypesMap.put("electronHead", new Head());
        this.elementTypesMap.put("electronTail", new Tail());
        this.elementTypesMap.put("conductor", new Conductor());
        this.elementTypesMap.put("empty", new Empty());

        this.elementTypesMap.put("ORGate", new OR());
        this.elementTypesMap.put("NOTGate", new NOT());
        this.elementTypesMap.put("XORGate", new XOR());
        this.elementTypesMap.put("ANDGate", new AND());

        this.elementTypesMap.put("generator", new Generator());
        this.elementTypesMap.put("wire", new Wire());

        focusDirectionButton();

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

        Set<Node> addButtons = addElementsBox.lookupAll(".addElemButton");
        //All add buttons
        for (Node button : addButtons) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                onAddClick(button);
                //Find button type with regex
                String id = button.getId();
                Pattern r = Pattern.compile("^.*(?=(Button))");
                Matcher m = r.matcher(id);
                m.find();
                elemType = m.group(0);


            });
        }


        Set<Node> directionButtons = scene.getRoot().lookupAll(".directionButton");
        //All direction arrows
        for (Node button : directionButtons) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                //Find direction with regex
                String id = button.getId();
                Pattern r = Pattern.compile("^.*(?=(Button))");
                Matcher m = r.matcher(id);
                m.find();
                facingSide = m.group(0);
                focusDirectionButton();

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
        //User clicked on canvas while element selection was active
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
            ElementsGenerator elem = elementTypesMap.get(elemType);
            elem.generate(tempCell, facingSide);

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
        private void focusDirectionButton(){
            unfocusDirectionButtons();
            ColorAdjust tempEffect = new ColorAdjust();
            tempEffect.setBrightness(0.6);
            Node directionButton = scene.getRoot().lookup("#"+facingSide+"Button");
            directionButton.setEffect(tempEffect);
        }
        private void unfocusDirectionButtons(){
            ColorAdjust tempEffect = new ColorAdjust();
            tempEffect.setBrightness(1.0);
            Set<Node> directionButtons = scene.getRoot().lookupAll(".directionButton");
            //All direction arrows
            for (Node button : directionButtons) {
                button.setEffect(tempEffect);
            }

        }

}


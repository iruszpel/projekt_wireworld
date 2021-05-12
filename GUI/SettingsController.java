package GUI;

import WireWorld.Map;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SettingsController implements GUIController {
    private Scene scene;

    public SettingsController(Scene scene) {
        this.scene = scene;
    }
    @Override
    public void enableListeners() {
         /*
         *Ustawienia wewnątrz okna ustawień
         */
        AnchorPane settingsPanel = (AnchorPane) scene.lookup("#settingsBox");

        ImageView settingsButton = (ImageView) scene.lookup("#settingsButton");
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            settingsPanel.setVisible(true);
            event.consume();
        });

        ImageView settingsCloseButton = (ImageView) scene.lookup("#settingsClose");
        settingsCloseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            settingsPanel.setVisible(false);
            event.consume();
        });




        /*
        *Ustawienia poza oknem ustawień
        */
        TextField iterationsField = (TextField) scene.lookup("#iterField");
        TextField animationSpeedField = (TextField) scene.lookup("#animSpeedField");
        //Słuchacze pól tekstowych
        iterationsField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                    if (!newVal) { //Unfocused
                        int newIterations = iterationsField.getText().equals("") ? 0 : Integer.parseInt(iterationsField.getText());
                        if (Map.maps.size() - 1 < newIterations){
                            Main.generateIterations(newIterations - Main.howManyIterations);
                        }
                        Main.howManyIterations = newIterations;
                        Main.currentIteration = 0;
                        //Update slider
                        Slider iterSlider = (Slider) scene.lookup("#iterSlider");
                        iterSlider.setMax(Main.howManyIterations);
                        iterSlider.setValue(0);
                        iterSlider.setMajorTickUnit(Main.howManyIterations/5);
                        //Reset current iterations
                        Main.canvasDrawer.drawIteration(0);
                    }
        });
        animationSpeedField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { //Unfocused
                Main.animationSpeed = animationSpeedField.getText().equals("") ? 1 : Integer.parseInt(animationSpeedField.getText());
            }
        });


        //Ograniczenie tylko do wpisywania liczb w obu polach (można też zrobić klase NumberField która by rozszerzała TextField i przyjmowała tylko liczby):
        iterationsField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                iterationsField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });
        animationSpeedField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                animationSpeedField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        //Wpisanie w nie domyślnych ustawień

        iterationsField.setText(Integer.toString(Main.howManyIterations));
        animationSpeedField.setText(Integer.toString(Main.animationSpeed));


    }
}

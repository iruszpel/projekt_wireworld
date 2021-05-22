package GUI;

import WireWorld.Map;
import WireWorld.ReadFromFile;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SettingsController implements GUIController {
    private Scene scene;
    public static String fileFormat = "ser"; //Default file format

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
        TextField widthField = (TextField) scene.lookup("#widthField");
        TextField heightField = (TextField) scene.lookup("#heightField");

        ComboBox fileFormatBox = (ComboBox) scene.lookup("#fileFormatBox");
        fileFormatBox.getItems().add("Stan mapy (*.ser)");
        fileFormatBox.getItems().add("Obraz (*.png)");

        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            //Init height and width values
            widthField.setText(String.valueOf(Main.w));
            heightField.setText(String.valueOf(Main.h));
            //Init file format
            fileFormatBox.getSelectionModel().select(fileFormat == "ser" ? 0 : 1);
            settingsPanel.setVisible(true);
            event.consume();
        });

        ImageView settingsCloseButton = (ImageView) scene.lookup("#settingsClose");
        settingsCloseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            settingsPanel.setVisible(false);
            event.consume();
        });


        Button applyButton = (Button) scene.lookup("#applyButton");
        applyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            fileFormat = fileFormatBox.getSelectionModel().getSelectedIndex() == 0 ? "ser" : "png"; //Lepsza mapa jakby więcej formatów było
            int selectedHeight = Integer.parseInt(heightField.getText());
            int selectedWidth = Integer.parseInt(widthField.getText());
            if (Main.h != selectedHeight || Main.w != selectedWidth) {
                Map.height = Main.h = selectedHeight;
                Map.width = Main.w = selectedWidth;
                try {
                    Map.maps.clear();
                    Map.iteration = -1;
                    ReadFromFile.read(Main.currentFilePath);
                } catch(Exception e) {
                    System.out.println("Nastąpił błąd przy ponownym wczytywaniu pliku. Sprawdź czy plik wciąż istnieje");
                    return;
                }
                Main.generateIterations(Main.howManyIterations);
                Main.canvasDrawer.drawIteration(0);
            }

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

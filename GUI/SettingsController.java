package GUI;

import javafx.scene.Scene;
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
        AnchorPane settingsPanel = (AnchorPane) scene.lookup("#settingsbox");

        ImageView settingsButton = (ImageView) scene.lookup("#settingsbutton");
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            settingsPanel.setVisible(true);
            event.consume();
        });

        ImageView settingsCloseButton = (ImageView) scene.lookup("#settingsclose");
        settingsCloseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            settingsPanel.setVisible(false);
            event.consume();
        });




        /*
        *Ustawienia poza oknem ustawień
        */
        TextField iterationsField = (TextField) scene.lookup("#iterfield");
        TextField animationSpeedField = (TextField) scene.lookup("#animspeedfield");
        //Słuchacze pól tekstowych
        iterationsField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                    if (!newVal) { //Unfocused
                        System.out.println(iterationsField.getText());
                        System.out.println("W tym momencie trzeba będzie zapisać informację o tym jaką ilość iteracji wybrano");
                    }
        });
        animationSpeedField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { //Unfocused
                System.out.println(animationSpeedField.getText());
                System.out.println("W tym momencie trzeba będzie zapisać informację o tym jaką szybkość animacji wybrano");
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


    }
}

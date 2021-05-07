package GUI;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class IOButtonsController implements GUIController {
    private Scene scene;
    private Stage stage;
    public IOButtonsController(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }
    @Override
    public void enableListeners() {
        ImageView saveButton = (ImageView) scene.lookup("#savebutton");
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Plik tekstowy (*.txt)", "*.txt");
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Obraz png (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(textFilter);
            fileChooser.getExtensionFilters().add(imageFilter);

            File selectedFile = fileChooser.showSaveDialog(stage);
            event.consume();
        });

        ImageView openFileButton = (ImageView) scene.lookup("#openbutton");
        openFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);
            event.consume();
        });
    }
}

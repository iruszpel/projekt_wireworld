package GUI;

import WireWorld.Map;
import WireWorld.ReadFromFile;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class IOButtonsController implements GUIController {
    private Scene scene;
    private Stage stage;
    public IOButtonsController(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;

    }
    @Override
    public void enableListeners() {
        ImageView saveButton = (ImageView) scene.lookup("#saveButton");
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Plik tekstowy (*.txt)", "*.txt");
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Obraz png (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(textFilter);
            fileChooser.getExtensionFilters().add(imageFilter);

            File selectedFile = fileChooser.showSaveDialog(stage);
            event.consume();
        });

        ImageView openFileButton = (ImageView) scene.lookup("#openButton");
        Text openFileText = (Text) scene.lookup("#openFileText");
        openFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);

            try {
                if (selectedFile == null)
                    return;
                //ReadFromFile.read(selectedFile.getAbsolutePath());
                Map.maps.clear();
                Map.iteration = -1;
                ReadFromFile.read(selectedFile.getAbsolutePath());
            } catch(Exception e) {
                System.out.println("Nastąpił błąd przy wybieraniu pliku.");
                return;
            }

            Main.generateIterations(Main.howManyIterations);
            Main.canvasDrawer.clearMap();
            Main.canvasDrawer.drawEdges();
            Main.canvasDrawer.drawMap(0);

            openFileText.setText("Wczytany plik: " + selectedFile.getName());

            event.consume();
        });

    }
}

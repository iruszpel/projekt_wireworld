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
    private MapDrawer drawer;
    public IOButtonsController(Scene scene, Stage stage, MapDrawer drawer) {
        this.scene = scene;
        this.stage = stage;
        this.drawer = drawer;
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
        Text openFileText = (Text) scene.lookup("#openfiletext");
        openFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);
            try {
                //Lepiej wyczyścić listę map tutaj
                ReadFromFile.read(selectedFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            drawer.clearMap();


            drawer.drawEdges();
            drawer.drawMap(Map.maps.size()-1);//I tu zmienić na 0 jak się już zrobi czyszczenie
            openFileText.setText("Wczytany plik: " + selectedFile.getName());

            event.consume();
        });
    }
}

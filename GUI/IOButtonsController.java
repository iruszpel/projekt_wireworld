package GUI;

import WireWorld.Map;
import WireWorld.ReadFromFile;
import WireWorld.Write2File;
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

            FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Plik stanu mapy (*.ser)", "*.ser");
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Obraz png (*.png)", "*.png");
            if (SettingsController.fileFormat == "ser") fileChooser.getExtensionFilters().add(textFilter);
            if (SettingsController.fileFormat == "png") fileChooser.getExtensionFilters().add(imageFilter);

            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile == null)
                return;

            if (SettingsController.fileFormat == "ser")
                Write2File.WriteObjectToFile(Map.maps.get(Main.currentIteration).map, selectedFile.getAbsolutePath());
            if (SettingsController.fileFormat == "png")
                Main.canvasDrawer.saveMapToImage(selectedFile);
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

                Map.maps.clear();
                Map.iteration = -1;
                Main.currentFilePath = selectedFile.getAbsolutePath();
                ReadFromFile.read(selectedFile.getAbsolutePath());
            } catch(Exception e) {
                System.out.println("Nastąpił błąd przy wybieraniu pliku.");
                return;
            }

            Main.generateIterations(Main.howManyIterations);
            Main.canvasDrawer.drawIteration(0);

            openFileText.setText("Wczytany plik: " + selectedFile.getName());

            event.consume();
        });
        ImageView clearMapButton = (ImageView) scene.lookup("#clearMapButton");
        clearMapButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            clearMap();
            Main.currentFilePath = null;

            //Disable undo button and clear edit history
            AddButtonsController.disableUndoButton();
            Main.canvasDrawer.removeMapStates();

            event.consume();
        });

    }
    protected static void clearMap(){
        Map.maps.clear();
        Map.iteration = -1;
        new Map(Main.h,Main.w);
        Main.generateIterations(Main.howManyIterations);
        Main.canvasDrawer.drawIteration(0);
    }
}

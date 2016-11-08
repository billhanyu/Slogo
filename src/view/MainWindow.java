package view;

import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.workspace.DisplayLabelReader;

/**
 * @author Chalena Scholl
 * This class provides the main stage for the main UI framework.
 */


public class MainWindow {
	
	private static final String UI_RESOURCES = "resources/labels/MainWindow";
	private Stage mainStage;
	private HorizontalTabBar tabBar;
	private DisplayLabelReader valueReader;
	
	public MainWindow() {
		valueReader = new DisplayLabelReader(UI_RESOURCES);
		init();
	}

	private void init() {
		mainStage = new Stage();
		mainStage.setTitle(valueReader.getLabel("Title"));
		tabBar = new HorizontalTabBar(500, 20);
		VBox layout = new VBox(10, tabBar.getRoot());
        VBox.setVgrow(tabBar.getRoot(), Priority.ALWAYS);
        final Scene scene = new Scene(layout);
        layout.getStylesheets().add(valueReader.getLabel("StylesheetPath"));
        mainStage.setWidth(1000);
        mainStage.setHeight(780);
        mainStage.setScene(scene);
        mainStage.show();
	}

}

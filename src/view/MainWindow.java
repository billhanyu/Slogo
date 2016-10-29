package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
	
	private Stage mainStage;
	private HorizontalTabBar tabBar;
	
	public MainWindow() {
		init();
	}

	private void init() {
		mainStage = new Stage();
		//mainStage.setTitle(this.controller.getValueReader().getLabel("Title"));
		tabBar = new HorizontalTabBar(500, 20);
		VBox layout = new VBox(10, tabBar.getRoot());
        VBox.setVgrow(tabBar.getRoot(), Priority.ALWAYS);
        final Scene scene = new Scene(layout);
        mainStage.setWidth(1000);
        mainStage.setScene(scene);
        mainStage.show();
		
	}

}

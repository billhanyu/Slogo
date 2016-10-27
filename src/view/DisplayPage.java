package view;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DisplayPage{

	public void showPage(String pageLabel, DisplayLabelReader valueReader){
		Stage helpPage = new Stage();
        Scene scene = new Scene(new Group());
        VBox root = new VBox();     
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser); 
        scrollPane.setFitToWidth(true);;
        scrollPane.setFitToHeight(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
		String url = MainView.class.getResource(valueReader.getLabel(pageLabel)).toExternalForm();
        webEngine.load(url);
        root.getChildren().addAll(scrollPane);
        scene.setRoot(root);
        helpPage.setScene(scene);
        helpPage.show();
	}

}

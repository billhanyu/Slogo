package view;

import controller.Controller;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MenuView extends View {
	
	private MenuBar bar;

	public MenuView(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}
	
	private void init() {
		bar = new MenuBar();
		bar.setPrefWidth(this.getWidth());
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");
		Menu menuProperties = new Menu("Properties");
		Menu menuRun = new Menu("Run");
		bar.getMenus().addAll(menuFile, menuEdit, menuProperties, menuRun);
		this.getRoot().getChildren().add(bar);
		HBox.setHgrow(bar, Priority.ALWAYS);
	}

}

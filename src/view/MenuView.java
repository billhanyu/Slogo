package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import view.floating.FloatingViewManager;
import view.floating.PenPropertyView;
import view.floating.TurtleStateView;

public class MenuView extends View {
	
	private MenuBar bar;
	private FloatingViewManager floatingManager;

	public MenuView(Controller controller, double width, double height) {
		super(controller, width, height);
		floatingManager = new FloatingViewManager(controller);
		init();
	}
	
	private void init() {
		bar = new MenuBar();
		bar.setPrefWidth(this.getWidth());
		Menu menuFile = makeMenuFile();
		Menu menuEdit = makeMenuEdit();
		Menu menuProperties = makeMenuProperties();
		Menu menuRun = makeMenuRun();
		bar.getMenus().addAll(menuFile, menuEdit, menuProperties, menuRun);
		this.getRoot().getChildren().add(bar);
		HBox.setHgrow(bar, Priority.ALWAYS);
	}
	
	private Menu makeMenuFile() {
		Menu menu = new Menu("File");
		return menu;
	}
	
	private Menu makeMenuEdit() {
		Menu menu = new Menu("Edit");
		return menu;
	}
	
	private Menu makeMenuProperties() {
		Menu menu = new Menu("Properties");
		MenuItem turtleState = makeMenuItem("Turtle State",
				e -> floatingManager.show(TurtleStateView.class));
		MenuItem penState = makeMenuItem("Pen",
				e -> floatingManager.show(PenPropertyView.class));
		menu.getItems().addAll(turtleState, penState);
		return menu;
	}
	
	private Menu makeMenuRun() {
		Menu menu = new Menu("Run");
		MenuItem run = makeMenuItem("Run Script", 
				e -> this.getController().getMainView().getEditor().runScript());
		menu.getItems().add(run);
		return menu;
	}
	
	private MenuItem makeMenuItem(String name, EventHandler<ActionEvent> handler) {
		MenuItem item = new MenuItem(name);
		item.setOnAction(handler);
		return item;
	}

}

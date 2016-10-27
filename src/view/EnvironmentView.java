package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class EnvironmentView extends View {
	
	private GlobalVarsView globalVarsView;
	private UserCommandsView userCommandsView;
	private CommandHistoryView commandHistoryView;
	
	public EnvironmentView(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}
	
	public GlobalVarsView getGlobalVarsView() {
		return globalVarsView;
	}
	
	public UserCommandsView getUserCommandsView() {
		return userCommandsView;
	}
	
	public CommandHistoryView getCommandHistoryView() {
		return commandHistoryView;
	}
	
	
	private Tab makeTab(View toAdd){
		 Tab tab = new Tab();
		 tab.setText("toAdd");
		 tab.setContent(toAdd.getUI());
		 return tab;
	}
	
	private void init() {
		globalVarsView = new GlobalVarsView(this.getController(), this.getWidth(), this.getHeight() / 2);
		commandHistoryView = new CommandHistoryView(this.getController(), this.getWidth(), this.getHeight() / 2);
		userCommandsView = new UserCommandsView(this.getController(), this.getWidth(), this.getHeight() / 2);
		 TabPane tabPane = new TabPane();
		 tabPane.getTabs().addAll(makeTab(globalVarsView), makeTab(commandHistoryView), makeTab(userCommandsView));
		 this.getRoot().getChildren().add(tabPane);
	}
	
}

package view.workspace.environment;

import controller.Controller;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import view.workspace.View;

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

	private Tab makeTab(View toAdd, String label){
		Tab tab = new Tab();
		tab.setText(label);
		tab.setContent(toAdd.getUI());
		return tab;
	}

	private void init() {
		globalVarsView = new GlobalVarsView(this.getController(), this.getWidth(), this.getHeight() / 2.5);
		commandHistoryView = new CommandHistoryView(this.getController(), this.getWidth(), this.getHeight() / 2.5);
		userCommandsView = new UserCommandsView(this.getController(), this.getWidth(), this.getHeight() / 2.5);
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(makeTab(globalVarsView, "Variables"), makeTab(commandHistoryView, "History"), makeTab(userCommandsView, "User Commands"));
		this.getRoot().getChildren().add(tabPane);
	}

}

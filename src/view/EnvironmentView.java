package view;

import controller.Controller;

public class EnvironmentView extends View {
	
	public EnvironmentView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
	}

	private GlobalVarsView globalVarsView;
	private UserCommandsView userCommandsView;
	private CommandHistoryView commandHistoryView;
	
}

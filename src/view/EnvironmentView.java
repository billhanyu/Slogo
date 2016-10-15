package view;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Node;

public class EnvironmentView extends View {
	
	public EnvironmentView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
	}

	private GlobalVarsView globalVarsView;
	private UserCommandsView userCommandsView;
	private CommandHistoryView commandHistoryView;
	private Group root;
	
	@Override
	public Node getUI() {
		root.getChildren().addAll(globalVarsView.getUI(), userCommandsView.getUI(), commandHistoryView.getUI());
		return root;
	}

}

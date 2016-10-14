package view;

import javafx.scene.Group;
import javafx.scene.Node;

public class EnvironmentView implements Displayable {
	
	@Override
	public void init(double x, double y, double width, double height) {
		// TODO Auto-generated method stub
		
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

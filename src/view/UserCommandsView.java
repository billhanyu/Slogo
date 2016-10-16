package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import model.UserCommands;

public class UserCommandsView extends EnvironmentListView {
	
	private static final String LABEL = "Commands";
	
	public UserCommandsView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
	}

	public void update(UserCommands cmds) {
		
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			System.out.println("User Command selected: " + newVal);
		};
	}

	@Override
	String getLabelString() {
		return LABEL;
	}
}

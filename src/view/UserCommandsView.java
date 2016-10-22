package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;

public class UserCommandsView extends EnvironmentListView {
	
	
	public UserCommandsView(Controller controller, double width, double height) {
		super(controller, width, height);
	}
	
	@Override
	public String getLabelString() {
		return this.getController().getValueReader().getLabel("Commands");
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			System.out.println("User Command selected: " + newVal);
		};
	}
	
}

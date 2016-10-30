package view.workspace.environment;

import controller.Controller;

public class UserCommandsView extends EnvironmentListView {
	
	
	public UserCommandsView(Controller controller, double width, double height) {
		super(controller, width, height);
	}
	
	@Override
	public String getLabelString() {
		return this.getController().getValueReader().getLabel("Commands");
	}
	
	@Override
	protected void onDoubleClickItem(String selected) {
		this.getController().putScript(selected);
	}
	
}

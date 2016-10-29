package view.workspace.environment;

import controller.Controller;

public class GlobalVarsView extends EnvironmentListView {
	
	public GlobalVarsView(Controller controller, double width, double height) {
		super(controller, width, height);
	}
	
	@Override
	public String getLabelString() {
		return this.getLabelReader().getLabel("Variables");
	}

	@Override
	protected void onDoubleClickItem(String selected) {
		this.getController().getMainView().getEditor().appendText(selected);
	}
	
}

package view;

import controller.Controller;

public class CommandHistoryView extends EnvironmentListView {
	
	public CommandHistoryView(Controller controller, double width, double height) {
		super(controller, width, height);
	}

	@Override
	public String getLabelString() {
		return this.getLabelReader().getLabel("CommandHistory");
	}

	@Override
	protected void onDoubleClickItem(String selected) {
		this.getController().putScript(selected);
		this.getController().getMainView().getEditor().runScript();
	}

}

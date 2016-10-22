package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;

public class CommandHistoryView extends EnvironmentListView {
	
	public CommandHistoryView(Controller controller, double width, double height) {
		super(controller, width, height);
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			this.getController().putScript(newVal);
		};
	}

	@Override
	public String getLabelString() {
		return this.getLabelReader().getLabel("CommandHistory");
	}

}

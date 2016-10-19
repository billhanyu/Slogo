package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import model.CommandHistory;

public class CommandHistoryView extends EnvironmentListView {
	
	public CommandHistoryView(Controller controller, double width, double height) {
		super(controller, width, height);
	}

	public void update(CommandHistory history) {
		
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			this.getController().putScript(newVal);
		};
	}

	@Override
	String getLabelString() {
		return this.getLabelReader().getLabel("CommandHistory");
	}

}

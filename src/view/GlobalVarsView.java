package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;

public class GlobalVarsView extends EnvironmentListView {
	
	public GlobalVarsView(Controller controller, double width, double height) {
		super(controller, width, height);
	}
	
	@Override
	public String getLabelString() {
		return this.getLabelReader().getLabel("Variables");
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			this.getController().getMainView().getEditor().appendText(newVal);
		};
	}
	
}

package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import model.StackFrame;

public class GlobalVarsView extends EnvironmentListView {
	
	private static final String LABEL = "Variables";
	
	public GlobalVarsView(Controller controller, double width, double height) {
		super(controller, width, height);
	}

	public void update(StackFrame vars) {
		
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			this.getController().getMainView().getEditor().appendText(newVal);
		};
	}

	@Override
	String getLabelString() {
		return LABEL;
	}
	
}

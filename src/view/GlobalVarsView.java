package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import model.GlobalVariables;

public class GlobalVarsView extends EnvironmentListView {
	
	public GlobalVarsView(Controller controller, double width, double height) {
		super(controller, width, height);
	}

	public void update(GlobalVariables vars) {
		
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			this.getController().getMainView().getEditor().appendText(newVal);
		};
	}

	@Override
	String getLabelString() {
		return this.getLabelReader().getLabel("Variables");
	}
	
}

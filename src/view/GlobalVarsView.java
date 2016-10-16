package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import model.GlobalVars;

public class GlobalVarsView extends EnvironmentListView {
	
	private static final String LABEL = "Variables";
	
	public GlobalVarsView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
	}

	public void update(GlobalVars vars) {
		
	}

	@Override
	protected ChangeListener<String> getChangeListener() {
		return (ov, oldVal, newVal) -> {
			System.out.println("Variable selected: " + newVal);
		};
	}

	@Override
	String getLabelString() {
		return LABEL;
	}
	
}

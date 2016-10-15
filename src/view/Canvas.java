package view;

import controller.Controller;
import javafx.scene.Node;
import model.TurtleLog;

public class Canvas extends View {
	
	public Canvas(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
	}

	public void render(TurtleLog log) {
		
	}

	@Override
	public Node getUI() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

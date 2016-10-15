package view;

import controller.Controller;
import javafx.scene.Node;

public interface Displayable {
	
	void init(Controller controller, double x, double y, double width, double height);
	Node getUI();
	
}

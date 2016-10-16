package view;

import controller.Controller;
import javafx.scene.Node;

public interface Displayable {
	
	//TODO: get rid of this x and y, not needed
	void init(Controller controller, double x, double y, double width, double height);
	Node getUI();
	
}

package view;

import javafx.scene.Node;

public interface Displayable {
	
	void init(double x, double y, double width, double height);
	Node getUI();
	
}

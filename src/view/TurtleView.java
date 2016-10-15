package view;

import controller.Controller;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView extends View {
	
	public TurtleView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
	}

	private ImageView turtleImage;
	
	public void setImage(Image image) {
		turtleImage.setImage(image);
	}
	
	public void setPositionX(double x) {
		turtleImage.setX(x);
	}
	
	public void setPositionY(double y) {
		turtleImage.setY(y);
	}
	
	public void setDirection(double degrees) {
		turtleImage.setRotate(degrees);
	}

	@Override
	public Node getUI() {
		return turtleImage;
	}

}

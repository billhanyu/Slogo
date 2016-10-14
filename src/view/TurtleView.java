package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView implements Displayable {
	
	private ImageView turtleImage;
	
	@Override
	public void init(double x, double y, double width, double height) {
		// TODO Auto-generated method stub
	}
	
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

package view;

import controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView extends View {
	
	private static final String DEFAULT_IMAGEFILE = "turtle.gif"; 
	private ImageView turtleImage;
	
	public TurtleView(Controller controller, double x, double y, double width, double height) {
		this(controller, width, height);
		setPositionX(x);
		setPositionY(y);
	}
	
	public TurtleView(Controller controller, double width, double height) {
		super(controller, width, height);
		turtleImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGEFILE)));
		turtleImage.setFitWidth(width);
		turtleImage.setFitHeight(height);
		this.getRoot().getChildren().add(turtleImage);
	}
	
	public void setImage(Image image) {
		turtleImage.setImage(image);
	}
	
	public void setPositionX(double x) {
		turtleImage.setX(x - this.getWidth() / 2);
	}
	
	public void setPositionY(double y) {
		turtleImage.setY(y - this.getHeight() / 2);
	}
	
	public void setDirection(double degrees) {
		turtleImage.setRotate(degrees);
	}
	
	public void setVisible(boolean visible) {
		turtleImage.setVisible(visible);
	}
	
}

package view;

import controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView extends View {
	
	private static final String DEFAULT_IMAGEFILE = "turtle.gif"; 
	private ImageView turtleImageView;
	
	public TurtleView(Controller controller, 
			double x, 
			double y, 
			double width, 
			double height,
			double degrees) {
		this(controller, width, height);
		setPositionX(x);
		setPositionY(y);
		setDirection(degrees);
	}
	
	public TurtleView(Controller controller, double width, double height) {
		super(controller, width, height);
		turtleImageView = new ImageView();
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGEFILE));
		setImage(image);
		this.getRoot().getChildren().add(turtleImageView);
	}
	
	public void setImage(Image image) {
		turtleImageView.setImage(image);
		turtleImageView.setFitWidth(this.getWidth());
		turtleImageView.setFitHeight(this.getHeight());
	}
	
	public void setPositionX(double x) {
		turtleImageView.setX(x - this.getWidth() / 2);
	}
	
	public void setPositionY(double y) {
		turtleImageView.setY(y - this.getHeight() / 2);
	}
	
	public void setDirection(double degrees) {
		turtleImageView.setRotate(degrees);
	}
	
	public void setVisible(boolean visible) {
		turtleImageView.setVisible(visible);
	}
	
	public ImageView getImageView() {
		return turtleImageView;
	}
	
}

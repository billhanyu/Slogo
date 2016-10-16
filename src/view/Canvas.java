package view;

import controller.Controller;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.TurtleLog;

public class Canvas extends View {
	
	private TurtleView turtleView;
	private Rectangle background;
	private double turtleWidth = 40;
	private double turtleHeight = 40;
	private static final double CANVAS_WIDTH = 500;
	private static final double CANVAS_HEIGHT = 500;
	
	public Canvas(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
		turtleView = new TurtleView(controller, translateX(0), translateY(0), turtleWidth, turtleHeight);
		background = new Rectangle(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		this.getRoot().getChildren().addAll(background, turtleView.getUI());
	}

	public void render(TurtleLog log) {
		
	}
	
	public void setBackgroundColor(Color color) {
		background.setFill(color);
	}
	
	private double translateX(double x) {
		return x + CANVAS_WIDTH / 2;
	}
	
	private double translateY(double y) {
		return y + CANVAS_HEIGHT / 2;
	}
	
}

package view;

import controller.Controller;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import model.ActorState;
import model.TurtleLog;
import model.TurtleState;

public class Canvas extends View {
	
	private TurtleView turtleView;
	private Rectangle background;
	private TurtleState currentState;
	private double turtleWidth = 20;
	private double turtleHeight = 20;
	private static final double CANVAS_WIDTH = 500;
	private static final double CANVAS_HEIGHT = 500;
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	
	public Canvas(Controller controller, double width, double height) {
		super(controller, width, height);
		currentState = new TurtleState();
		turtleView = new TurtleView(controller, translateX(0), translateY(0), turtleWidth, turtleHeight);
		background = new Rectangle(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		background.setFill(BACKGROUND_COLOR);
		this.getRoot().getChildren().addAll(background, turtleView.getUI());
	}

	public void render(TurtleLog log) {
		for (ActorState nextState : log) {
			if (nextState instanceof TurtleState) {
				TurtleState next = (TurtleState) nextState;
				turtleView.setPositionX(translateX(next.getPositionX()));
				turtleView.setPositionY(translateY(next.getPositionY()));
				turtleView.setDirection(next.getHeading());
				turtleView.setVisible(next.isVisible());
				if (currentState.isPenDown()) {
					addPath(next);
				}
				currentState = next;
			}
		}
	}
	
	public TurtleView getTurtleView() {
		return turtleView;
	}
	
	public void setBackgroundColor(Color color) {
		background.setFill(color);
	}
	
	public void setPenColor(Color color) {
		currentState.setPenColor(color);
	}
	
	public TurtleState getCurrentState() {
		return currentState;
	}
	
	private double translateX(double x) {
		return x + CANVAS_WIDTH / 2;
	}
	
	private double translateY(double y) {
		return y + CANVAS_HEIGHT / 2;
	}
	
	private void addPath(TurtleState nextState) {
		Path path = new Path();

		MoveTo moveTo = new MoveTo();
		moveTo.setX(translateX(currentState.getPositionX()));
		moveTo.setY(translateY(currentState.getPositionY()));

		LineTo lineTo = new LineTo();
		lineTo.setX(translateX(nextState.getPositionX()));
		lineTo.setY(translateY(nextState.getPositionY()));

		path.getElements().add(moveTo);
		path.getElements().add(lineTo);
		path.setFill(currentState.getPenColor());
		this.getRoot().getChildren().add(path);
	}
	
}

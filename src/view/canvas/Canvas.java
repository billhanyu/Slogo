package view.canvas;

import java.awt.Point;
import java.util.Iterator;

import controller.Controller;
import exception.OutOfBoundsException;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import model.ActorState;
import model.TurtleLog;
import model.TurtleState;
import view.TurtleView;
import view.View;

public class Canvas extends View {

	private TurtleView turtleView;
	private Rectangle background;
	private ActorState currentState;
	private double turtleWidth = 20;
	private double turtleHeight = 20;
	private static final double CANVAS_WIDTH = 500;
	private static final double CANVAS_HEIGHT = 500;
	public static final Color BACKGROUND_COLOR = Color.WHITE;

	public Canvas(Controller controller, double width, double height) {
		super(controller, width, height);
		currentState = new TurtleState();
		turtleView = new TurtleView(controller, 
				translateX(0), 
				translateY(0), 
				turtleWidth, 
				turtleHeight,
				currentState.getHeading());
		background = new Rectangle(0, 0, getCanvasWidth(), getCanvasHeight());
		background.setId("canvas");
		this.getRoot().getChildren().addAll(background, turtleView.getUI());
	}

	public void render(TurtleLog log) throws OutOfBoundsException {
		boolean first = false;
		for (ActorState next : log) {
			if (!first) {
				first = true;
				continue;
			}
			if (!inCanvasBounds(translateX(next.getPositionX()), translateY(next.getPositionY()))){
					log.noRender();
					throw new OutOfBoundsException();
			}
			else{
				Point nextPoint = findNextPoints(next);
				turtleView.setPositionX(nextPoint.getX());
				turtleView.setPositionY(nextPoint.getY());
				turtleView.setDirection(next.getHeading());
				turtleView.setVisible(next.isVisible());
				if (next.clearsScreen()) {
					clearScreen();
					next.setClearScreen(false);
				}
				else if (currentState.isPenDown()) {
					addPath(next);
				}
				currentState = next;
			}
		}
		log.didRender();
	}	
	
	public Point findNextPoints(ActorState next){
		Point nextPoint = new Point();
		nextPoint.setLocation(translateX(next.getPositionX()), translateY(next.getPositionY()));
		return nextPoint;
	}
	
	public TurtleView getTurtleView() {
		return turtleView;
	}
	
	public boolean inCanvasBounds(double xPos, double yPos){
		return (xPos <= getCanvasWidth() && xPos >= 0 
			&& yPos <= getCanvasHeight() && yPos >= 0);
	}
	
	public void setBackgroundColor(Color color) {
		background.setFill(color);
	}

	public void setPenColor(Color color) {
		currentState.setPenColor(color);
	}

	public ActorState getCurrentState() {
		return currentState;
	}

	protected double translateX(double x) {
		return x + getCanvasWidth() / 2;
	}

	protected double translateY(double y) {
		return y + getCanvasHeight() / 2;
	}

	private void addPath(ActorState nextState) {
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
		path.setStroke(currentState.getPenColor());
		this.getRoot().getChildren().add(path);
	}
	
	private void clearScreen() {
		for (Iterator<Node> iter = this.getRoot().getChildren().listIterator(); 
				iter.hasNext(); ) {
		    Node node = iter.next();
		    if (node instanceof Path) {
		        iter.remove();
		    }
		}
	}

	public static double getCanvasWidth() {
		return CANVAS_WIDTH;
	}

	public static double getCanvasHeight() {
		return CANVAS_HEIGHT;
	}


}

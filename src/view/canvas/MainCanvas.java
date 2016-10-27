package view.canvas;

import java.awt.Point;
import java.util.Iterator;

import controller.Controller;
import exception.OutOfBoundsException;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.ActorState;
import model.TurtleLog;
import model.TurtleState;
import view.TurtleView;
import view.View;

public class MainCanvas extends View {

	private TurtleView turtleView;
	private Canvas background;
	private ActorState currentState;
	private double turtleWidth = 20;
	private double turtleHeight = 20;
	private Duration animateSpeed = Duration.seconds(2.5);
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	

	public MainCanvas(Controller controller, double width, double height) {
		super(controller, width, height);
		currentState = new TurtleState();
		turtleView = new TurtleView(controller, 
				translateX(0), 
				translateY(0), 
				turtleWidth, 
				turtleHeight,
				currentState.getHeading());
		initCanvas();
	}
	
	private void initCanvas(){
		this.getRoot().getChildren().removeAll(this.getRoot().getChildren());
		background = new Canvas(getCanvasWidth(), getCanvasHeight());
		background.setId("canvas");
		setBackgroundColor(BACKGROUND_COLOR);
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
				doRotation(next.getHeading());
				turtleView.setVisible(next.isVisible());
				doMovement(next);
				if (next.clearsScreen()) {
					clearScreen();
					next.setClearScreen(false);
				}
				currentState = next;
			}
		}
		log.didRender();
		this.notifySubscribers();
	}	
	
	protected Point findNextPoints(ActorState next){
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
		GraphicsContext gc= background.getGraphicsContext2D();
		gc.setFill(color);
		gc.fillRect(0, 0, getCanvasWidth(),getCanvasHeight());
	}

	public void setPenColor(Color color) {
		currentState.getPen().setColor(color);;
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

	private void doMovement(ActorState nextState) {
		if (getDuration().toMillis() == 0.0){
			turtleView.setPositionX(translateX(nextState.getPositionX()));
			turtleView.setPositionY(translateY(nextState.getPositionY()));
			if (currentState.getPen().isDown()){
				addPath(nextState);
			}
		}
		else{
			Point currentPos = new Point(), nextPos = new Point();
			currentPos.setLocation(translateX(currentState.getPositionX()), translateY(currentState.getPositionY()));
			nextPos.setLocation(translateX(nextState.getPositionX()), translateY(nextState.getPositionY()));
			AnimatedPath pathDrawn = new AnimatedPath(currentPos, nextPos);
			if (currentPos.distance(nextPos)!=0){
				pathDrawn.createPathAnimation(getDuration(), background.getGraphicsContext2D(), 
													currentState, turtleView).play();
			}
		}

	}
	
	
	private void doRotation(double degrees){
		if (getDuration().toMillis() == 0.0){
			turtleView.setDirection(degrees);
		}
		else{
			AnimatedPath.createRotationAnimation(getDuration(), background.getGraphicsContext2D(), 
					turtleView, degrees).play();
		}
	}
	
	private void clearScreen() {
		initCanvas();
	}

	public double getCanvasWidth() {
		return this.getWidth();
	}

	public double getCanvasHeight() {
		return this.getHeight();
	}
	
	public void setDuration(double seconds){
		animateSpeed = new Duration(seconds);
	}
	
	public Duration getDuration(){
		return animateSpeed;
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
		path.setFill(currentState.getPen().getColor());
		path.setStroke(currentState.getPen().getColor());
		path.setStrokeWidth(currentState.getPen().getThickness());
		
		//pen type
		switch (currentState.getPen().getType()) {
		case Solid:
			break;
		case Dashed:
			path.getStrokeDashArray().addAll(10d, 10d);
			break;
		case Dotted:
			path.getStrokeDashArray().addAll(2d, 2d);
			break;
		}
		
		this.getRoot().getChildren().add(path);
	}


}

package view.workspace.canvas;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import controller.Controller;
import exception.OutOfBoundsException;
import javafx.animation.SequentialTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.ActorState;
import model.LogHolder;
import model.TurtleLog;
import view.workspace.View;

public class MainCanvas extends View {

	private Map<Integer, TurtleView> turtleTrackers;
	private Map<Integer, TurtleView> turtleViews;
	private Canvas background;
	private Map<Integer, ActorState> currentStates;
	private LogHolder log;

	private double turtleWidth = 20;
	private double turtleHeight = 20;
	private Duration totalAnimationSpeed = Duration.seconds(1);
	private Duration singleAnimationSpeed;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	private AnimatedMovement movement;
	private SequentialTransition transitions;

	public MainCanvas(Controller controller, double width, double height) {
		super(controller, width, height);
		log = controller.getLogHolder();
		currentStates = new HashMap<>();
		updateCurrentStates();
		turtleTrackers = new HashMap<>();
		turtleViews = new HashMap<>();
		updateTurtleViewsAndTrackers();
		initCanvas();
		movement = new AnimatedMovement(this);
		transitions = new SequentialTransition();
	}

	public void render() throws OutOfBoundsException {
		boolean first = false;
		transitions = new SequentialTransition();
		
		singleAnimationSpeed = new Duration((totalAnimationSpeed.toSeconds() / log.size())*1000);
		for (int activeID : log.getActiveIDs()) {
			TurtleLog activelog = log.getTurtleLog(activeID);
			ActorState currentState = currentStates.get(activeID);
			for (ActorState next : activelog) {
				if (!first) {
					first = true;
					continue;
				}
				if (!inCanvasBounds(translateX(next.getPositionX()), translateY(next.getPositionY()))){
					activelog.noRender();
				}
				else {
					movement.setStates(currentState, next);
					TurtleView turtleView = turtleViews.get(activeID);
					TurtleView turtleTracker = turtleTrackers.get(activeID);
					doRotation(next.getHeading(), currentState, 
							turtleView, turtleTracker);
					turtleView.setVisible(next.isVisible());
					doMovement(currentState, next, turtleView, turtleTracker);
					if (next.clearsScreen()) {
						clearScreen();
						next.setClearScreen(false);
					}
					currentState = next;
					currentStates.put(activeID, next);
				}
			}
			activelog.didRender();
		}
		transitions.play();
		this.notifySubscribers();
	}

	private void updateCurrentStates() {
		currentStates.clear();
		for (int key : this.getController().getLogHolder().getActiveIDs()) {
			currentStates.put(key, 
					this.getController().getLogHolder().getTurtleLog(key).peekLast());
		}
	}

	private void updateTurtleViewsAndTrackers() {
		for (int id : currentStates.keySet()) {
			ActorState currentState = currentStates.get(id);
			TurtleView turtleView = new TurtleView(
					this.getController(), 
					translateX(0), 
					translateY(0), 
					turtleWidth, 
					turtleHeight,
					currentState.getHeading());
			TurtleView turtleTracker = new TurtleView(
					this.getController(), 
					translateX(0), 
					translateY(0), 
					turtleWidth, 
					turtleHeight,
					currentState.getHeading());
			turtleViews.put(id, turtleView);
			turtleTrackers.put(id, turtleTracker);
		}
	}

	private void initCanvas(){
		this.getRoot().getChildren().removeAll(this.getRoot().getChildren());
		background = new Canvas(getCanvasWidth(), getCanvasHeight());
		background.setId("canvas");
		setBackgroundColor(log.getWorkspaceState().getBackgroundColor());
		this.getRoot().getChildren().add(background);
		this.getRoot().getChildren().addAll(
				turtleViews.values()
				.stream()
				.map(view -> view.getUI())
				.collect(Collectors.toList()));
	}	

	protected Point findNextPoints(ActorState next){
		Point nextPoint = new Point();
		nextPoint.setLocation(translateX(next.getPositionX()), translateY(next.getPositionY()));
		return nextPoint;
	}

	public Map<Integer, TurtleView> getTurtleViews() {
		return turtleViews;
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
		for (TurtleLog activelog : log.getActiveLogs()) {
			activelog.peekLast().getPen().setColor(color);
		}
	}

	public Map<Integer, ActorState> getCurrentStates() {
		return currentStates;
	}

	protected double translateX(double x) {
		return x + getCanvasWidth() / 2;
	}

	protected double translateY(double y) {
		return y + getCanvasHeight() / 2;
	}

	private void doMovement(
			ActorState currentState, 
			ActorState nextState, 
			TurtleView turtleView,
			TurtleView turtleTracker) {
		if (getDuration().toMillis() == 0.0){
			
			turtleView.setPositionX(translateX(nextState.getPositionX()));
			turtleView.setPositionY(translateY(nextState.getPositionY()));
			if (currentState.getPen().isDown()){
				addPath(currentState, nextState);
			}
		}
		else {
			Point currentPos = new Point(), nextPos = new Point();
			currentPos.setLocation(translateX(currentState.getPositionX()), translateY(currentState.getPositionY()));
			nextPos.setLocation(translateX(nextState.getPositionX()), translateY(nextState.getPositionY()));
			if (currentPos.distance(nextPos)!=0){
				transitions.getChildren().add(movement.createPathAnimation(totalAnimationSpeed, background.getGraphicsContext2D(), 
													turtleView, turtleTracker));

				movement.createPathAnimation(getDuration(), background.getGraphicsContext2D(), 
						turtleView, turtleTracker).play();
			}
		}
	}

	private void doRotation(double degrees, ActorState currentState,
			TurtleView turtleView, TurtleView turtleTracker) {
		if (getDuration().toMillis() == 0.0){
			turtleView.setDirection(degrees);
		}
		else if (!(currentState.getHeading() == degrees)){
			transitions.getChildren().add(movement.createRotationAnimation(totalAnimationSpeed, background.getGraphicsContext2D(), 
					turtleView, turtleTracker, degrees));
			turtleTracker.setDirection(degrees);
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
		totalAnimationSpeed = new Duration(seconds);
	}

	public Duration getDuration(){
		return singleAnimationSpeed;
	}

	public AnimatedMovement getAnimatedMovement(){
		return movement;
	}
	
	public void pauseAnimation(){
		transitions.pause();
	}
	
	public void playAnimation(){
		transitions.play();
	}
	
	public void stopAnimation(){
		transitions.stop();
	}
	
	private void addPath(ActorState currentState, ActorState nextState) {
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

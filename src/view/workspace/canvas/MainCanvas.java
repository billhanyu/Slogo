package view.workspace.canvas;

import java.awt.Point;

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
import model.TurtleLog;
import model.TurtleState;
import view.workspace.View;

public class MainCanvas extends View {

	private TurtleView turtleView;
	private TurtleView turtleTracker;
	private Canvas background;
	private ActorState currentState;
	private double turtleWidth = 20;
	private double turtleHeight = 20;
	private Duration totalAnimationSpeed = Duration.seconds(1);
	private Duration singleAnimationSpeed;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	private Duration animateSpeed = Duration.seconds(2.5);
	private AnimatedMovement movement;

	public MainCanvas(Controller controller, double width, double height) {
		super(controller, width, height);
		currentState = new TurtleState();
		turtleView = new TurtleView(controller, 
				translateX(0), 
				translateY(0), 
				turtleWidth, 
				turtleHeight,
				currentState.getHeading());
		turtleTracker = new TurtleView(controller, 
				translateX(0), 
				translateY(0), 
				turtleWidth, 
				turtleHeight,
				currentState.getHeading());
		initCanvas();
		movement = new AnimatedMovement(this);
		transitions = new SequentialTransition();
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
		transitions = new SequentialTransition();
		
		singleAnimationSpeed = new Duration((totalAnimationSpeed.toSeconds()/log.size())*1000);
		for (ActorState next : log) {
			if (!first) {
				first = true;
				continue;
			}
			if (!inCanvasBounds(translateX(next.getPositionX()), translateY(next.getPositionY()))){
					log.noRender();
					throw new OutOfBoundsException();
			}
			else {
				Point nextPoint = findNextPoints(next);
				movement.setStates(currentState, next);
				doRotation(next.getHeading());
				turtleView.setVisible(next.isVisible());
				doMovement(next);
				if (next.clearsScreen()) {
					clearScreen();
					next.setClearScreen(false);
				}
				else {
					movement.setStates(currentState, next);
					TurtleView turtleView = turtleViews.get(activeID);
					doRotation(next.getHeading(), turtleView);
					turtleView.setVisible(next.isVisible());
					doMovement(currentState, next, turtleView);
					if (next.clearsScreen()) {
						clearScreen();
						next.setClearScreen(false);
					}
					currentState = next;
					currentStates.put(activeID, next);
				}
			}
		}
		transitions.play();
		log.didRender();
		this.notifySubscribers();
=======
	}

	private void updateCurrentStates() {
		currentStates.clear();
		for (int key : this.getController().getLogHolder().getActiveIDs()) {
			currentStates.put(key, 
					this.getController().getLogHolder().getTurtleLog(key).peekLast());
		}
	}

	private void updateTurtleViews() {
		for (int id : currentStates.keySet()) {
			ActorState currentState = currentStates.get(id);
			TurtleView turtleView = new TurtleView(
					this.getController(), 
					translateX(0), 
					translateY(0), 
					turtleWidth, 
					turtleHeight,
					currentState.getHeading());
			turtleViews.put(id, turtleView);
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
>>>>>>> parent of 5a58655... Revert "make background and language update workspacestate":src/view/canvas/MainCanvas.java
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
			if (currentPos.distance(nextPos)!=0){
				transitions.getChildren().add(movement.createPathAnimation(totalAnimationSpeed, background.getGraphicsContext2D(), 
													turtleView, turtleTracker));
			}
		}

	}
	
	
	private void doRotation(double degrees){
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

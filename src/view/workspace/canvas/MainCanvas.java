package view.workspace.canvas;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import controller.Controller;
import exception.OutOfBoundsException;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.ActorState;
import model.LogHolder;
import model.TurtleLog;
import model.WorkspaceState;
import view.floating.Publisher;
import view.floating.Subscriber;
import view.workspace.View;

/**
 * @author Chalena Scholl
 */
public class MainCanvas extends View implements Subscriber {

	private Map<Integer, TurtleView> turtleTrackers;
	private Map<Integer, TurtleView> turtleViews;
	private Canvas background;
	private VBox holder;
	private Map<Integer, ActorState> currentStates;
	private LogHolder log;

	private double turtleWidth = 20;
	private double turtleHeight = 20;
	private Duration animationSpeed = Duration.millis(500);

	private AnimatedMovement movement;
	private TransitionManager transitionManager;

	public static final Color BACKGROUND_COLOR = Color.WHITE;

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
		transitionManager = new TransitionManager();
		log.getWorkspaceState().addSubscriber(this);
		updateActiveTurtles();
	}

	public void render() throws OutOfBoundsException {
		transitionManager.clear();
		for (int renderID : log.getAllIDs()) {
			TurtleLog activelog = log.getTurtleLog(renderID);
			ActorState currentState = currentStates.get(renderID);
			boolean first = false;
			for (ActorState next : activelog) {
				if (!first) {
					first = true;
					if (currentState == null) {
						addNewTurtle(renderID, activelog);
					}
					continue;
				}
				if (!inCanvasBounds(translateX(next.getPositionX()), translateY(next.getPositionY()))){
					activelog.noRender();
				}
				else {
					movement.setStates(currentState, next);
					TurtleView turtleView = turtleViews.get(renderID);
					TurtleView turtleTracker = turtleTrackers.get(renderID);
					doRotation(renderID, next.getHeading(), currentState, 
							turtleView, turtleTracker);
					turtleView.setVisible(next.isVisible());
					doMovement(renderID, currentState, next, turtleView, turtleTracker);
					if (next.clearsScreen()) {
						clearScreen();
						next.setClearScreen(false);
					}
					currentState = next;
					currentStates.put(renderID, next);
				}
			}
			activelog.didRender();
		}
		updateActiveTurtles();
		transitionManager.play();
		this.notifySubscribers();
	}

	public Map<Integer, TurtleView> getTurtleViews() {
		return turtleViews;
	}

	public boolean inCanvasBounds(double xPos, double yPos){
		return (xPos <= getCanvasWidth() && xPos >= 0 
				&& yPos <= getCanvasHeight() && yPos >= 0);
	}

	public void setBackgroundColor(Color color) {
		holder.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public Map<Integer, ActorState> getCurrentStates() {
		return currentStates;
	}
	
	public double getCanvasWidth() {
		return this.getWidth();
	}

	public double getCanvasHeight() {
		return this.getHeight();
	}

	public void setDuration(double milliseconds) {
		animationSpeed = new Duration(milliseconds);
	}

	public Duration getDuration() {
		return animationSpeed;
	}

	public AnimatedMovement getAnimatedMovement() {
		return movement;
	}

	public void pauseAnimation() {
		transitionManager.pause();
	}

	public void playAnimation() {
		transitionManager.play();
	}

	public void stopAnimation(){
		transitionManager.stop();
	}

	protected double translateX(double x) {
		return x + getCanvasWidth() / 2;
	}

	protected double translateY(double y) {
		return y + getCanvasHeight() / 2;
	}
	
	protected Point findNextPoints(ActorState next){
		Point nextPoint = new Point();
		nextPoint.setLocation(translateX(next.getPositionX()), translateY(next.getPositionY()));
		return nextPoint;
	}
	
	private void addNewTurtle(int activeID, TurtleLog log) {
		TurtleView turtleView = zeroedTurtleView();
		TurtleView turtleTracker = zeroedTurtleView();
		this.getRoot().getChildren().add(turtleView.getUI());
		turtleViews.put(activeID, turtleView);
		turtleTrackers.put(activeID, turtleTracker);
		currentStates.put(activeID, log.peekLast());
	}

	private void updateCurrentStates() {
		currentStates.clear();
		for (int key : log.getActiveIDs()) {
			currentStates.put(key, log.getTurtleLog(key).peekLast());
		}
	}

	private void updateTurtleViewsAndTrackers() {
		for (int id : currentStates.keySet()) {
			TurtleView turtleView = zeroedTurtleView();
			TurtleView turtleTracker = zeroedTurtleView();
			turtleViews.put(id, turtleView);
			turtleTrackers.put(id, turtleTracker);
		}
	}

	private TurtleView zeroedTurtleView() {
		return new TurtleView(
				this.getController(), 
				translateX(0), 
				translateY(0), 
				turtleWidth, 
				turtleHeight,
				0);
	}

	private void initCanvas(){
		this.getRoot().getChildren().removeAll(this.getRoot().getChildren());
		background = new Canvas(getCanvasWidth(), getCanvasHeight());
		background.setId("canvas");
		holder = new VBox();
		holder.setPrefSize(getCanvasWidth(), getCanvasHeight());
		holder.getChildren().add(background);
		setBackgroundColor(log.getWorkspaceState().getBackgroundColor());
		this.getRoot().getChildren().add(holder);
		this.getRoot().getChildren().addAll(
				turtleViews.values()
				.stream()
				.map(view -> view.getUI())
				.collect(Collectors.toList()));
	}	

	private void doMovement(
			int id,
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
			double distance = 
					Math.pow(
							Math.pow(currentState.getPositionX() - nextState.getPositionX(), 2) +
							Math.pow(currentState.getPositionY() - nextState.getPositionY(), 2),
							0.5
							);
			if (distance != 0) {
				transitionManager.get(id).getChildren().add(
						movement.createPathAnimation(
								animationSpeed, background.getGraphicsContext2D(), turtleView, turtleTracker));
			}
		}
	}

	private void doRotation(int id, double degrees, ActorState currentState,
			TurtleView turtleView, TurtleView turtleTracker) {
		if (getDuration().toMillis() == 0.0){
			turtleView.setDirection(degrees);
		}
		else if (!(currentState.getHeading() == degrees)){
			transitionManager.get(id).getChildren().add(
					movement.createRotationAnimation(
							animationSpeed, background.getGraphicsContext2D(), turtleView, turtleTracker, degrees));
			turtleTracker.setDirection(degrees);
		}
	}

	private void clearScreen() {
		initCanvas();
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
	
	private void updateActiveTurtles() {
		DropShadow ds = new DropShadow(20, Color.AQUA);
		for (int id : turtleViews.keySet()) {
			if (log.getActiveIDs().contains(id)) {
				turtleViews.get(id).getImageView().setEffect(ds);
			}
			else {
				turtleViews.get(id).getImageView().setEffect(null);
			}
		}
	}

	@Override
	public void didUpdate(Publisher target) {
		if (target instanceof WorkspaceState) {
			this.setBackgroundColor(((WorkspaceState) target).getBackgroundColor());
		}
	}

}

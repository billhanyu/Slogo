package view.canvas;

import java.awt.Point;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.ActorState;
import view.TurtleView;

public class AnimatedMovement {
	private MainCanvas actingOn;
	private RotateTransition rotation;
	private PathTransition pathMovement;
	private ActorState currentState;
	private ActorState nextState;
	
	
	public AnimatedMovement(MainCanvas actingOn) {
		rotation = new RotateTransition();
		pathMovement = new PathTransition();
		this.actingOn = actingOn;
	}

	public Path createPath() {
		Path path = new Path();

		MoveTo moveTo = new MoveTo();
		moveTo.setX(actingOn.translateX(currentState.getPositionX()));
		moveTo.setY(actingOn.translateY(currentState.getPositionY()));

		LineTo lineTo = new LineTo();
		lineTo.setX(actingOn.translateX(nextState.getPositionX()));
		lineTo.setY(actingOn.translateY(nextState.getPositionY()));

		path.getElements().addAll(moveTo, lineTo);
		return path;
	}
	
	
    public Animation createPathAnimation(Duration duration, GraphicsContext graphics, TurtleView turtle, TurtleView turtleTracker) {
        
    	Circle pen = new Circle(0, 0, 20);
        pathMovement = new PathTransition(duration, createPath(), pen);
        pathMovement.currentTimeProperty().addListener( new ChangeListener<Duration>() {

            Point oldPosition = null;
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                if( oldValue != Duration.ZERO){
                	
	                double x = pen.getTranslateX();
	                double y = pen.getTranslateY();
	                if( oldPosition == null) {
	                	oldPosition = new Point();
	                	updatePositions(x, y, oldPosition, turtle);
	                    return;
	                }
	                if (currentState.getPen().isDown()){
		                graphics.setStroke(currentState.getPen().getColor());
		                graphics.setFill(currentState.getPen().getColor());
		                graphics.setLineWidth(currentState.getPen().getThickness());
		                graphics.strokeLine(oldPosition.getX(), oldPosition.getY(), x, y);
	                }
	                
	        		switch (currentState.getPen().getType()) {
	        		case Solid:
	        			break;
	        		case Dashed:
	        			graphics.setLineDashes(10d, 10d);
	        			break;
	        		case Dotted:
	        			graphics.setLineDashes(2d, 2d);
	        			break;
	        		}
	                updatePositions(x, y, oldPosition, turtle);
                }
            }
        });
        return pathMovement;
    }
    
    public Animation createRotationAnimation(Duration duration, GraphicsContext graphics, TurtleView turtle, TurtleView turtleTracker, double degrees){
    	rotation = new RotateTransition(Duration.millis(3000), turtle.getImageView());
    	rotation.setFromAngle(turtleTracker.getDirection());
    	System.out.println("from: " + turtleTracker.getDirection() + ", to " + degrees);
    	rotation.setToAngle(degrees);
    	rotation.setOnFinished(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			turtle.setDirection(degrees);
    		}
    	});
    	return rotation;

    	
    }
    
    private void updatePositions(double x, double y, Point oldPosition, TurtleView turtle){
    	oldPosition.setLocation(x,  y);
        turtle.setPositionX(x);
        turtle.setPositionY(y);
    }
    
    protected void setStates(ActorState current, ActorState next){
    	currentState = current;
    	nextState = next;
    	
    }
    
    public void pauseAnimation(){
    	pathMovement.pause();
    	rotation.pause();
    }
    
    public void playAnimation(){
    	pathMovement.play();
    	rotation.play();
    }
    
    public void stopAnimation(){
    	pathMovement.stop();
    	rotation.stop();
    }
}

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

public class AnimatedPath {
	Point from;
	Point to;
	double heading;
	
	public AnimatedPath(Point from, Point to){
		this.from = from;
		this.to = to;
	}
	
	public Path createPath() {
		Path path = new Path();

		MoveTo moveTo = new MoveTo();
		moveTo.setX(from.getX());
		moveTo.setY(from.getY());

		LineTo lineTo = new LineTo();
		lineTo.setX(to.getX());
		lineTo.setY(to.getY());

		path.getElements().addAll(moveTo, lineTo);
		return path;
	}
	
	
    public Animation createPathAnimation(Duration duration, GraphicsContext graphics, ActorState currentState, TurtleView turtle) {
        
    	Circle pen = new Circle(0, 0, 20);
        PathTransition pathTransition = new PathTransition(duration, createPath(), pen);
        pathTransition.currentTimeProperty().addListener( new ChangeListener<Duration>() {

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
        return pathTransition;
    }
    
    public static Animation createRotationAnimation(Duration duration, GraphicsContext graphics, TurtleView turtle, double degrees){
    	RotateTransition rt = new RotateTransition(Duration.millis(3000), turtle.getImageView());
    	rt.setToAngle(degrees
    			);
    	rt.setOnFinished(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			turtle.setDirection(degrees);
    		}
    	});
    	return rt;

    	
    }
    
    public void updatePositions(double x, double y, Point oldPosition, TurtleView turtle){
    	oldPosition.setLocation(x,  y);
        turtle.setPositionX(x);
        turtle.setPositionY(y);
    }
}

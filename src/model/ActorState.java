package model;

import javafx.scene.paint.Color;

/**
 * @author billyu
 * API for actor (probably turtle) in the project
 */

public interface ActorState {
	
	void setPositionX(double x);
	void setPositionY(double y);
	void setDirection(double degrees);
	void setPen(boolean isDown);
	void setVisible(boolean isVisible);
	void setAnimate(boolean animate);
	void setPenColor(Color color);
	void setClearScreen(boolean clear);
	double getPositionX();
	double getPositionY();
	double getHeading();
	boolean isPenDown();
	boolean isVisible();
	boolean doesAnimate();
	Color getPenColor();
	boolean clearsScreen();
	void duplicateOnto(ActorState other);
}

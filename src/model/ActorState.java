package model;

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
	double getPositionX();
	double getPositionY();
	double getHeading();
	boolean isPenDown();
	boolean isVisible();
	boolean doesAnimate();
	void duplicateOnto(ActorState other);
}

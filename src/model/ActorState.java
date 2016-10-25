package model;

/**
 * @author billyu
 * API for actor (probably turtle) in the project
 */

public interface ActorState {
	
	void setPositionX(double x);
	void setPositionY(double y);
	void setDirection(double degrees);
	void setVisible(boolean isVisible);
	void setAnimate(boolean animate);
	void setClearScreen(boolean clear);
	void setPen(Pen pen);
	double getPositionX();
	double getPositionY();
	double getHeading();
	boolean isVisible();
	boolean doesAnimate();
	Pen getPen();
	boolean clearsScreen();
	void duplicateOnto(ActorState other);
}

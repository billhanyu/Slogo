package model;

public class Turtle {
	
	private TurtleState myState;
	
	public void setState(TurtleState state) {
		myState = state;
	}
	
	public TurtleState getState() {
		return myState;
	}
}

package view.canvas;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.ActorState;
import model.TurtleLog;

public class ToroidalCanvas extends Canvas{
	
	Line2D upperBound;
	Line2D lowerBound;
	Line2D rightBound;
	Line2D leftBound;

	public ToroidalCanvas(Controller controller, double width, double height) {
		super(controller, width, height);
		setBoundaryLines();
	}
	
	
	public Point findNextPoints(ActorState next){
		//if on boundary, have to draw line through the inside of the box
		Point intersection = null;
		double nextX = translateX(next.getPositionX());
		double nextY = translateY(next.getPositionY());
		double currX = translateX(getCurrentState().getPositionX());
		double currY = translateY(getCurrentState().getPositionY());
		
		if (!inCanvasBounds(nextX, nextY)){//make line in both directions and find intersection
										//make line meaning find x,y points of + canvas_width or canvas_height, whichever is greater
			
			
			Point extendedPoint = calculateNextPoint(getCanvasWidth()*2);
			Line2D extendedLine = new Line2D.Double(currX, currY, extendedPoint.getX(), extendedPoint.getY());
			Line2D intersectionLine = findBoundaryIntersection(extendedLine);
			System.out.println(intersectionLine==null);
			//System.out.println("extended " + extendedLine.getX1() + ", " + extendedLine.getY1() + "   " + extendedLine.getX2() + ", " + extendedLine.getY2());

			//System.out.println("intersection " + intersectionLine.getX1() + ", " + intersectionLine.getY1() + "   " + intersectionLine.getX2() + ", " + intersectionLine.getY2());
			intersection = findIntersectionPoint(extendedLine, intersectionLine);
			//System.out.println("intersection point found is : " + intersection.getX() + ", " + intersection.getY());
			
			
			
		}
		else{
			return super.findNextPoints(next);
		}
		return intersection;
	}
	
	
	private void setBoundaryLines(){
		double canvasWidth = getCanvasWidth();
		double canvasHeight = getCanvasHeight();
		upperBound = new Line2D.Double(0, 0, canvasWidth, 0);
		lowerBound = new Line2D.Double(0, canvasHeight, canvasWidth, canvasHeight);
		leftBound = new Line2D.Double(0, 0, 0, canvasHeight);
		rightBound = new Line2D.Double(canvasWidth, 0, canvasWidth, canvasHeight);
	}
	
	private Point calculateNextPoint(double offset){
		Point nextPoint = new Point();
		
		double nextX = translateX(getCurrentState().getPositionX() + 
				offset * Math.sin((getCurrentState().getHeading()-180) / 180 * Math.PI));
		double nextY = translateY(getCurrentState().getPositionY() - 
				offset * Math.cos((getCurrentState().getHeading()-180) / 180 * Math.PI));
		
		nextPoint.setLocation(nextX, nextY);
		return nextPoint;
	}
	
	
	private Line2D findBoundaryIntersection(Line2D extendedLine){
		double startX = extendedLine.getX1();
		double startY = extendedLine.getY1();
		if (extendedLine.intersectsLine(upperBound) && !lineContains(upperBound, startX, startY)){
			return upperBound;
		}
		else if (extendedLine.intersectsLine(lowerBound) && !lineContains(lowerBound, startX, startY)){
			return lowerBound;
		}
		else if (extendedLine.intersectsLine(rightBound) && !lineContains(rightBound, startX, startY)){
			return rightBound;
		}
		else if (extendedLine.intersectsLine(leftBound)  && !lineContains(leftBound, startX, startY)){
			return leftBound;
		}
		return null;
	}
	
	private boolean lineContains(Line2D toCheck, double x, double y){
		return (toCheck.ptLineDist(x, y) == 0);
	}
	
	
	private Point findIntersectionPoint(Line2D extendedLine, Line2D boundaryLine){
		double denom = (boundaryLine.getX2() - boundaryLine.getX1())*(extendedLine.getY1() - extendedLine.getY2()) 
						- (extendedLine.getX1() - extendedLine.getX2())* (boundaryLine.getY2() - boundaryLine.getY1());
		
		if (denom == 0.0){
			return null;
		}
		double ta = (boundaryLine.getY1() - boundaryLine.getY2())*(extendedLine.getX1() - boundaryLine.getX1()) 
				+ (boundaryLine.getX2() - boundaryLine.getX1())* (extendedLine.getY1() - boundaryLine.getY1());
		
		double tb = (extendedLine.getY1() - extendedLine.getY2())*(extendedLine.getX1() - boundaryLine.getX1()) 
				+ (extendedLine.getX2() - extendedLine.getX1())* (extendedLine.getY1() - boundaryLine.getY1());
		
		ta = ta/denom;
		tb = tb/denom;
		
		if ((0 <= ta && ta <= 1) && (0 <= tb && tb <= 1)){//intersect
			return new Point((int) (extendedLine.getX1() + ta*(extendedLine.getX2() - extendedLine.getX1())), 
								(int) (extendedLine.getY1() + ta*(extendedLine.getY2() - extendedLine.getY1())));
		}
		
		else{
			return null;
		}
		
	}

	

}

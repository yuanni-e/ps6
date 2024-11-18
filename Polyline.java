import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 * @author Tim Pierson Dartmouth CS 10, provided for Winter 2024
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE
	private List<Point> points;		// points (x1, y1), (x2, y2), (x3, y3), ... to be connected
	private Color color;

	public Polyline(Color color){
		points = new ArrayList<Point>();
		this.color = color;
	}

	public Polyline(List<Point> points, Color color){
		this.points = points;
		this.color = color;
	}

	public void addPoint(int x, int y) {
		points.add(new Point(x, y));
	}


	@Override
	public void moveBy(int dx, int dy) {
		for(Point p : points){
			p.move(p.x+dx, p.y+dy);
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean contains(int x, int y) {
		boolean inLine = false;
		for(int i = 0; i < points.size() - 1; i++){
			Segment seg = new Segment(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y, color);
			if(seg.contains(x, y)){
				inLine = true;
			}
		}
		return inLine;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		int[] xVals = new int[points.size()];
		int[] yVals = new int[points.size()];
		for(int i = 0; i < points.size(); i++){
			xVals[i] = (int)points.get(i).getX();
			yVals[i] = (int)points.get(i).getY();
		}
		g.drawPolyline(xVals, yVals, points.size());
	}

	@Override
	public String toString() {
		String p = "polyline ";
		for (Point point : points){
			p += (int)point.getX() + " ";
			p += (int)point.getY() + " ";
		}
		p += color.getRGB();

		return p;
	}
}

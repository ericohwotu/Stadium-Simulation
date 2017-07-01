import java.util.*;
public class Route extends Segment{
	private ArrayList<Segment> segments = new ArrayList<Segment>();
	private double length;
	private Person assignedPerson;
	private Seat assignedSeat;
	
	//consructor for auto route creation
	public Route(Person p, Seat s){
		super((double)p.getCoordX(), (double)p.getCoordY(),(double)s.getCoordX(), (double)s.getCoordY(), 2);
		double lx, ly;
		this.assignedPerson = p;
		this.assignedSeat = s;
		lx = Math.abs(s.getCoordX()-p.getCoordX());
		ly = Math.abs(s.getCoordY()-p.getCoordY());
		this.length = Math.sqrt((lx*lx)+(ly*ly));
	}
	
	//
	public Segment getSegment(int i){
		return segments.get(i);
	}
	
	public int getSegmentCount(){
		return segments.size();
	}
	
	public double getLength(){
		return this.length;
	}
}
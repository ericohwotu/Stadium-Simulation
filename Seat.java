public class Seat{
	private double coordX, coordY, elevation;
	private SeatSection seatSection;
	private static double width = 0.5;
	private static double height = 1;
	private static double length = 0.5;
	
	public Seat(double x, double y, SeatSection s){
		this.coordX = x;
		this.coordY = y;
		this.seatSection = s;
	}
	public Seat(double x, double y, SeatSection s, int e){
		this.coordX = x;
		this.coordY = y;
		this.seatSection = s;
		this.elevation = e;
	}
	
	// static variables for use ith the seat class
	public static double getWidth(){
		return width;
	}
	public static double getHeight(){
		return height;
	}
	public static double getLength(){
		return length;
	}
	
	// public members for the object
	public double getCoordX(){
		return this.coordX;
	}
	public double getCoordY(){
		return this.coordY;
	}
	public SeatSection getSeatSection(){
		return this.seatSection;
	}
	public void displaySeatSectionStats(){
		this.seatSection.displayStats("-->");
	}
}
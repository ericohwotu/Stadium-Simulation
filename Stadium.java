import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Stadium{
	private int width, height, length;
	private SeatArea seatArea;
	private ArrayList<Person> people = new ArrayList<Person>();
	//private EntranceTunnel entranceTunnel;
	private Pitch pitch;
	//private Hallway hallway;
	//private Exterior exterior;
	
	Stadium(){
		int[] outline = {0,0,1000,1000};
		seatArea = new SeatArea(50,outline);
		System.out.println("");
		int pt = 6500;
		for(int i=1; i<=pt; i++){
			System.out.print("\r->> Completed (" + i + " out of "+pt+")");
			Seat s = seatArea.getFreeSeat();
			Person p = new Person(i,(int)(Math.random()*outline[2]),0,2.0,80,1.5,0.3, s);
			people.add(p);
		}
		System.out.println("");
		
		people.get((int)(Math.random()*people.size())).displayStats("***");
		people.get((int)(Math.random()*people.size())).displayStats("***");
		people.get((int)(Math.random()*people.size())).displayStats("***");
		people.get((int)(Math.random()*people.size())).displayStats("***");
		people.get((int)(Math.random()*people.size())).displayStats("***");
		
		seatArea.displayStats();
		System.out.println("\nThere are currently " + seatArea.getSeatCount() + " seats in the stadium.");
	}
	
	Stadium(int pt){
		int[] outline = {0,0,1000,1000};
		seatArea = new SeatArea(50,outline);
		init(pt);
	}
	
	public void init(int pt){
		int[] outline = {0,0,1000,1000};
		//System.out.println("");
		//int pt = 65000;
		for(int i=1; i<=pt; i++){

			Seat s = seatArea.getFreeSeat();
			Person p = new Person(i,(int)(Math.random()*outline[2]),0,2.0,80,1.5,0.3, s);
			people.add(p);
			//jl.setText("Completed (" + i + " out of "+pt+")");
		}
		seatArea.displayStats();
		//jl.setText(seatArea.getSeatCount() + " seats in the stadium.");
	}
	
	public void generatePeople(){
		Person p = new Person(1,0,0,2,80,1.5,0.3, seatArea.getFreeSeat());
	}
	
	public Vector<String> getSectionNames(){
		return seatArea.getSectionNames();
	}
	
	public SeatArea getSeatArea(){
		return this.seatArea;
	}
	
}

class StadiumSection{
	private int width, height, length;
	private int[] outline;
	
	StadiumSection(int h, int[] o){
		this.width = o[2]-o[0];
		this.height = h;
		this.length = o[3]-o[1];
		this.outline = o;
	}
	
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	public int getLength(){
		return this.length;
	}
	public int[] getOutline(){
		return this.outline;
	}
}

class SeatArea extends StadiumSection{
	private int seatCount, floors, freeSeatCount;
	private ArrayList<SeatSection> seatSections = new ArrayList<SeatSection>();
	
	SeatArea(int h, int[] o){
		super(h,o);
		int[] outline1 = {50, 50, 450, 250};
		int[] outline2 = {50, 650, 450, 850};
		int[] outline3 = {0, 250, 50, 650};
		int[] outline4 = {450, 250, 500, 650};
		String name1 = "North Wing";
		String name2 = "South Wing";
		String name3 = "West Wing";
		String name4 = "East Wing";
		//int[] outline5 = {450, 250, 500, 650};
		seatSections.add(new SeatSection(1, 50, outline1, name1));
		seatSections.add(new SeatSection(2, 50, outline2, name2));
		seatSections.add(new SeatSection(3, 50, outline3, name3));
		seatSections.add(new SeatSection(4, 50, outline4, name4));
		calculateSeatCount();
	}
	
	private void calculateSeatCount(){
		//TODO: check the amount of SeatSections in SeatArea
		for(int i = 0; i < seatSections.size(); i++){
			this.seatCount += this.seatSections.get(i).getSeatCount();
		}
	}
	private void calculateFreeSeatCount(){
		this.freeSeatCount = 0;
		for(int i = 0; i < seatSections.size(); i++){
			this.freeSeatCount += this.seatSections.get(i).getFreeSeatCount();
		}
	}
	public void addSeatSection(String n, int i, int x, int y, int l, int w){
		//add the sections to the seating area
		int[] outline = {x,y,x+l,y+w};
		seatSections.add(new SeatSection(i, 50, outline, n));
	}
	public Vector<String> getSectionNames(){
		Vector<String> v = new Vector<String>();
		for(int i = 0; i < seatSections.size(); i++){
			v.addElement(this.seatSections.get(i).getName());
		}
		return v;
	}
	
	public Seat getFreeSeat(){
		int rnd = (int)(Math.random()*(seatSections.size()));
		while (!seatSections.get(rnd).isSeatAvailable()){
			rnd = (int)(Math.random()*(seatSections.size()));
			calculateFreeSeatCount();
			if(this.freeSeatCount ==0){
				return null;
			}
		}
		return seatSections.get(rnd).getFreeSeat();
	}
	
	public int getSeatCount(){
		return this.seatCount;
	}
	
	public void displayStats(){
		for(int i = 0; i < seatSections.size(); i++){
			this.seatSections.get(i).displayStats(">>>");
		}
	}
	
	public ArrayList<SeatSection> getSeatSections(){
		return this.seatSections;
	}
	
	public int getSectionsCount(){
		return this.seatSections.size();
	}
}

class SeatSection extends StadiumSection{
	private ArrayList<Seat> seats = new ArrayList<Seat>();
	private ArrayList<Seat> freeSeats;
	private ArrayList<Seat> assignedSeats = new ArrayList<Seat>();
	private String name;
	private int id;
	
	SeatSection(int i, int h, int[] o, String n){
		super(h,o);
		this.id = i;
		this.name = n;
		setSeats();
		
	}
	
	private void setSeats(){
		//TODO: Implement a way to automatically asign seats
		int seatCount = getSeatCount();
		
		for(double i = super.getOutline()[1]; i<super.getOutline()[3]; i+= Seat.getLength()){ // row count
			for(double j = super.getOutline()[0]; j<super.getOutline()[2]; j+= Seat.getWidth()){ // column count
				seats.add(new Seat(i,j, this)); // add seats to the stadium section
			}
		}
		this.freeSeats = new ArrayList<Seat>(this.seats);
		displayStats("-->");
	}
	
	public int getSeatCount(){
		return seats.size();
	}
	public int getFreeSeatCount(){
		return freeSeats.size();
	}
	public int getAssignedSeatCount(){
		return assignedSeats.size();
	}
	public String getName(){
		return name;
	}
	
	public int getTopX(){
		return super.getOutline()[0];
	}
	public int getTopY(){
		return super.getOutline()[1];
	}
	public int getLength(){
		return Math.abs(super.getOutline()[0]-super.getOutline()[2]);
	}
	public int getWidth(){
		return Math.abs(super.getOutline()[1]-super.getOutline()[3]);
	}
	
	public Seat getFreeSeat(){
		int rnd = (int)(Math.random()*(freeSeats.size()));
		Seat chosenSeat = seats.get(rnd);
		assignedSeats.add(chosenSeat);
		freeSeats.remove(rnd);
		return chosenSeat;
	}
	
	public boolean isSeatAvailable(){
		if(freeSeats.size()==0){
			return false;
		}else{
			return true;
		}
	}
	
	
	public void displayStats(String pre){
		System.out.println("\n" + pre + " " + this.name + " Seat Section Data: ");
		System.out.println(pre + " Seats in section: " + getSeatCount());
		System.out.println(pre + " Free Seats in section: " + getFreeSeatCount());
		System.out.println(pre + " Assigned Seats in section: " + getAssignedSeatCount());
		System.out.println(pre + " Rows in section: " + (int)(super.getLength()/Seat.getLength()));
		System.out.println(pre + " Columns in section: " + (int)(super.getWidth()/Seat.getWidth()));
		//System.out.println(pre + "---------- END OF SECTION -----------");
	}
	
}

class Pitch extends StadiumSection{
	Pitch(int h, int[] o){
		super(h,o);
	}
}
public class Person{
	//private fields
	private int id, coordX, coordY, weight;
	private double velocity, velocityX, velocityY, velocityZ, shoulderSpan, height, travelTime;
	private Seat assignedSeat;
	private Route assignedRoute;

	
	//constructor
	public Person(int i, int x, int y, double v, int w, double h, double s, Seat st){
		this.id = i;
		this.coordX = x;
		this.coordY = y;
		this.velocity = v;
		this.weight = w;
		this.height = h;
		this.shoulderSpan = s;
		this.assignedSeat = st;
		this.assignedRoute = new Route(this, st);
		calculateVelocityComponents(this.assignedSeat.getCoordX(),this.assignedSeat.getCoordY());
	}
	
	// instruct person to move
	public void move(int t){
		//TODO: Implement move method
		calculateCurrentSegment();
		this.coordX += this.velocityX;
		this.coordY += this.velocityY;
	}
	
	public int getCoordX(){
		return this.coordX;
	}
	public int getCoordY(){
		return this.coordY;
	}
	
	//calculate current segment
	private void calculateCurrentSegment(){
		//TODO: Implement calculate segment
		double targetX, targetY, xs, ys, xe, ye;
		
		// check the current segment in the route
		for(int i = 0; i < assignedRoute.getSegmentCount(); i++){
			//get the segment start and end coordinates
			xs = assignedRoute.getSegment(i).getCoordXStart();
			ys = assignedRoute.getSegment(i).getCoordYStart();
			xe = assignedRoute.getSegment(i).getCoordXEnd();
			ye = assignedRoute.getSegment(i).getCoordYEnd();
			//get the target coordinate values
			if((this.coordX == xs)&&(this.coordY == ys)){
				targetX = xe;
				targetY = ye;
				calculateVelocityComponents(targetX, targetY);
			}
		}
	}
	//calculate the x and y velocity components
	private void calculateVelocityComponents(double tx, double ty){
		//TODO: implement velocity calculation components
		//tx and ty represent target x and y coordinates
		double angle, x, y, h; //private function variables
		
		x = Math.abs(tx - coordX); // get x distance
		y = Math.abs(ty - coordY); // get y distance
		h = (x*x) + (y*y); // pythagoras theorem
		h = Math.sqrt(h); 
		angle = Math.atan2(x,y); //calculate angle
		
		this.velocityY = Math.sin(angle)*h; //calculate y velocity
		this.velocityX = Math.cos(angle)*h; //calculate x velocity
	}
	
	//get time prediction
	private double estimateTravelTime(){
		double tx = Math.abs( this.coordX - this.assignedSeat.getCoordX() )/this.velocityX;
		double ty = Math.abs( this.coordY - this.assignedSeat.getCoordY() )/this.velocityY;
		double t = this.assignedRoute.getLength() / this.velocity;
		return t;
	}
	
	public void displayStats(String pre){
		System.out.println("\n" + pre + " Person " + this.id + ": ");
		System.out.println(pre + " Weight: " + this.weight);
		System.out.println(pre + " Height: " + this.height);
		System.out.println(pre + " ETE: " + estimateTravelTime());
		System.out.println(pre + " Position (X,Y): (" + this.coordX + ", " + this.coordY + ")");
		System.out.println(pre + " Velocity (X,Y): (" + this.velocityX + ", " + this.velocityY + ", " + this.velocity + ")");
		System.out.println(pre + " Seat Position (X,Y): (" + this.assignedSeat.getCoordX() + ", " + this.assignedSeat.getCoordY() + ")");
		System.out.println(pre + " Seat Section: " + this.assignedSeat.getSeatSection().getName());
	}
	
}
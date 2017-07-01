public class Segment{
	private double coordXStart, coordYStart, coordXEnd, coordYEnd;
	private int maxVelocity, minVelocity;
	
	public Segment(double xs,double ys,double xe, double ye, int mv){
		this.coordXStart = xs;
		this.coordYStart = ys;
		this.coordXEnd = xe;
		this.coordYEnd = ye;
		this.maxVelocity = mv;
	}
	
	public double getCoordXStart(){
		return this.coordXStart;
	}
	public double getCoordXEnd(){
		return this.coordXEnd;
	}
	public double getCoordYStart(){
		return this.coordYStart;
	}
	public double getCoordYEnd(){
		return this.coordYEnd;
	}
	private void calculateMaxVelocity(){
		//TODO: Implement max velocity calculation
	}
}
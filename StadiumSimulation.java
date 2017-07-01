import java.util.*;
import javax.swing.*;

public class StadiumSimulation{
	public static void main(String args[]){
	
		
		
		System.out.println("Stadium Simulation File: ");
		System.out.println("Version: V17.05.2401");
		System.out.println("Development Version: V0.01");
		System.out.println("Author: Eric Ohwotu");
		System.out.println("Change Log: ");
		System.out.println("--> Currently Implemented Seating Areas With Auto Seat Assignment");
		System.out.println("--> Currently Implemented Persons that calculate their velocity");
		System.out.println("--> Currently Implemented Assign People To Seats");
		System.out.println("--> Currently Implemented Assign Routes To People Based On Starting Position and Assigned Seat");
		//Stadium s = new Stadium();
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new MainFrame();
			}
		});
		
		
	}
}
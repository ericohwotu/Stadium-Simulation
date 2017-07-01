import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

//class that controls the user interface of the stadium simulation.

public class MainFrame{
	private JComboBox<String> sectionNames = new JComboBox<String>();
	private JTextField sectionSeats = new JTextField("Section Seats");
	private JTextField sectionFreeSeats = new JTextField("Free Seats");
	private JTextField sectionAssignedSeats = new JTextField("Assigned Seats");
	private JTextField sectionRowCount = new JTextField("No of Rows");
	private JTextField sectionColumnCount = new JTextField("No of Columns");
	private Stadium stadium = null;
	private Canvas stadiumCanvas = new Canvas();
	
	public MainFrame(){		
		//create a new window for the program
		JFrame mainWindow = new JFrame("Stadium Simulation");
		JPanel windowPanel = new JPanel(new BorderLayout());
		
		//create a panel to host the aboutus, simstart and sim info
		JPanel aboutUs = new JPanel();
		JPanel simControl = new JPanel();
		JPanel simInfo = new JPanel();
		JPanel canvasPanel = new JPanel();
		
		
		getSeatInfoPanel(simInfo);
		
		getSimControlPanel(simControl);
		
		windowPanel.add(simControl, BorderLayout.PAGE_START);
		windowPanel.add(getStadiumCanvas(),BorderLayout.CENTER);
		windowPanel.add(simInfo, BorderLayout.PAGE_END);
		mainWindow.setJMenuBar(getMenuBar());
		mainWindow.add(windowPanel);
		mainWindow.setLayout(new GridLayout(0,1));
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(400,300);
		mainWindow.setVisible(true);
		
	}
	
	private Canvas getStadiumCanvas(){
		this.stadiumCanvas.setSize(600,400);
		return this.stadiumCanvas;
	}
	
	private void drawOnCanvas(){
		
	};

	private JMenuBar getMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");
		
		JMenuItem aboutItem = new JMenuItem("About Us");
		JMenuItem addSectionItem = new JMenuItem("Add Section");
		
		aboutItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showAboutUs();
			}
		});
		
		addSectionItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				showAddSectionDialog();
			}
		});
		
		aboutMenu.add(aboutItem);
		fileMenu.add(addSectionItem);
		menuBar.add(aboutMenu);
		menuBar.add(fileMenu);
		
		return menuBar;
	}
	
	private void showAboutUs(){
		JFrame aboutWindow = new JFrame("About");
		JPanel aboutUs = new JPanel();
		aboutWindow.setSize(600,250);
		aboutWindow.setLayout(new GridLayout(0,1));
		aboutWindow.add(getAboutUsPanel(aboutUs));
		aboutWindow.setVisible(true);
	}
	
	private void showAddSectionDialog(){
		JFrame addSectionWindow = new JFrame("Add Section");
		JPanel addSectionPanel = new JPanel(new GridLayout(0,5));
		JPanel blank = new JPanel();
		addSectionWindow.add(getLabelAddSection());
		addSectionWindow.add(getAddSection(addSectionPanel));
		addSectionWindow.add(blank);
		for (int i=0; i<stadium.getSeatArea().getSectionsCount(); i++){
			addSectionWindow.add(getSectionInfo(i));
		}
		addSectionWindow.setSize(450,300);
		addSectionWindow.setLayout(new GridLayout(0,1));
		addSectionWindow.setVisible(true);
	}
	
	private JPanel getLabelAddSection(){
		JPanel p = new JPanel();
		p.add(new JLabel(" "));
		p.add(new JLabel("Top X Point"));
		p.add(new JLabel("Top Y Point"));
		p.add(new JLabel("Length"));
		p.add(new JLabel("Width"));
		
		p.setLayout(new GridLayout(0,5));
		
		return p;
		
	}
	private JPanel getAddSection(JPanel p){
		JButton addButton = new JButton("Add Section");
		JTextField topX = new JTextField("");
		JTextField topY = new JTextField("");
		JTextField bottomY = new JTextField("");
		JTextField bottomX = new JTextField("");
		SeatArea sa = this.stadium.getSeatArea();
		
		p.add(addButton);
		p.add(topX);
		p.add(topY);
		p.add(bottomX);
		p.add(bottomY);
		
		p.setLayout(new GridLayout(0,5));
		
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sa.addSeatSection("Section "+sa.getSectionsCount(),sa.getSectionsCount()+1,Integer.parseInt(topX.getText()),Integer.parseInt(topY.getText()),Integer.parseInt(bottomX.getText()),Integer.parseInt(bottomY.getText()));
			}
		});
		
		return p;
	}
	private JPanel getSectionInfo(int i){
		JPanel p = new JPanel();
		SeatSection s = this.stadium.getSeatArea().getSeatSections().get(i);
		JButton addButton = new JButton("Add Section");
		JTextField topX = new JTextField("" + s.getTopX());
		JTextField topY = new JTextField("" + s.getTopY());
		JTextField length = new JTextField("" + s.getLength());
		JTextField height = new JTextField("" + s.getWidth());
		JTextField name = new JTextField("" + s.getName());
		name.setEditable(false);
		topX.setEditable(false);
		topY.setEditable(false);
		length.setEditable(false);
		height.setEditable(false);
		p.add(name);
		p.add(topX);
		p.add(topY);
		p.add(length);
		p.add(height);
		p.setLayout(new GridLayout(0,5));
		
		return p;
	}
	
	private void getSeatInfoPanel(JPanel simInfo){
		sectionSeats.setEditable(false);
		sectionFreeSeats.setEditable(false);
		sectionAssignedSeats.setEditable(false);
		sectionRowCount.setEditable(false);
		sectionColumnCount.setEditable(false);
		simInfo.add(new JLabel("Select Section"));
		simInfo.add(sectionNames);
		simInfo.add(new JLabel("Section Seats"));
		simInfo.add(sectionSeats);
		simInfo.add(new JLabel("Free Seats"));
		simInfo.add(sectionFreeSeats);
		simInfo.add(new JLabel("Assigned Seats"));
		simInfo.add(sectionAssignedSeats);
		simInfo.add(new JLabel("No Of Rows"));
		simInfo.add(sectionRowCount);
		simInfo.add(new JLabel("No Of COlumns"));
		simInfo.add(sectionColumnCount);
		simInfo.setLayout(new GridLayout(0,4));
		
		//setup event listeners
		sectionNames.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setSectionInfo(sectionNames.getSelectedIndex());
			}
		});
	}
	
	private JPanel getAboutUsPanel(JPanel aboutUs){
		aboutUs.add(new JLabel("Stadium Simulation File: "));
		aboutUs.add(new JLabel("Version: V17.05.2401"));
		aboutUs.add(new JLabel("Development Version: V0.01"));
		aboutUs.add(new JLabel("Author: Eric Ohwotu"));
		aboutUs.add(new JLabel("Change Log: "));
		aboutUs.add(new JLabel("--> Currently Implemented Seating Areas \nWith Auto \nSeat Assignment"));
		aboutUs.add(new JLabel("--> Currently Implemented Persons that calculate their velocity"));
		aboutUs.add(new JLabel("--> Currently Implemented Assign People To Seats"));
		aboutUs.add(new JLabel("--> Currently Implemented Assign Routes To People Based On Starting Position and Assigned Seat"));
		aboutUs.add(new JLabel("--> Currently Implementing New GUI"));
		aboutUs.add(new JLabel("--> Currently Implemented Initialising Simulation through GUI"));
		aboutUs.add(new JLabel("--> Currently Implemented Display Seat Section Data"));
		aboutUs.add(new JLabel("--> Currently Implemented Display About Us On Seperate Popup"));
		aboutUs.add(new JLabel("--> Currently Implemented Display Add Section With Section Dimensions"));
		aboutUs.setLayout(new GridLayout(0,1));
		
		return aboutUs;
	}
	
	private void getSimControlPanel(JPanel simControl){
		JButton initialise = new JButton("Initialise");
		JTextField personLimit = new JTextField("25000");
		JButton start = new JButton("Start");
		start.setEnabled(false);
		//JButton addStadiumSection = new JButton("New Section");
		//JTextField topX = new JTextField("");

		simControl.add(initialise);
		simControl.add(start);
		simControl.add(new JLabel("Expected People"));
		simControl.add(personLimit);
		simControl.setLayout(new GridLayout(0,4));
		simControl.setSize(400,30);
		
		//JLabel progressText = new JLabel("Not running");
		initialise.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				Stadium s = new Stadium(Integer.parseInt(personLimit.getText()));
				setComboBox(s);
				start.setEnabled(true);
				initialise.setEnabled(false);
				initialise.setText("Reset");
				//setup information section	
			}
		});
		
		start.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				stadium.init(Integer.parseInt(personLimit.getText()));
				setComboBox(stadium);
				//setup information section	
			}
		});
	}
	public void setComboBox(Stadium s){
		DefaultComboBoxModel model = new DefaultComboBoxModel(s.getSectionNames());//.toArray(new String[items.size()]));
		sectionNames.setModel(model); // = new JComboBox<String>(items);
		this.stadium = s;
	}
	
	public void setSectionInfo(int i){
		sectionSeats.setText("" + this.stadium.getSeatArea().getSeatSections().get(i).getSeatCount());
		sectionFreeSeats.setText("" + this.stadium.getSeatArea().getSeatSections().get(i).getFreeSeatCount());
		sectionAssignedSeats.setText("" + this.stadium.getSeatArea().getSeatSections().get(i).getAssignedSeatCount());
		sectionRowCount.setText("" + this.stadium.getSeatArea().getSeatSections().get(i).getSeatCount());
		sectionColumnCount.setText("" + this.stadium.getSeatArea().getSeatSections().get(i).getSeatCount());
	}
}
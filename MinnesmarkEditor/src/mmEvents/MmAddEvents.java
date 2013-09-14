package mmEvents;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdesktop.swingx.mapviewer.GeoPosition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;


import mmMap.*;
import mmStationEvents.MmAudioEvent;
import mmStationEvents.MmImageEvent;
import mmStationEvents.MmMarkerEvent;
import mmStationEvents.MmMessageEvent;
import mmStationEvents.MmModelEvent;
import mmStationEvents.MmPanoramaEvent;
import mmStationEvents.MmStationEvents;
import mmStationEvents.MmVideoEvent;


public class MmAddEvents extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame frame,property_frame;
	public JPanel eventPanel;
	ArrayList<JLabel> eventLabels;
	int currentIndex=0;
	
	ArrayList<MmAudioEvent> audioEvents;
	ArrayList<MmVideoEvent> videoEvents;
	ArrayList<MmMarkerEvent> markerEvents;
	ArrayList<MmPanoramaEvent> panoramaEvents;
	ArrayList<MmImageEvent> imageEvents;
	ArrayList<MmMessageEvent> messageEvents;
	ArrayList<MmModelEvent> modelEvents;
	ArrayList<MmStationEvents> stationEvents;
	Point pnt,current_pnt,select_pnt;
	
	JLabel moveLabel;
	
	JScrollPane pane;
	
	int select_index=-1,put_index=-1,selectLabelIndex=-1;
		
	boolean isMoveLabel=false;
	
	MmStationEvents station;
	
	MmMapViewer map;
	
	JDialog dialogFrame;
	
	JDialog eventDialog;
	
	boolean clicked;
	
	boolean isSaved;
	
	JFrame mainWindow;
	
	

	public MmAddEvents(JDialog frame1)
	{
		initializeEvents();
		
		eventDialog=frame1;
		
		dialogFrame = new JDialog();
		
		dialogFrame = null;
		
						
		//frame.setLocation(0,0);
		 //this.frame.setPreferredSize(new Dimension(400,200));
		
		eventDialog.setUndecorated(true);
		
		eventDialog.setBackground(new Color(255,255,255,255));
		setBorder(BorderFactory.createEmptyBorder(25, 5, 2, 5));
		
		 //MmEventsTabs tabs = new MmEventsTabs();
		 //add(tabs);
		 GridLayout grid = new GridLayout(0,1,1,1);
		 this.setLayout(new BorderLayout());
		 
		 eventPanel = new JPanel();
		 eventLabels = new ArrayList<JLabel>();
		 
		 pnt = new Point(0,0);
		 current_pnt = new Point(-1,-1);
		 
		 moveLabel = new JLabel();
		 
		 eventPanel.setLayout(grid);
		 
		 
		 JLabel lb = new JLabel("Lägg till media som spelas på stationen");
		 lb.setName("label");
		 eventLabels.add(lb);
		 eventLabels.add(addLabels());
		 eventLabels.add(addLabels());
		 eventLabels.add(addLabels());
		 
		 for(int i=0;i<eventLabels.size();i++)
			  eventPanel.add(eventLabels.get(i));
		 
		 JPanel buttonPanel = new JPanel();
		 		 	 
		 JButton addButton = new JButton("+   Lägg till media");
		 JButton minusButton = new JButton("-   Ta bort media");
		 JButton okButton = new JButton("Ok");
		 
		 pane = new JScrollPane(eventPanel);
		 pane.setBorder(BorderFactory.createEtchedBorder());
		 pane.setLocation(2, 10);
		 		 
		 add(pane,BorderLayout.CENTER);
		 
		 
		 eventPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent event) {
				// TODO Auto-generated method stub
								
				if(selectLabelIndex!=-1)
				{
					JLabel lb1 = (JLabel) eventPanel.getComponentAt(pnt);
					
					lb1.setOpaque(false);
					lb1.setBackground(Color.white);
					eventPanel.revalidate();
				}
				
				JLabel lb = (JLabel) eventPanel.getComponentAt(event.getPoint());
				 
				if(!lb.getText().isEmpty() && !(lb.getName().equals("label")))
				{	
					
				    lb.setOpaque(true);
					lb.setBackground(Color.lightGray);
				    int index = station.getLabels().indexOf(lb);
				    selectLabelIndex =index;
				    current_pnt=event.getPoint();
				    pnt=event.getPoint();
			        eventPanel.revalidate();
				   			      
				}  
				
				
				
				if(!lb.getText().isEmpty() && !(lb.getName().equals("label")) && event.getClickCount()==2)
				{
					
					lb.setOpaque(false);
					lb.setBackground(Color.white);
					
					if(lb.getText().contains("png")||lb.getText().contains("jpg")||lb.getText().contains("bmp")||lb.getText().contains("tiff"))
				    {
						
						int index = station.getLabels().indexOf(lb);
						
					    JDialog frame2 = new JDialog(mainWindow);
					    
					        
					    MmAddEventsDialog eventProperties = new MmAddEventsDialog(frame2,index,eventPanel,station,true);
					    eventProperties.setMainWindow(mainWindow);
					    frame2.pack();
					    frame2.setSize(400, 150);
					    frame2.setContentPane(eventProperties);
					    frame2.setLocation(eventDialog.getLocation().x+25, eventDialog.getLocation().y+50);
					    frame2.setVisible(true); 
					    frame2.toFront();
					    dialogFrame = frame2;
								
				    }    
					else if(!lb.getText().contains("patt"))
					{
						int index = station.getLabels().indexOf(lb);
						
						JDialog frame2 = new JDialog(mainWindow);
						MmAddEventsDialog eventProperties = new MmAddEventsDialog(frame2,index,eventPanel,station,false);
						eventProperties.setMainWindow(mainWindow);
					    frame2.pack();
					    frame2.setSize(400, 150);
					    frame2.setContentPane(eventProperties);
					    frame2.setLocation(eventDialog.getLocation().x+25, eventDialog.getLocation().y+50);
					    frame2.setVisible(true);
					    dialogFrame = frame2;
							
					}
					
				    					
				}
				
				eventPanel.remove(moveLabel);
								
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent event) {
				// TODO Auto-generated method stub
				JLabel lb =  (JLabel) eventPanel.getComponentAt(event.getPoint());
				
				int index = station.getLabels().indexOf(lb);
				System.out.println("index "+index+" "+station.getLabels().size());
				
				if(!lb.getName().equals("label") && (index+1)<station.getLabels().size() /*&& !station.getLabels().get(index+1).getName().equals("label")*/)
				{
					
					isMoveLabel=true;
					select_index = station.getLabels().indexOf(lb);
					select_pnt = event.getPoint();
					moveLabel.setText(lb.getText());
					moveLabel.setName(lb.getName());
					moveLabel.setOpaque(true);
					moveLabel.setBackground(Color.lightGray);
					moveLabel.setLocation(lb.getLocation());
					eventPanel.add(moveLabel);
					eventPanel.revalidate();
					
				}
				
				if(!lb.getName().equals("label") && index==station.getLabels().size()-1)
				{
					isMoveLabel=true;
					select_index = station.getLabels().indexOf(lb);
					select_pnt = event.getPoint();
					moveLabel.setText(lb.getText());
					moveLabel.setName(lb.getName());
					moveLabel.setOpaque(true);
					moveLabel.setBackground(Color.lightGray);
					moveLabel.setLocation(lb.getLocation());
					eventPanel.add(moveLabel);
					eventPanel.revalidate();
					
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				// TODO Auto-generated method stub
				
				JLabel lb = (JLabel) eventPanel.getComponentAt(event.getPoint());
				
				if(lb.getText().isEmpty() && isMoveLabel)
				{	
				   eventPanel.remove(moveLabel);
				   moveLabel.setOpaque(false);
				   moveLabel.setText("");
				   eventPanel.revalidate();
				   select_index=-1;
				   
				   isMoveLabel=false;
				}
				
				if(!lb.getText().isEmpty() && isMoveLabel)
				{	
				   put_index = station.getLabels().indexOf(lb);
				   System.out.println("index of label "+put_index);
				   if(Math.abs(select_index-put_index)==1)
				   {	   
				      station.getLabels().get(select_index).setText(lb.getText());
				      station.getLabels().get(put_index).setText(moveLabel.getText());
				      JLabel lb1 = (JLabel) eventPanel.getComponentAt(select_pnt);
				      lb1.setText(station.getLabels().get(select_index).getText());
				      lb.setText(moveLabel.getText());
				      
				      eventPanel.remove(moveLabel);
					  moveLabel.setOpaque(false);
				      moveLabel.setText("");
					  eventPanel.revalidate();
					  
				   }   
				   else
				   {
					 if(select_index>put_index)
					 {
						 
						 JLabel tempLabel = new JLabel(station.getLabels().get(put_index).getText());
						 tempLabel.setName(station.getLabels().get(put_index).getName());
						 station.getLabels().get(put_index).setText(moveLabel.getText());
						 station.getLabels().get(put_index).setName(moveLabel.getName());
						 swapDown(tempLabel,put_index);
						 eventPanel.remove(moveLabel);
						 moveLabel.setOpaque(false);
					     moveLabel.setText("");
						 eventPanel.revalidate();
						 
						 						 
					 }
					 
					 if(select_index<put_index)
					 {
						 
						 JLabel tempLabel = new JLabel(station.getLabels().get(select_index+1).getText());
						 tempLabel.setName(station.getLabels().get(select_index+1).getName());
						 int index = select_index; 
						 index++;
						 JOptionPane.showMessageDialog(null, "Entered");
						 swapUp(tempLabel,index);
						 station.getLabels().get(put_index).setText(moveLabel.getText());
						 station.getLabels().get(put_index).setName(moveLabel.getName());
						 eventPanel.remove(moveLabel);
						 moveLabel.setOpaque(false);
					     moveLabel.setText("");
						 eventPanel.revalidate();
						 
					 }
					 
				   }
				   isMoveLabel=false;
				   isSaved=false;
				}
				
				
			}
			 
		 });
		 
		 eventPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent event) {
				// TODO Auto-generated method stub
				
				if(isMoveLabel)
				{	
				   //showMessage("moved");	
				   moveLabel.setLocation(0,(int)event.getPoint().getY()-5);
				}   
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
		 
		 
		 addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
	           
				
				addEvent();
								
				eventPanel.repaint();
			    eventPanel.updateUI();
			    eventPanel.revalidate();
			    eventDialog.repaint();
				
				if(eventPanel.getComponentCount()>1)
				    System.out.println("component at index "+eventPanel.getComponent(1));
				
				isSaved=false;
			}
			
			
			 
		 });
		 
		 minusButton.addActionListener(new ActionListener(){
			 
			 public void actionPerformed(ActionEvent event)
			 {
				 
				 int index = selectLabelIndex;
				 JLabel lb1 = (JLabel)eventPanel.getComponent(selectLabelIndex);
				 lb1.setOpaque(false);
				 isSaved=false;		 
				 
				 System.out.println("index "+index);
				
				 if(index==0 && !station.getLabels().get(index).getName().equals("label") && station.getLabels().get(index+1).getName().equals("label"))
				 {
					 station.getLabels().get(index).setText("Lägg till media som spelas på stationen");
					 station.getLabels().get(index).setName("label");
					 JLabel lb = (JLabel) eventPanel.getComponent(index);
					 lb.setText("Lägg till media som spelas på stationen");
					 lb.setName("label");
					 lb.setOpaque(false);
					 System.out.println("panel size "+eventPanel.getComponentCount());	 
					 eventPanel.remove(moveLabel);
					 
				 }
				 else if(index<3 && station.getLabels().size() == 4)
				 {
					 index++;
					 deleteAndSwapUp(station.getLabels().get(index),index);
					 eventPanel.remove(moveLabel);
					 currentIndex--;
				 }
				 
				 if(index==3 && station.getLabels().size() == 4)
				 {
					 station.getLabels().get(index).setText("");
					 station.getLabels().get(index).setName("label");
					 JLabel lb = (JLabel) eventPanel.getComponent(index);
				     lb.setText("");
				     lb.setName("label");
				     
				     eventPanel.remove(moveLabel);
				     currentIndex--;
				 }
				 
				 if(station.getLabels().size() > 4)
				 {
					 station.getLabels().remove(index);
					 eventPanel.remove(index);
					 
					 eventPanel.remove(moveLabel);
					 currentIndex--;
				 }
				 
				 
				 eventPanel.revalidate(); 
				 pane.revalidate();
				 pane.updateUI();
				 eventDialog.repaint();
				 
				 /*int current_index=-1;
				 for(int i=0;i<station.getLabels().size();i++)
				 {	 
					 if(station.getLabels().get(i).isOpaque())
					 {	 
						 if(i+1<station.getLabels().size())
						 {	 
							current_index=i+1; 
						    if(!station.getLabels().get(i+1).getText().isEmpty())
						    {
							   station.getLabels().get(i).setText(station.getLabels().get(i+1).getText());
							   station.getLabels().get(i).setName(station.getLabels().get(i+1).getText());
							   station.getLabels().get(i+1).setText("");
							   station.getLabels().get(i+1).setName("label");
							   break;
						    }
						    else if(i!=0 && i!=station.getLabels().size()-1 )
						    {
						    	station.getLabels().get(i).setText("");
								station.getLabels().get(i).setName("label");
								break;
						    }
						 }
					 }
					 
					 if(i==station.getLabels().size()-1 && i!=0)
					 {
						 current_index=i;
						 station.getLabels().remove(i);
						 break;
					 }
					 
					 if(i==0)
					 {
						 if(!station.getLabels().get(i).getText().isEmpty())
						 {
							 current_index=i+1;
							 if(station.getLabels().get(i+1).getText().isEmpty())
							 {
								 station.getLabels().get(i).setText("L�gg till media som spelas p� stationen");
								 station.getLabels().get(i).setName("label");
								 break;
							 }
						 }
					 }
				 }
				 
				 if(current_index>4)
				 {
					 station.getLabels().remove(current_index);
				 }
				 
				 if(current_pnt.getX()!=-1 && current_pnt.getY()!=-1)
				 {	 
					 
					if(current_index-1<=4)
					{	
					   JLabel lb = (JLabel) eventPanel.getComponentAt(current_pnt);
					   showMessage(lb.getText());
					   lb.setText(station.getLabels().get(current_index-1).getText());
					   lb.setName(station.getLabels().get(current_index-1).getName());
					   lb.setOpaque(false);
					}   
					else
					{	
				      eventPanel.remove(eventPanel.getComponentAt(current_pnt));
					}  
				    //eventPanel.repaint();
				    //eventPanel.revalidate();
				    eventPanel.updateUI();
				    current_pnt.setLocation(-1, -1);
				    
				    currentIndex--;
				 }  
				 /*if(station.getLabels().size()<4)
				 {
					 JLabel lb = new JLabel();
					 lb.setName("label");
					 station.getLabels().add(lb);
				 }
				 
				 eventPanel.updateUI();*/
			 }
			 
			 
		 });
		 
		 okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				eventDialog.setVisible(false);
				if(dialogFrame!=null)
				  dialogFrame.setVisible(false);
				
				/*int stationIndex = stationEvents.indexOf(station);
				station.setStationName("station"+Integer.toString(stationIndex+1));
				station.setStationIndex(stationIndex);*/
				
			}
			 
		 });
		 
		 buttonPanel.add(addButton);
		 buttonPanel.add(minusButton);
		 buttonPanel.add(okButton);
		

		 
		 JPanel panel1 = new JPanel();
		 
		 panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
		 panel1.add(buttonPanel);
		 
		 add(panel1,BorderLayout.SOUTH);
		 eventDialog.add(this);
        
		 
		
	}
	
	
	public JFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	
	
	public void setMap(MmMapViewer map1)
	{
		map = map1;
	}
	
	/*
	 * Adds a new station index stores the current station index in array, stationIndex for naming the station,
	 * stationType for what type of station that is station or swing point, latitude,longitude and gps radius
	 */
	public void addStation(double lat,double lon,int index,int stationIndex,int gpsRadius,boolean stationType)
	{
		 station = new MmStationEvents();
		 station.setStationName("station"+Integer.toString(stationIndex));
		 station.setLatLon(lat, lon);
		 station.setStationIndex(stationIndex-1);
		 station.setCurrentStationIndex(index);
		 station.setGPSRadius(gpsRadius);
		 station.setStationType(stationType);
		 stationEvents.add(station);
		 eventPanel.removeAll();
		 currentIndex=0;
		 if(station.getLabels().isEmpty())
		 {	 
			 ArrayList<JLabel> stationLabels = new ArrayList<JLabel>(); 
			 JLabel lb = new JLabel("Lägg till media som spelas på stationen");
			 lb.setName("label");
			 stationLabels.add(lb);
			 stationLabels.add(addLabels());
			 stationLabels.add(addLabels());
			 stationLabels.add(addLabels());
			    
			 station.setLabels(stationLabels);
			    
			 for(int i=0;i<station.getLabels().size();i++)
					 eventPanel.add(station.getLabels().get(i));
			 eventPanel.updateUI();
			    
		}   
			 
		
	}
	
	public void updateStationGeoPosition(int index,GeoPosition point)
	{
		 
		if(!stationEvents.isEmpty())
		{	
		    System.out.println("Station events size "+stationEvents.size());		 
		    stationEvents.get(index).setLatLon(point.getLatitude(), point.getLongitude());
		 
		    System.out.println("events lat lon "+ stationEvents.get(index).getLatitude()+"  "+stationEvents.get(index).getLongitude()+"   "+index);
		}    
	}
	
	public void updateStationEvents(int index)
	{
		 
		 station = stationEvents.get(index);
		 
		 	 
		 System.out.println("eventLabels "+station.getLabels().size());
	     for(int i=0; i<station.getLabels().size();i++)
		 {
			  System.out.println("data "+station.getLabels().get(i));
			  eventPanel.add(station.getLabels().get(i));
		 }	 
	     
	     eventPanel.repaint();
		 eventPanel.updateUI();
	}
	
	public void updateStationEvents(GeoPosition point)
	{
		
		 int index = -1;
		 eventPanel.removeAll();
		 eventPanel.updateUI();
		 
		 currentIndex = 0;
		 
		 for(int i=0;i<stationEvents.size();i++)
		 {
			 System.out.println("events gps "+ stationEvents.get(i).getLatitude()+"  "+stationEvents.get(i).getLongitude());
			 if(stationEvents.get(i).isGPSPointPresent(point))
			 {
				 index = i;
				 break;
			 }
		 }
		 
		 		 
		 station = stationEvents.get(index);
			 
		 System.out.println("eventLabels "+station.getLabels().size());
	     for(int i=0; i<station.getLabels().size();i++)
		 {
				 System.out.println("data "+station.getLabels().get(i));
				 eventPanel.add(station.getLabels().get(i));
		 }	 
	     
	     eventPanel.repaint();
		 eventPanel.updateUI();
		 
	}
	
	public void insertStation(double lat,double lon,int index,int stationIndex,int gpsRadius,boolean stationType)
	{
		 int swgIndx = index;
		 currentIndex = 0;
		 for(int i=0;i<stationEvents.size();i++)
		 {
			 if(stationEvents.get(i).getCurrentStationIndex()==index)
			 {
				
				 for(int j=i;j<stationEvents.size();j++)
				 { 	 
				    if(stationEvents.get(j).getStationType())
				    {
				       swgIndx++;	
					   System.out.println("index present "+swgIndx);
				       stationEvents.get(j).setCurrentStationIndex(swgIndx);
				       stationEvents.get(j).setStationIndex(stationIndex-1);
				       stationEvents.get(j).setStationName("station"+stationIndex+"_"+"swingPoint"+(swgIndx));
				       System.out.println("swing point name "+stationEvents.get(j).getStationName());
				       
				    }
				 }   
			 }
		 }
		 
		 station = new MmStationEvents();
		 station.setLatLon(lat, lon);
		 station.setStationName("station"+stationIndex+"_"+"swingPoint"+index);
		 station.setStationIndex(stationIndex-1);
		 station.setCurrentStationIndex(index);
		 station.setGPSRadius(gpsRadius);
		 station.setStationType(stationType);
		 stationEvents.add(index, station);
		 
		 System.out.println("swing point name "+station.getStationName());
		 		 
	}
	
	// updates the swing point
	public void updateSwingPoints(int index,double lat,double lon)
	{
		 
		stationEvents.get(index).setLatLon(lat, lon);
		 
	}
	
	public void removeStation(int index)
	{
		int station_index = stationEvents.get(index).getStationIndex();
		
			
		if(index==0)
		{
			stationEvents.remove(index);
			for(int i=index;!stationEvents.isEmpty();)
			{
				//JOptionPane.showMessageDialog(null, stationEvents.get(i).getStationName()+"  "+stationEvents.get(i).getStationIndex());
				if(stationEvents.get(i).getStationIndex()==station_index)
				{
					//JOptionPane.showMessageDialog(null, stationEvents.get(i).getStationName()+"  "+stationEvents.get(i).getStationIndex());
					stationEvents.remove(index);
				}
				else
					break;
			}
			
			for(int i=0;i<stationEvents.size();i++)
			{
				if(!stationEvents.get(i).getStationType())
				{
					stationEvents.get(i).setStationIndex(stationEvents.get(i).getStationIndex()-2);
					stationEvents.get(i).setStationName("station"+Integer.toString(stationEvents.get(i).getStationIndex()));
					stationEvents.get(i).setCurrentStationIndex(stationEvents.get(i).getCurrentStationIndex()-1);		
				}
				
				if(stationEvents.get(i).getStationType())
				{
					stationEvents.get(i).setStationIndex(stationEvents.get(i).getStationIndex()-2);
					stationEvents.get(i).setStationName("station"+Integer.toString(stationEvents.get(i).getStationIndex())+"_swingPoint"+Integer.toString(i+1));
					stationEvents.get(i).setCurrentStationIndex(stationEvents.get(i).getCurrentStationIndex()-1);		
				}
			}
			
			/*for(int i=0;i<stationEvents.size();i++)
			{
				System.out.println("station index after remove "+stationEvents.get(i).getStationName()+"  "+stationEvents.get(i).getStationIndex());
			}*/
			
			return;
		}
		
		int swingIndx = -1;
		
		if((index)>0 && (index)<stationEvents.size()-1)
		{
		
			for(int i=index-1;i<stationEvents.size();i++)
			{
				System.out.println("data before "+stationEvents.get(i).getStationName()+"  "+stationEvents.get(i).getStationIndex());
			}
			
			stationEvents.remove(index); 
			if(stationEvents.get(index-1).getStationType())
			    swingIndx = stationEvents.get(index-1).getIndexStationNameIndex();
			int stationIndx;
			
			
			
			System.out.println(" ");
			
						
			for(int i = index;i<stationEvents.size();i++)
			{
				if(stationEvents.get(i).getStationType() && swingIndx!=-1)
				{
					swingIndx++;
					stationIndx = stationEvents.get(i).getStationIndex()-2;
					stationEvents.get(i).setStationIndex(stationIndx);
					stationEvents.get(i).setStationName("station"+Integer.toString(stationEvents.get(i).getStationIndex())+"_swingPoint"+Integer.toString(swingIndx));
					stationEvents.get(i).setCurrentStationIndex(stationEvents.get(i).getCurrentStationIndex()-1);
				}
				
				if(!stationEvents.get(i).getStationType())
				{
					swingIndx=0;
					stationIndx = stationEvents.get(i).getStationIndex()-2;
					stationEvents.get(i).setStationIndex(stationIndx);
					stationEvents.get(i).setStationName("station"+Integer.toString(stationEvents.get(i).getStationIndex()));
					stationEvents.get(i).setCurrentStationIndex(stationEvents.get(i).getCurrentStationIndex()-1);
				}
			}
			
			
			for(int i=index-1;i<stationEvents.size();i++)
			{
				System.out.println("data after "+stationEvents.get(i).getStationName()+"  "+stationEvents.get(i).getStationIndex());
			}
			
			return;
			
			/*JOptionPane.showConfirmDialog(null, "Entered "+stationEvents.get(index-1).getIndexStationName()+"  "+stationEvents.get(index+1).getIndexStationName());
			String prev_station = stationEvents.get(index-1).getStationName();
			String next_station = stationEvents.get(index+1).getStationName();
			
			station_index = stationEvents.get(index).getStationIndex();
			
			if(prev_station.length()==8 && next_station.length()==8)
			{
				stationEvents.remove(index);
				
				for(int i=index;i<stationEvents.size();i++)
				{
					if(!stationEvents.get(index).getStationType())
					{
						stationEvents.get(index).setStationName("station"+Integer.toString(i));
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getStationIndex()-2);
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getCurrentStationIndex()-1);
					}
					
					if(stationEvents.get(index).getStationType())
					{
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getStationIndex()-2);
						stationEvents.get(index).setStationName("station"+Integer.toString(i)+"_swingPoint"+ stationEvents.get(index).getStationIndex());
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getCurrentStationIndex()-1);
					}
				}
			}
			
			/*if(stationEvents.get(index+1).getStationName().contains("station"))
			{
				stationEvents.remove(index);
				
				for(int i=index;i<stationEvents.size();i++)
				{
					if(!stationEvents.get(index).getStationType())
					{
						stationEvents.get(index).setStationName("station"+Integer.toString(i));
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getStationIndex()-2);
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getCurrentStationIndex()-1);
					}
					
					if(stationEvents.get(index).getStationType())
					{
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getStationIndex()-2);
						stationEvents.get(index).setStationName("station"+Integer.toString(i)+"_swingPoint"+ stationEvents.get(index).getStationIndex());
						stationEvents.get(index).setStationIndex(stationEvents.get(index).getCurrentStationIndex()-1);
					}
				}
			}
			
			
			String swingName = stationEvents.get(index).getStationName();
			if(swingName.contains("swingPoint"))
			{		
			    int strIndx = stationEvents.get(index).getStationName().indexOf("swingPoint");
			
			    swingName = swingName.substring(strIndx+8, swingName.length());
			
			    int indx = Integer.parseInt(swingName);
			
			    JOptionPane.showMessageDialog(null, "index "+indx);
			}
		    
			stationEvents.remove(index);
			
			for(int i=index;i<stationEvents.size();i++)
			{
				if(!stationEvents.get(i).getStationType())
				{
					stationEvents.get(i).setStationIndex(stationEvents.get(i).getStationIndex()-2);
					stationEvents.get(i).setStationName("station"+Integer.toString(stationEvents.get(i).getStationIndex()));
					stationEvents.get(i).setCurrentStationIndex(stationEvents.get(i).getCurrentStationIndex()-1);		
				}
				
				if(stationEvents.get(i).getStationType())
				{
					stationEvents.get(i).setStationIndex(stationEvents.get(i).getStationIndex()-2);
					stationEvents.get(i).setStationName("station"+Integer.toString(stationEvents.get(i).getStationIndex())+"_swingPoint"+Integer.toString(i+1));
					stationEvents.get(i).setCurrentStationIndex(stationEvents.get(i).getCurrentStationIndex()-1);		
				}
			} */
			
		}
		
		if((index) == stationEvents.size()-1)
		{
			stationEvents.remove(index);
			
			int i= stationEvents.size()-1;
			
			while(!stationEvents.isEmpty())
			{
				if(stationEvents.get(i).getStationType())
				{
					stationEvents.remove(i);
					i=stationEvents.size()-1;
				}
				else
					break;
					
			}
			
			return;
		}
		
	}
	
	
	
	public void removeSwingPoint(int index)
	{
		
				
		stationEvents.remove(index);
		
		for(int i=index;i<stationEvents.size();i++)
		{
			if(stationEvents.get(i).getStationType())
			{	
			    stationEvents.get(i).setStationName("station"+Integer.toString(stationEvents.get(i).getStationIndex())+"_swingPoint"+Integer.toString(i));
			    stationEvents.get(i).setCurrentStationIndex(i);
			    //return;
			}  
			else
			{	
				stationEvents.get(i).setCurrentStationIndex(i);
				//return;
			}	
			
		}
		
		
		
	}
	
		
	public void initializeEvents()
	{
		audioEvents = new ArrayList<MmAudioEvent>();
	    videoEvents = new ArrayList<MmVideoEvent>();
	    markerEvents = new ArrayList<MmMarkerEvent>();
	    panoramaEvents = new ArrayList<MmPanoramaEvent>();
	    imageEvents = new ArrayList<MmImageEvent>();
	    messageEvents = new ArrayList<MmMessageEvent>();
	    modelEvents = new ArrayList<MmModelEvent>();
	    stationEvents = new ArrayList<MmStationEvents>();
	}
	
	public JLabel addLabels()
	{
		JLabel lb = new JLabel();
		lb.setName("label");
		return lb;
	}
	
	public void addEvent()
	{
		
		map.hideStationEventWindow();
		
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter MediaFilter = new FileNameExtensionFilter(
		        "*.jpg *.png *.gif *.bmp *.mp3 *.m4a *.aiff *.m4v *.txt", "jpg", "png","gif","bmp","mp3","m4a","aiff","m4v","txt","obj","osg");
		
		
		fileChooser.addChoosableFileFilter(MediaFilter);
		fileChooser.setFileFilter(MediaFilter);
		
		
		
		int val = fileChooser.showOpenDialog(null);
		
	       if (val == JFileChooser.APPROVE_OPTION) {
	    	   File file = fileChooser.getSelectedFile();
	    	   
	    	   map.showStationEventWindow();
	    	   map.bringTofront();
	    	   
	    	   if(currentIndex<4)
	    	   {  
	    		  
	    		  for(int i=0;i<station.getLabels().size();i++)
	    		  {
	    			  if(station.getLabels().get(i).getName().equals("label"))
	    			  {	  
	    				  
	  	                  station.getLabels().get(i).setText(file.getName());
	  	                  //JOptionPane.showMessageDialog(null, file.getAbsoluteFile());
	  	                  station.getLabels().get(i).setName(file.getAbsolutePath());
	  	                  JLabel lb = (JLabel) eventPanel.getComponent(i);
	  	                  lb.setText(file.getName());
	  	                  lb.setName(file.getAbsolutePath());
	  	                  currentIndex++;
	  	                  break;
	    			  }    
	    		  }    
	  	    	  	        
	    	   }    
	    	   else
	    	   {
	    		   JLabel lb1 = new JLabel(file.getName());
	    		   
	    		   lb1.setName(file.getAbsolutePath());
	    		   station.getLabels().add(lb1);
	    		   eventPanel.add(lb1);
	    	   }
	  	       
	    	   	  	       
	  	       /*int index = file.getName().lastIndexOf('.');
	  	       
	  	       if(index>0)
	  	       {
	  	    	   String extention = file.getName().substring(index+1);
	  	    	   if(extention.equals("jpg")||(extention.equals("png")))
	  	    	   {
	  	    		   MmImageEvent imageEvent = new MmImageEvent();
	  	    		   imageEvent.setImageFile(file.getName());
	  	    		   imageEvents.add(imageEvent);
	  	    	   }
	  	    	   
	  	       }*/
	  	       
	       }
	       else
	       {
	    	   map.showStationEventWindow();
	    	   map.bringTofront();
	       }
	       
		
	}
	
	public void showMessage(String message)
    {
		 JOptionPane.showMessageDialog(this, message);
    }
	
	public boolean swapDown(JLabel label,int curr_index)
	{
		System.out.println("sawp index "+curr_index);
		//showMessage(label.getName());
		JLabel next_label = new JLabel(station.getLabels().get(curr_index+1).getText());
		next_label.setName(station.getLabels().get(curr_index+1).getName());
		station.getLabels().get(curr_index+1).setText(label.getText());
		station.getLabels().get(curr_index+1).setName(label.getName());
		JLabel lb1 = (JLabel) eventPanel.getComponent(curr_index+1);
		lb1.setText(label.getText());
		lb1.setName(label.getName());
		//showMessage(lb1.getName());
		curr_index++;
		if(curr_index==select_index)
		{
			return false;
		}
		//else
		{
			swapDown(next_label,curr_index);
		}
		
		return true;
	}
	
	public boolean swapUp(JLabel label,int curr_index)
	{
		System.out.println("sawp index "+curr_index);
		//showMessage(label.getName());
		
		if(curr_index==put_index)
		{
			JLabel last_label = new JLabel(station.getLabels().get(put_index).getText());
			last_label.setName(station.getLabels().get(put_index).getName());
			//showMessage(last_label.getName());
			station.getLabels().get(put_index-1).setText(last_label.getText());
			station.getLabels().get(put_index-1).setName(last_label.getName());
			JLabel lb2 = (JLabel) eventPanel.getComponent(put_index-1);
			lb2.setText(last_label.getText());
			lb2.setName(last_label.getName());
			return false;
		}
		else
		{	
		    JLabel next_label = new JLabel(station.getLabels().get(curr_index+1).getText());
		    next_label.setName(station.getLabels().get(curr_index+1).getName());
		    station.getLabels().get(curr_index-1).setText(label.getText());
		    station.getLabels().get(curr_index-1).setName(label.getName());
		    JLabel lb1 = (JLabel) eventPanel.getComponent(curr_index-1);
		    lb1.setText(label.getText());
		    lb1.setName(label.getName());
		    //showMessage(lb1.getName());
		    curr_index++;
		    swapUp(next_label,curr_index);
		}
		
		
		return true;
	}
	
	public boolean deleteAndSwapUp(JLabel label,int curr_index)
	{
		System.out.println("sawp index "+curr_index+"  "+label.getName());
		
		
		if(curr_index==3 && station.getLabels().size()-1==3)
		{
			JLabel last_label = new JLabel(station.getLabels().get(curr_index).getText());
			last_label.setName(station.getLabels().get(curr_index).getName());
			//showMessage("last_label "+last_label.getText()+"  "+last_label.getName());
			station.getLabels().get(curr_index-1).setText(last_label.getText());
			station.getLabels().get(curr_index-1).setName(last_label.getName());
			JLabel lb2 = (JLabel) eventPanel.getComponent(curr_index-1);
			lb2.setText(last_label.getText());
			lb2.setName(last_label.getName());
			station.getLabels().get(curr_index).setText("");
			station.getLabels().get(curr_index).setName("label");
			lb2 = (JLabel) eventPanel.getComponent(curr_index);
			lb2.setText("");
			lb2.setName("label");
			
			eventPanel.revalidate();
			return false;
		}
		
		/*if(curr_index==station.getLabels().size()-1)
		{
			JLabel last_label = new JLabel(station.getLabels().get(curr_index).getName());
			last_label.setName(station.getLabels().get(curr_index).getName());
			showMessage(last_label.getName());
			station.getLabels().get(curr_index-1).setText(last_label.getText());
			station.getLabels().get(curr_index-1).setName(last_label.getName());
			JLabel lb2 = (JLabel) eventPanel.getComponent(curr_index-1);
			lb2.setText(last_label.getText());
			lb2.setName(last_label.getName());
			station.getLabels().remove(station.getLabels().size()-1);
			eventPanel.remove(station.getLabels().size()-1);
			return false;
		}*/
		
		//else
		{
			//showMessage("text and label "+label.getText()+"  "+label.getName());
		    JLabel next_label = new JLabel(station.getLabels().get(curr_index+1).getText());
		    next_label.setName(station.getLabels().get(curr_index+1).getName());
		    station.getLabels().get(curr_index-1).setText(label.getText());
		    station.getLabels().get(curr_index-1).setName(label.getName());
		    JLabel lb1 = (JLabel) eventPanel.getComponent(curr_index-1);
		    lb1.setText(label.getText());
		    lb1.setName(label.getName());
		    //showMessage(lb1.getName());
		    curr_index++;
		    //showMessage("next_label and text "+next_label.getText()+"  "+next_label.getName());
		    deleteAndSwapUp(next_label,curr_index);
		}
		return true;
	}
	
	public ArrayList<MmStationEvents> getStations()
	{
		return stationEvents;
	}
	
	//returns the current station index
	public int getStationIndex(int index)
	{
		for(int i=0;i<stationEvents.size();i++)
		{
			if(!stationEvents.get(i).getStationType())
			{
				
				if(stationEvents.get(i).getStationIndex()==index)
				{
					return stationEvents.get(i).getCurrentStationIndex();
				}
			}
		}
		
		return -1;
	}
	
	
	public void setSaved(boolean save)
	{
		isSaved = save;
	}
	
	public boolean getSavedState()
	{
		return isSaved;
	}
	

}

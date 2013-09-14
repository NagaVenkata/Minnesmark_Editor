package mmMediaViewer_Player;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;


import mmStationEvents.MmGlobalMarkerEvents;
import mmStationEvents.MmStationEvents;


public class MmImageViewer extends JPanel {
	
    public JDialog frame;
	
	Image image;
	
	JPanel p,buttonPanel;
	
	int locationx,locationy;
	
	String selectedText=null;
	JLabel textLabel;
	JPanel textPanel;
	String[] textStrings;
	
	int componentIndex;
	
	MmStationEvents currentStation;
	MmGlobalMarkerEvents currentMarker;
	
	int panelWidth,panelHeight;
	
	JLabel mapMarkerLabel;
	
	JScrollPane imagePane;

	
	
	public MmImageViewer(JDialog frame1,String imageName)
	{
		frame=frame1;
		
			
		JScrollPane pane = new JScrollPane();
		
		File sourceimage = new File(imageName);
		
		JButton bt = new JButton("Stäng");
		
		
		try
		{
		   ImageIcon image1 = new ImageIcon(imageName);	
		   image = ImageIO.read(sourceimage);
		   JLabel imageLabel = new JLabel(image1);
		   //imageLabel.setText("HI");
		   pane.setPreferredSize(new Dimension(400,400));
		   imageLabel.setVisible(true);
		   pane.setViewportView(imageLabel);
		   add(pane);
			//frame.add(pane);
		   
		}
		catch(Exception e)
		{
			
		}
		
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				frame.setVisible(false);
			}
			
		});
			
		add(bt);
		
		
	}

}

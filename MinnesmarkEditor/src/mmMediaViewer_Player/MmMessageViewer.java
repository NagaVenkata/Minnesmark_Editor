package mmMediaViewer_Player;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;
import javax.swing.*;

import mmStationEvents.MmGlobalMarkerEvents;
import mmStationEvents.MmStationEvents;

public class MmMessageViewer extends JPanel {
	
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
	
	JScrollPane textPane;

	
	
	public MmMessageViewer(JDialog frame1,String textFile)
	{
		frame=frame1;
		
		JScrollPane pane = new JScrollPane();
		
		JButton bt = new JButton("Stäng");
		
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(textFile));
			StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                  sb.append(line);
                  sb.append("\n");
                  line = br.readLine();
            }
            String text = sb.toString(); 
		   JTextArea textArea = new JTextArea(5,20);
		   textArea.setEditable(false);
		   textArea.setText(text);
		   pane.setPreferredSize(new Dimension(400,400));
		   pane.setViewportView(textArea);
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

package mmEvents;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument.Iterator;


import mmMouseEvents.MmMouseEvents;
import mmStationEvents.MmAudioEvent;
import mmStationEvents.MmImageEvent;
import mmStationEvents.MmMarkerEvent;
import mmStationEvents.MmMessageEvent;
import mmStationEvents.MmModelEvent;
import mmStationEvents.MmPanoramaEvent;
import mmStationEvents.MmStationEvents;
import mmStationEvents.MmVideoEvent;

public class MmEventsTabs extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTabbedPane tabs;
	JComboBox eventPath;
	JPopupMenu markerContextMenu;
	
	ArrayList<MmAudioEvent> audioEvents;
	ArrayList<MmVideoEvent> videoEvents;
	ArrayList<MmMarkerEvent> markerEvents;
	ArrayList<MmPanoramaEvent> panoramaEvents;
	ArrayList<MmImageEvent> imageEvents;
	ArrayList<MmMessageEvent> messageEvents;
	ArrayList<MmModelEvent> modelEvents;
	ArrayList<MmStationEvents> stationEvents;
	
	MmMarkerEvent markerEvent;
	MmAudioEvent audioEvent;
	
	JMenu menuAudioEvent;
	
	public MmEventsTabs()
	{
		
		audioEvents = new ArrayList<MmAudioEvent>();
		videoEvents = new ArrayList<MmVideoEvent>();
		markerEvents = new ArrayList<MmMarkerEvent>();
		panoramaEvents = new ArrayList<MmPanoramaEvent>();
		imageEvents = new ArrayList<MmImageEvent>();
		messageEvents = new ArrayList<MmMessageEvent>();
		modelEvents = new ArrayList<MmModelEvent>();
		stationEvents = new ArrayList<MmStationEvents>();
		
		
		JComponent tab1 = markersPanel();
		
		this.addTab("Marker Event", tab1);
		
        //JComponent tab2 = new JLabel("Audio Event");
		JComponent tab2 = audioPanel();
		
		this.addTab("Audio Event", tab2);
		
        JComponent tab3 = new JLabel("Video Event");
		
		this.addTab("video Event", tab3);
	
        JComponent tab4 = new JLabel("3D Events");
		
		this.addTab("3D Events", tab4);
		
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    
		
	     	
		
	}
	
	public JPanel markersPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		JPanel markerPanel = new JPanel();
		markerPanel.setPreferredSize(new Dimension(300,200));
		markerPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		markerEvent = new  MmMarkerEvent();
		
		JLabel label = new JLabel("Marker File");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=0;
		constraints.gridy=0;
		markerPanel.add(label,constraints);
	 
		markerEvent.markerFileLabel = new JLabel("File");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=2;
		constraints.gridy=0;
		
		
		markerEvent.markerFileLabel.addMouseListener(new MmMouseEvents(markerEvent.markerFileLabel,markerPanel,2));
		
		markerPanel.add(markerEvent.markerFileLabel,constraints);
		
		
		JLabel eventLabel = new JLabel("Event Name");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=0;
		constraints.gridy=1;
		markerPanel.add(eventLabel,constraints);
	 
		//markerEvent.eventNameLabel = new JTextField(20);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=2;
		constraints.gridy=1;
		
		
		//markerPanel.add(markerEvent.eventNameLabel,constraints);
		
		JLabel modelLabel = new JLabel("Model File");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=0;
		constraints.gridy=2;
		markerPanel.add(modelLabel,constraints);
	 
		markerEvent.modelFileLabel = new JLabel("File");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=2;
		constraints.gridy=2;
		
		
		markerEvent.modelFileLabel.addMouseListener(new MmMouseEvents(markerEvent.modelFileLabel,markerPanel,2));
		
		markerPanel.add(markerEvent.modelFileLabel,constraints);
		
		JLabel eventsLabel = new JLabel("Other Events");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=0;
		constraints.gridy=3;
		markerPanel.add(eventsLabel,constraints);
	 	
		JButton bt1 = new JButton("Events");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=2;
		constraints.gridy=3;
		
		markerContextMenu = new JPopupMenu();
		
		menuAudioEvent = new JMenu("AudioEvent");
		if(audioEvents.size()>0)
		{	
			java.util.Iterator<MmAudioEvent> audioIterator = audioEvents.iterator();
		   
			while(audioIterator.hasNext())
			{	
		       menuAudioEvent.add(audioIterator.next().getEventName());
		       
			}   
		}   
	
		markerContextMenu.add(menuAudioEvent);
		markerContextMenu.add(new JMenuItem("Video Event"));
		markerContextMenu.add(new JMenuItem("Panorama Event"));
		
		
		bt1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent event) {
				// TODO Auto-generated method stub
				
				//if(event.isPopupTrigger())
				{
					System.out.println("entered data "+event.getX()+"  "+event.getY());
					markerContextMenu.show(event.getComponent(), 8, 25);
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
				
				
		markerPanel.add(bt1,constraints);
		
       
		panel.add(markerPanel,BorderLayout.NORTH);
		JButton bt = new JButton("OK");
		panel.add(bt,BorderLayout.SOUTH);
		
		return panel;
	}
	
	public JPanel audioPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		JPanel audioPanel = new JPanel();
		audioPanel.setPreferredSize(new Dimension(300,200));
		audioPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel label = new JLabel("Audio FileName");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=0;
		constraints.gridy=0;
		audioPanel.add(label,constraints);
	 
		JLabel markerFilePath = new JLabel("File");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=2;
		constraints.gridy=0;
		
		
		markerFilePath.addMouseListener(new MmMouseEvents(markerFilePath,audioPanel,2));
		
		audioPanel.add(markerFilePath,constraints);
		
		audioEvent = new MmAudioEvent();
		
		
		JLabel eventLabel = new JLabel("Event Name");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=0;
		constraints.gridy=1;
		audioPanel.add(eventLabel,constraints);
	 
		audioEvent.eventAudioName = new JTextField(50);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=2;
		constraints.gridy=1;
		
		audioPanel.add(audioEvent.eventAudioName,constraints);
		
		JLabel eventLabels = new JLabel("Other Events");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=0;
		constraints.gridy=2;
		audioPanel.add(eventLabels,constraints);
	 
		String[] events = {"Events","Audio Event","Video Event","3D Events"};
		
		JComboBox eventPath = new JComboBox(events);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		constraints.gridx=2;
		constraints.gridy=2;
		
				
		audioPanel.add(eventPath,constraints);
		
		eventPath.setSelectedIndex(eventPath.getSelectedIndex());
		
		System.out.println(eventPath.getSelectedIndex() +" selected " );

		
		panel.add(audioPanel,BorderLayout.NORTH);
		JButton bt = new JButton("OK");
		panel.add(bt,BorderLayout.SOUTH);
		
		bt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				audioEvent.setEventName(audioEvent.eventAudioName.getText());
				audioEvents.add(audioEvent);
				
				if(audioEvents.size()>0)
				{	
					java.util.Iterator<MmAudioEvent> audioIterator = audioEvents.iterator();
				   
					while(audioIterator.hasNext())
					{	
				       menuAudioEvent.add(audioIterator.next().getEventName());
				       
					}   
				}   
			}
			
		});
		
		return panel;
	}
}

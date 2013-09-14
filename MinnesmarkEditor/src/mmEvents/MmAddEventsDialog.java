package mmEvents;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.ComponentPeer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import mmEvents.MmAddEvents;
import mmStationEvents.MmGlobalMarkerEvents;
import mmStationEvents.MmStationEvents;
import mmMap.*;
import mmMediaViewer_Player.*;



import javax.swing.*;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.GeoPosition;

public class MmAddEventsDialog extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	Point orignalWindowLocation,currentWindowLocation;
	public boolean windowDisplaced=false;
	
	JCheckBox chkbx,panoramaChkbx,cmbgChkbx;
	JButton bt,bt1,bt2;
	String choosenText;
	
	int eventIndex;
	
	MmAudioVideoPlayer audioPlayer = new MmAudioVideoPlayer();
	
	MmMapViewer mapViewer;
	
	JFrame mainWindow;


	

	public MmAddEventsDialog(JDialog frame1,int width,int height,MmMapViewer mapView)
	{
		super();
		p = new JPanel();
		
		mapViewer = mapView;
		
		panelWidth=width;
		panelHeight=height;
		
		image = new ImageIcon(getClass().getResource("/frame.png")).getImage();
		
		
		
		this.frame = frame1;
		
		ImageIcon icon = new ImageIcon("/Users/Umapathi/Desktop/MinnesmarkEditor/images/map_marker.png");
		
		
				
		this.frame.setUndecorated(true);
		this.frame.setAlwaysOnTop(true);
		this.frame.setBackground(new Color(255,255,255,0));
		
		MmAddEventsDialog.setWindowAlpha(this.frame, 0.0f);
			
		setOpaque(true);
		
		mapMarkerLabel = new JLabel(icon);
		
		p.add(mapMarkerLabel);
		
		p.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			    windowDisplaced=true;	
			}

			@Override
			public void mousePressed(MouseEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				// TODO Auto-generated method stub
				
				Point pnt = new Point(event.getX()-300,event.getY()-300);
				mapViewer.geoPos.add(mapViewer.getGeoPosition(pnt));
				System.out.println(mapMarkerLabel.getLocation()+"frame loocation  "+frame.getLocation());
				mapViewer.setFocusable(true);
				
				frame.setVisible(false);
				frame.setFocusable(false);
				
			}
			
		});

		add(p);

	}
	
	
	public JFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	
	public MmAddEventsDialog(JDialog frame1,int index,JPanel panel,MmGlobalMarkerEvents marker,boolean focusEvents)
	{
			
		super();
		p = new JPanel();
		
		panelWidth=300;
		panelHeight=200;
		componentIndex=index;
		
		setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
		
		textPanel = panel;
		
		currentMarker = marker;
		
		panoramaChkbx = new JCheckBox("Panorama");
		
		cmbgChkbx  = new JCheckBox("Med Kamera bakgrund");
		
		panoramaChkbx.setEnabled(false);
		cmbgChkbx.setEnabled(false);
		
		chkbx = new JCheckBox("Mediafilen är en skatt");
		
		bt = new JButton("Ok");
		
        bt1 = new JButton();
        
        bt2 = new JButton("Stoppa");
        
        bt2.setEnabled(false);
		
		textStrings = ((JLabel) textPanel.getComponent(componentIndex)).getText().split(":");
		
				
		if((textStrings[0].contains(".jpg")) || (textStrings[0].contains(".png")) || (textStrings[0].contains(".bmp")))
		{
			
			panoramaChkbx.setEnabled(true);
			bt1.setText("Visa Bilden");
		}
		if((textStrings[0].contains(".mp3")) || (textStrings[0].contains(".aiff")))
		{
			bt1.setText("Spela Upp Ljud");
			
		}
		
		if((textStrings[0].contains(".txt")))
		{
			bt1.setText("Visa");
			
		}
		
		String label = ((JLabel) textPanel.getComponent(componentIndex)).getText();
		
		if(label.contains("Panorama"))
		{
			panoramaChkbx.setSelected(true);
			cmbgChkbx.setSelected(false);
		}
		
		if(label.contains("skatt"))
		{
			chkbx.setSelected(true); 
		}
		
		if(label.contains("Model"))
		{
			panoramaChkbx.setSelected(false);
			cmbgChkbx.setSelected(true);
		}
		
		textStrings = ((JLabel) textPanel.getComponent(componentIndex)).getText().split(":");
		
		panoramaChkbx.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(panoramaChkbx.isSelected())
					cmbgChkbx.setEnabled(false);
				else
					cmbgChkbx.setEnabled(true);
			}
			
		});
		
		cmbgChkbx.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(cmbgChkbx.isSelected())
					panoramaChkbx.setEnabled(false);
				else
					panoramaChkbx.setEnabled(true);
			}
			
		});
		
		if((textStrings[0].contains(".jpg")) || (textStrings[0].contains(".png")) || (textStrings[0].contains(".bmp")))
		{
			
			panoramaChkbx.setEnabled(true);
			cmbgChkbx.setEnabled(true);
		}
		
		
		System.out.println("data "+textStrings.length);
	
		p.setLayout(new GridLayout(0,2));
				
		MediaTracker mt = new MediaTracker(this);
		image = new ImageIcon(getClass().getResource("/frame.png")).getImage();
		
		mt.addImage(image,0);
		
		this.frame = frame1;
		
		
				
		this.frame.setUndecorated(true);
		
		this.frame.setBackground(new Color(255,255,255));
		
		
			
		setOpaque(true);
		
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				frame.setVisible(false);
				JLabel lb = (JLabel) textPanel.getComponent(componentIndex);
				lb.setText(textStrings[0]);
				if(panoramaChkbx.isSelected() && !chkbx.isSelected())
				{	
					lb.setText(lb.getText()+":"+"Panorama");
				}
				
				if(cmbgChkbx.isSelected() && !chkbx.isSelected())
				{	
				    lb.setText(lb.getText()+":"+"Model");
				}
				
				if(chkbx.isSelected())
				{
					if(panoramaChkbx.isSelected())
					   lb.setText(lb.getText()+":"+"Panorama"+":"+"skatt");
					else if(cmbgChkbx.isSelected())
						lb.setText(lb.getText()+":"+"Model"+":"+"skatt");	
					else
						lb.setText(lb.getText()+":"+"skatt");
				}
				
				currentMarker.getLabels().get(componentIndex).setText(lb.getText());
				
				textPanel.revalidate();
				textPanel.repaint();
			}
			
		});
		
		
		bt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				JLabel lb = (JLabel) textPanel.getComponent(componentIndex);
				if((lb.getText().contains("bmp")) || (lb.getText().contains("png")) || (lb.getText().contains("jpg")))
				{	
				   JDialog frame2 = new JDialog(mainWindow);
				   MmImageViewer imageViewer = new MmImageViewer(frame2,lb.getName());
				   imageViewer.setOpaque(true);
			       frame2.setContentPane(imageViewer);
			       frame2.pack();
			       frame2.setSize(425, 475);
			       frame2.setVisible(true);
				}   
				else if((lb.getText().contains("mp3")) || (lb.getText().contains("m4v")))
				{
					bt2.setEnabled(true);
				    audioPlayer.setAudioFile(lb.getName());
				    audioPlayer.audioPlay();
				}
				if((lb.getText().contains(".txt")))
				{	
				   JDialog frame = new JDialog(mainWindow);
				   MmMessageViewer messageViewer = new MmMessageViewer(frame,lb.getName());
				   messageViewer.setOpaque(true);
			       frame.setContentPane(messageViewer);
			       frame.pack();
			       frame.setSize(425, 475);
			       frame.setVisible(true);
				}
			}
			
		});
		
		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				audioPlayer.audioStop();
				bt2.setEnabled(false);
			}
			
		});
		
				
		p.add(panoramaChkbx);
		p.add(chkbx);
		p.add(cmbgChkbx);
		p.add(bt);
		p.add(bt1);
		p.add(bt2);
		p.setBorder(BorderFactory.createEtchedBorder());
		add(p);
		
		
	}
	
	
	
	public MmAddEventsDialog(JDialog frame1,int index,JPanel panel,MmStationEvents station,boolean focusEvents)
	{
			
		super();
		p = new JPanel();
		
		panelWidth=300;
		panelHeight=200;
		componentIndex=index;
		
		setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
		
		textPanel = panel;
		
		currentStation = station;
		
		panoramaChkbx = new JCheckBox("Panorama");
		
		panoramaChkbx.setEnabled(false);
		
		chkbx = new JCheckBox("Mediafilen är en skatt");
		
		bt = new JButton("Ok");
		
		bt1 = new JButton();
		
		bt2 = new JButton("Stoppa");
		
		bt2.setEnabled(false);
		
		textStrings = ((JLabel) textPanel.getComponent(componentIndex)).getText().split(":");
		
		
		
				
		if((textStrings[0].contains(".jpg")) || (textStrings[0].contains(".png")) || (textStrings[0].contains(".bmp")))
		{
			
			panoramaChkbx.setEnabled(true);
			bt1.setText("Visa Bilden");
		}
		if((textStrings[0].contains(".mp3")) || (textStrings[0].contains(".aiff")))
		{
			bt1.setText("Spela Upp Ljud");
			
		}
		
		if((textStrings[0].contains(".txt")))
		{
			bt1.setText("Visa");
			
		}
		
		if(textStrings.length==2)
		{	
			if(textStrings[1].contains("Panorama"))
				panoramaChkbx.setSelected(true);
			if(textStrings[1].contains("skatt"))
				chkbx.setSelected(true);
		}
		
		if(textStrings.length==3)
		{	
			if(textStrings[1].contains("Panorama"))
				panoramaChkbx.setSelected(true);
			if(textStrings[2].contains("skatt"))
				chkbx.setSelected(true);
		}
		
		
		p.setLayout(new GridLayout(0,2));
				
		MediaTracker mt = new MediaTracker(this);
		image = new ImageIcon(getClass().getResource("/frame.png")).getImage();
		
		mt.addImage(image,0);
		
		this.frame = frame1;
				
		this.frame.setUndecorated(true);
		
		this.frame.setBackground(new Color(255,255,255));
		
			
		setOpaque(true);
		
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				frame.setVisible(false);
				JLabel lb = (JLabel) textPanel.getComponent(componentIndex);
				lb.setText(textStrings[0]);
				if(panoramaChkbx.isSelected() && !chkbx.isSelected())
				{	
					lb.setText(lb.getText()+":"+"Panorama");
					
				}   
				
				if(chkbx.isSelected())
				{
					if(panoramaChkbx.isSelected())
					{
						lb.setText(lb.getText()+":"+"Panorama"+":"+"skatt");
					}   
					else
						lb.setText(lb.getText()+":"+"skatt");	
				}
				
				currentStation.getLabels().get(componentIndex).setText(lb.getText());
				
				textPanel.revalidate();
				textPanel.repaint();
			}
			
		});

		
		bt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				JLabel lb = (JLabel) textPanel.getComponent(componentIndex);
				if((lb.getText().contains("bmp")) || (lb.getText().contains("png")) || (lb.getText().contains("jpg")))
				{	
				   JDialog frame = new JDialog(mainWindow);
				   MmImageViewer imageViewer = new MmImageViewer(frame,lb.getName());
				   imageViewer.setOpaque(true);
			       frame.setContentPane(imageViewer);
			       frame.pack();
			       frame.setSize(425, 475);
			       frame.setVisible(true);
				}   
				else if((lb.getText().contains(".mp3")) || (lb.getText().contains(".aiff")))
				{
				    audioPlayer.setAudioFile(lb.getName());
				    audioPlayer.audioPlay();
				    bt2.setEnabled(true);
							    
				}
				if((lb.getText().contains(".txt")))
				{	
				   JDialog frame = new JDialog(mainWindow);
				   MmMessageViewer messageViewer = new MmMessageViewer(frame,lb.getName());
				   messageViewer.setOpaque(true);
			       frame.setContentPane(messageViewer);
			       frame.pack();
			       frame.setSize(425, 475);
			       frame.setVisible(true);
				}
				
				
			}
			
		});

		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				audioPlayer.audioStop();
				bt2.setEnabled(false);
			}
			
		}); 
				
		p.add(panoramaChkbx);
		p.add(chkbx);
		p.add(bt);
		p.add(bt1);
		p.add(bt2);
		p.setBorder(BorderFactory.createEtchedBorder());
		add(p);
		
		
	}
	
	
	
	public JPanel getTextPanel() {
		return textPanel;
	}

	public void setTextPanel(JPanel textPanel) {
		this.textPanel = textPanel;
	}

	public int getEventIndex() {
		return eventIndex;
	}

	public void setEventIndex(int eventIndex) {
		this.eventIndex = eventIndex;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void setWindowAlpha(Window w, float alpha) {
		    
			ComponentPeer peer = w.getPeer();
		    if (peer == null) {
		        return;
		    }
		    Class< ? extends ComponentPeer> peerClass = peer.getClass();

		    //noinspection EmptyCatchBlock
		    try {
		        Class< ?> nativeClass = Class.forName("apple.awt.CWindow");
		        if (nativeClass.isAssignableFrom(peerClass)) {
		            Method setAlpha = nativeClass.getMethod(
		                    "setAlpha", float.class);
		            setAlpha.invoke(peer, Math.max(0.0f, Math.min(alpha, 1.0f)));
		      }
		    } catch (ClassNotFoundException e) {
		    } catch (NoSuchMethodException e) {
		    } catch (IllegalAccessException e) {
		    } catch (InvocationTargetException e) {
		    }
	 }
	 
	 public boolean getWindowDisplacment()
	 {
		 return windowDisplaced;
	 }
	 

}

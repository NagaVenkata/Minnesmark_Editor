import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URI;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.vfsjfilechooser.VFSJFileChooser;
import net.sf.vfsjfilechooser.VFSJFileChooser.RETURN_TYPE;
import net.sf.vfsjfilechooser.VFSJFileChooser.SELECTION_MODE;
import net.sf.vfsjfilechooser.accessories.DefaultAccessoriesPanel;

import mmFileManager.*;


import org.apache.commons.vfs2.FileObject;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


import java.util.*;

import mmFileManager.MmFileSelector;

import com.sun.jna.NativeLibrary;


import mmAccordionMenu.*;

import mmMap.MmMapViewer;

public class MmEditorMain extends JFrame implements ActionListener,AWTEventListener{

	/**
	  */
	 
	private static final long serialVersionUID = 1L;
	public JSplitPane splitPane;
	public JPanel panelLeft,panelRight; 
	public JScrollPane scrollPaneLeft,scrollPaneRight;
    public MmAccordionComponent leftAccordion;
    ArrayList<MmAccordionPanel> accordionPanels;
    ArrayList<String> labels = new ArrayList<String>();
    
    public int width;
    
    JPanel accordionPanel;
    
    JFrame frame=null;
    
    int lastFrame,currentFrame;
    
    JMenuBar menuBar;
    
    private MmAccordionMenu accordionMenu;
    
    MmMapViewer map;
    
    String urlLink;
    
    JFrame window;
	
	public MmEditorMain(Dimension dim)
	{
		 menuBar = new JMenuBar();
		 addMenu();
		 splitPane = new JSplitPane();
	     splitPane.setAlignmentX(0);
	     splitPane.setDividerLocation(410);
	     accordionPanels = new ArrayList<MmAccordionPanel>();
	     MmAccordionComponent.width = splitPane.getDividerLocation();
	     width = splitPane.getDividerLocation();
	     
	     
	     
	     window = new JFrame();
	     window.setSize(new Dimension(dim.width,dim.height));
	     
	     accordionMenu = new MmAccordionMenu();
	     
	     accordionMenu.setMainWindow(window);
	     
	     accordionMenu.setFrame();
	     
	     addMainLedInterface();
	     
	     	     
	     
	     window.setJMenuBar(menuBar);
	     window.setTitle("Minnesmark Editor");
	    
	     splitPane.setLeftComponent(scrollPaneLeft);
	     
	     splitPane.setAutoscrolls(true);
	     splitPane.setRightComponent(scrollPaneRight);
	     
	     	     
	     splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY,new PropertyChangeListener(){
	    	
	    	public void propertyChange(PropertyChangeEvent e) {
	    		
	    		MmAccordionComponent.width = splitPane.getDividerLocation();
	    		
	    		for(int i=0;i<accordionPanels.size();i++)
	    		{
	    			accordionPanels.get(i).setWidth(splitPane.getDividerLocation());
	    			accordionPanels.get(i).adjustLayout();
	    			leftAccordion.addBar( "Trial Title",accordionPanels.get(i));
	    		}
	    		
	    		/*leftAccordion.addBar( "One", MMAccordionComponent.getDummyPanel( "One",width ) );
	    		leftAccordion.addBar( "Two", MMAccordionComponent.getDummyPanel( "Two",width ) );
	    		leftAccordion.addBar( "Three", MMAccordionComponent.getDummyPanel( "Three",width ) );
	    		leftAccordion.addBar( "Four", MMAccordionComponent.getDummyPanel( "Four",width ) );
	    		leftAccordion.addBar( "Five", MMAccordionComponent.getDummyPanel( "Five",width ) );*/
	    		//leftAccordion.setVisibleBar( 2 );
	    		
	    		//accordionPanel.setPreferredSize(new Dimension(splitPane.getDividerLocation(),accordionPanel.getHeight()));
	    	}
	    });
	    
	     
	     window.add(splitPane);
	     window.setVisible(true);
	     
	     map.setMainWindow(window);
	     map.showDialog();
	     
	     addMmWindowListener();  
	     
	     
	}
	
	public void addMainLedInterface()
	{
		panelLeft = new JPanel();
		//panelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		//panelLeft.setPreferredSize(new Dimension(splitPane.getDividerSize(),600));
		
		panelRight = new JPanel();
		//java.awt.EventQueue.invokeLater(new Runnable() {
			//public void run() {
			  map = new MmMapViewer();
			  panelRight.setLayout(new FlowLayout());
		      panelRight.add(map);
			//}
		//});	
	        
		      
		    
		
		//panelRight.setPreferredSize(new Dimension(500,600));
		      
		//int width = splitPane.getLeftComponent().getPreferredSize().width;  
		      
		leftAccordion = new MmAccordionComponent(MmAccordionComponent.width-25,40);
		
		
		
				
	    for(int i=0;i<accordionPanels.size();i++)
	    {
	    	leftAccordion.addBar( accordionPanels.get(i).barTitle,accordionPanels.get(i));	
	    }
		
	    
		//leftAccordion.addBar( "Trial Start Events", MmAccordionComponent.getDummyPanel( "Trial Start Events",width ) );
		//leftAccordion.addBar( "Three", MMAccordionComponent.getDummyPanel( "Three",width ) );
		//leftAccordion.addBar( "Four", MMAccordionComponent.getDummyPanel( "Four",width ) );
		//leftAccordion.addBar( "Five", MMAccordionComponent.getDummyPanel( "Five",width ) );
		
		//leftAccordion.setVisibleBar( 0 );
	    
		//panelLeft.add(leftAccordion);
	    
	    JPanel accordionPanel = new JPanel();
	    accordionPanel.setPreferredSize(new Dimension(375,650));
	    /*accordionPanel.setBackground(new java.awt.Color(45, 183, 255));
	    accordionPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	    accordionPanel.setLayout(new javax.swing.BoxLayout(accordionPanel, javax.swing.BoxLayout.LINE_AXIS));*/
	    
	    accordionPanel.setBackground(new java.awt.Color(45, 183, 255));
	    accordionPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, true));
	    accordionPanel.setLayout(new BoxLayout(accordionPanel,BoxLayout.LINE_AXIS));
        panelLeft.add(accordionPanel);
	    
        accordionMenu.setMap(map);
        
        //first menu
        accordionMenu.addMenu("Sök i kartan","search");
        accordionMenu.addTextFieldItem("search",":Sök",25);
        //accordionMenu.addGeoFieldItem("search");
        accordionMenu.addButtonItem("search", "Sök", "ok");
        accordionMenu.addItems("search");
        accordionMenu.buttonActions("search");
        
        
        //second menu
        accordionMenu.addMenu("Välj markör och koppla media till den ","markers");
        ArrayList<JLabel> texts = new ArrayList<JLabel>();
        
        texts.add(addLabel("Markör 1","patt.marker1"));
        texts.add(addLabel("Markör 2","patt.marker2"));
        texts.add(addLabel("Markör 3","patt.marker3"));
        texts.add(addLabel("Markör 4","patt.marker4"));
        texts.add(addLabel("Markör 5","patt.marker5"));
        texts.add(addLabel("Markör 6","patt.marker6"));
        texts.add(addLabel("Markör 7","patt.marker7"));
        texts.add(addLabel("Markör 8","patt.marker8"));
        texts.add(addLabel("Markör 9","patt.marker9"));
        texts.add(addLabel("Markör 10","patt.marker10"));
        texts.add(addLabel("Markör 11","patt.marker11"));
        texts.add(addLabel("Markör 12","patt.marker12"));
        texts.add(addLabel("Markör 13","patt.marker13"));
        texts.add(addLabel("Markör 14","patt.marker14"));
        texts.add(addLabel("Markör 15","patt.marker15"));
        texts.add(addLabel("Markör 16","patt.marker16"));
        texts.add(addLabel("Markör 17","patt.marker17"));
        texts.add(addLabel("Markör 18","patt.marker18"));
        
        
        accordionMenu.markerDialog();
	    accordionMenu.addLabelItems("markers",texts);
	    accordionMenu.addButtonItem("markers", "Lägg till media","ok");
	    accordionMenu.addButtonItem("markers", "Ta bort media","cancel");
	    accordionMenu.addItems("markers");
	    accordionMenu.buttonActions("markers");
	    
       
	    
	    //third menu
	    accordionMenu.addMenu("Definiera händelser vid uppstart av rundvandringen", "start");
	    ArrayList<JLabel> texts1 = new ArrayList<JLabel>();
        texts1.add(addLabel("Lägg till uppstart bild","text"));
        texts1.add(addLabel("Lägg till media som spelas vid uppstart","text"));
        texts1.add(addLabel("Lägg till media som spelas vid uppstart","text"));
        texts1.add(addLabel("Lägg till media som spelas vid uppstart","text"));
        texts1.add(addLabel("","text"));
        texts1.add(addLabel("","text"));
                
        
	    accordionMenu.addLabelItems("start",texts1);
	    accordionMenu.addButtonItem("start", "Lägg till media","ok");
	    accordionMenu.addButtonItem("start", "Ta bort media","cancel");
	    accordionMenu.addItems("start");
	    //accordionMenu.buttonActions("start");
	    accordionMenu.startMenuButtonActions();
	    
	    
	    accordionMenu.backgroundPaint("search",new Color(211, 242, 252));
        accordionMenu.backgroundPaint("markers",new Color(211, 242, 252));
        accordionMenu.backgroundPaint("start",new Color(211, 242, 252));
	    
        
        accordionMenu.setForeground(Color.white);
        
        accordionMenu.setFont(new Font("monospaced", Font.PLAIN, 14));
	    accordionPanel.add(accordionMenu);
	    panelLeft.setOpaque(false);
		scrollPaneLeft = new JScrollPane(panelLeft,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneLeft.setPreferredSize(new Dimension(500,600));
		
		
		
				
		scrollPaneRight = new JScrollPane(panelRight,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneRight.setPreferredSize(new Dimension(500,600));
		
		
		
		
	}
	
	public void addMmWindowListener()
	{
		
         		
		Toolkit.getDefaultToolkit().addAWTEventListener(
				new Listener(map), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
		
		window.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent event) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(null, event.getLocationOnScreen());
				map.bringTofront();
			}

			@Override
			public void mouseEntered(MouseEvent event) {
				// TODO Auto-generated method stub
				System.out.println("data "+event.getLocationOnScreen());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		window.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				window.setState(JFrame.NORMAL);
				//map.showStationEventWindow();
				
				
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
				if(!map.getSavedState())
				{
				   int option = JOptionPane.showConfirmDialog(null, "kankä spara file", "Spara", JOptionPane.YES_NO_CANCEL_OPTION);
				   if(option==JOptionPane.OK_OPTION)
				   {
				       SaveFile();
				    
				       System.exit(0);
				   }    
				}
				
				
				
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				//window.setState(JFrame.ICONIFIED);
				//JOptionPane.showMessageDialog(null, "out of window");
				//map.setFocusable(!map.isFocusable());
				//JOptionPane.showMessageDialog(null, "out of window "+map.isFocusOwner());
				//if(map.isFocusOwner())
			  	
				//map.hideStationEventWindow();
				
				
				
				
			
			}

			@Override
			public void windowDeiconified(WindowEvent event) {
				// TODO Auto-generated method stub
				
				map.showStationEventWindow();
				//window.setState(JFrame.NORMAL);
				
			}

			@Override
			public void windowIconified(WindowEvent event) {
				// TODO Auto-generated method stub
							
				//window.setState(JFrame.ICONIFIED);
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				//window.setState(JFrame.NORMAL);
			}
	    	  
	     });
		
		
		
		
						
	}
	
	@Override
	public void eventDispatched(AWTEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, MouseInfo.getPointerInfo().getLocation());
	}
	
	
	public JLabel addLabel(String text,String name)
	{
		JLabel lb = new JLabel(text);
		lb.setName(name);
		
		return lb;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("get data "+accordionPanels.size()+" "+currentFrame+"  "+lastFrame);
		
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image_add.png"));
		
		for(int i=0;i<accordionPanels.size();i++)
		{
			if(e.getSource()==accordionPanels.get(i).getMinusButton())
			{
				  //accordionPanels.get(i).mouseEvent.removeComponent();
				  accordionPanels.get(i).validate();
				  panelLeft.validate();
				  leftAccordion.validate();
			}
			
			if(e.getSource()==accordionPanels.get(i).getPlusButton())
			{
				System.out.println("frame "+ frame);
				currentFrame=i;
				
				if(frame==null)
				{	
				   frame = new JFrame("Minesmark Events");
				   frame.setPreferredSize(new Dimension(400,400));
				   frame.setAlwaysOnTop(true);
				
				   
				   for(int j=0;j<2;j++)
				   {
					   if(j==0)			
				 	   {
						   labels.clear();
						   System.out.println("entered "+j);
						   labels.add("Trial Title");
						   labels.add("Url");
						   //accordionPanels.get(i).eventsDialog = new MmAccordionTrialDialog(frame,labels,accordionPanels.get(i));
						   accordionPanels.get(j).eventsDialog.setFrame(frame);
						   accordionPanels.get(j).eventsDialog.setLabels(labels);
						   
						   if(i==0)
						   {
							   for(int x=0;x<accordionPanels.get(i).eventsDialog.componentPanel.getLabels().size();x++)
							   {
								  JLabel label = accordionPanels.get(i).eventsDialog.componentPanel.getLabels().get(x);
								  JCheckBox checkBox = accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(x);
								  label.setVisible(true);
								  checkBox.setVisible(true);
								  
							   }
						   }
						   
						   
					   }
					   
					   if(j==1)
					   {
						   labels.clear();
						   System.out.println("entered "+j);
						   labels.add("BackgroundImageLandscape");
						   labels.add("BackgroundImagePortrait");
						   labels.add("BackgroundAudio");
						   labels.add("BackgroundVideo");
						   //accordionPanels.get(i).eventsDialog = new MmAccordionTrialDialog(frame,labels,accordionPanels.get(i));
						   accordionPanels.get(j).eventsDialog.setFrame(frame);
						   accordionPanels.get(j).eventsDialog.setLabels(labels);
						   
						   if(i==1)
						   {
							   for(int x=0;x<accordionPanels.get(i).eventsDialog.componentPanel.getLabels().size();x++)
							   {
								  JLabel label = accordionPanels.get(i).eventsDialog.componentPanel.getLabels().get(x);
								  JCheckBox checkBox = accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(x);
								  label.setVisible(true);
								  checkBox.setVisible(true);
								  
							   }
						   }
						   
						   
					   }   
					   
					   //accordionPanels.get(j).eventsDialog.setOpaque(false);
					   //frame.setContentPane(accordionPanels.get(j).eventsDialog);
				   }
				   
				
				   accordionPanels.get(i).eventsDialog.setOpaque(false);
				   frame.setContentPane(accordionPanels.get(i).eventsDialog);
				   frame.pack();
				   frame.setVisible(true);
				}
				else
				{	
					if(lastFrame!=currentFrame)
					{
						 System.out.println("enterd else part "+currentFrame+" "+lastFrame);	
						 for(int j=0;j<accordionPanels.get(lastFrame).eventsDialog.componentPanel.getLabels().size();j++)
						 {
							  JLabel label = accordionPanels.get(lastFrame).eventsDialog.componentPanel.getLabels().get(j);
							  JCheckBox checkBox = accordionPanels.get(lastFrame).eventsDialog.componentPanel.getCheckBoxes().get(j);
							  System.out.println("label text "+label.getName());
							  label.setVisible(false);
							  checkBox.setVisible(false);
							  label=null;
							  checkBox=null;
						 }
						 
						 for(int j=0;j<accordionPanels.get(lastFrame).eventsDialog.componentPanel.getLabels().size();j++)
						 {
							  accordionPanels.get(lastFrame).eventsDialog.componentPanel.repaint();
							  
						 }
						 
						 for(int j=0;j<accordionPanels.get(currentFrame).eventsDialog.componentPanel.getLabels().size();j++)
						 {
							  accordionPanels.get(currentFrame).eventsDialog.componentPanel.repaint();
							  
						 }
						 
						 accordionPanels.get(currentFrame).eventsDialog.setOpaque(false);
						 frame.setContentPane(accordionPanels.get(currentFrame).eventsDialog);
						 
						 //System.out.println("current frame "+ currentFrame);
						 for(int j=0;j<accordionPanels.get(currentFrame).eventsDialog.componentPanel.getLabels().size();j++)
						 {
							  //JLabel label = accordionPanels.get(currentFrame).eventsDialog.componentPanel.getLabels().get(j);
							   //System.out.println("label text "+accordionPanels.get(currentFrame).eventsDialog.componentPanel.getLabels().get(j).getName());
							   
							  accordionPanels.get(currentFrame).eventsDialog.componentPanel.getLabels().get(j).setVisible(true);
							  accordionPanels.get(currentFrame).eventsDialog.componentPanel.getCheckBoxes().get(j).setVisible(true);
							  //accordionPanels.get(currentFrame).eventsDialog.componentPanel.validate();
							  accordionPanels.get(currentFrame).eventsDialog.repaint();
							  accordionPanels.get(currentFrame).eventsDialog.componentPanel.repaint();
							  //System.out.println("entered data "+ accordionPanels.get(currentFrame).eventsDialog.componentPanel.getLabels().get(j).isVisible());
							  //accordionPanels.get(currentFrame).eventsDialog.componentPanel.validate();
							  //accordionPanels.get(currentFrame).eventsDialog.componentPanel.repaint();
						 }
				     }   
				
					SwingUtilities.updateComponentTreeUI(frame);
					SwingUtilities.updateComponentTreeUI(this);
					frame.validate();
					frame.repaint();
					frame.setVisible(true);
					this.validate();
					this.repaint();
					
					System.out.println(" frame count  "+frame.getComponentCount());
				}	
			}
			
			if(e.getSource()==accordionPanels.get(i).eventsDialog.cancelButton)
			{
				frame.dispose();
				//frame.setVisible(false);
				lastFrame=i;
			}
			
			if(e.getSource()==accordionPanels.get(i).eventsDialog.okButton)
			{
				//for(int x=0;x<accordionPanels.size();x++)
				{	
					for(int j=0;j<accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().size();j++)
					{	
					     System.out.println("check boxs selected "+accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).isSelected());
					}       
				   
				   for(int j=0;j<accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().size();j++)
				   {	
				       System.out.println(accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).isSelected());	
					
				      //JCheckBox checkbox = accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(i);
				      if(accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).isSelected())
				      {
					      //System.out.println(accordionPanels.get(i).rows.get(j).getComponent().getName());
					      System.out.println(accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).getName());
					      if(i==0)
					      {	  
				             accordionPanels.get(i).addText(accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).getName(), 0, j);
				             accordionPanels.get(i).addTextField( 1, j);
				             accordionPanels.get(i).validate();
				             accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).setSelected(false);
				             
					      }
					      
					      if(i==1)
					      {	  
				             accordionPanels.get(i).addText(accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).getName(), 0, j);
				             accordionPanels.get(i).addText(imageIcon, 2, j);
				             accordionPanels.get(i).validate();
				             accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().get(j).setSelected(false);
				             
					      }
				      }   
				     
				   }
				}  
				//accordionPanels.get(i).eventsDialog.componentPanel.getCheckBoxes().clear();
				accordionPanels.get(i).eventsDialog.componentPanel.validate();
				accordionPanels.get(i).eventsDialog.componentPanel.repaint();
				frame.dispose();
				lastFrame=i;
				//frame.setVisible(false);
			}
		}
	}
	
	
	public void resetStartContent()
	{
		
	    
	}
	
	public void addMenu()
	{
		JMenu file = new JMenu("Fil");
		file.setMnemonic(KeyEvent.VK_F);
		JMenu help = new JMenu("Hjälpa");
		help.setMnemonic(KeyEvent.VK_H);
		menuBar.add(file);
		menuBar.add(help);
		JMenuItem newTrail = new JMenuItem("Ny N",KeyEvent.VK_N);
		JMenuItem open  = new JMenuItem("Öpanna O",KeyEvent.VK_O);
		JMenuItem save  = new JMenuItem("Spara S",KeyEvent.VK_S);
		JMenuItem save_as  = new JMenuItem("Spara som");
		JMenuItem print  = new JMenuItem("Skriva ut P",KeyEvent.VK_P);
		JMenuItem print_preview  = new JMenuItem("Print Preview");
		JMenuItem exit  = new JMenuItem("Avsluta E",KeyEvent.VK_E);
		
	
						
		file.add(newTrail);
		file.add(open);
		file.add(save);
		file.add(save_as);
		file.add(print);
		file.add(print_preview);
		file.add(exit);
		
		/*file.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		file.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}
			
		});
		
		help.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		help.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				map.bringTofront();
			}
			
		}); */
		
		newTrail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				/*if(!map.geoPos.isEmpty())
				{
					if(map.getSavedState())
					{	
						accordionMenu.clearContent();
						accordionMenu.getStartEvents().imageEvents.clear();
						accordionMenu.getStartEvents().audioEvents.clear();
						accordionMenu.getStartEvents().videoEvents.clear();
						accordionMenu.getStartEvents().messageEvents.clear();
					    map.resetMapContents();
					}    
					else
					{
						int option = JOptionPane.showConfirmDialog(null, "kankä spara file", "Spara", JOptionPane.YES_NO_CANCEL_OPTION);
						if(option==JOptionPane.OK_OPTION)
						{	
						    JFileChooser saveFile = new JFileChooser();
						    int saveOption = saveFile.showSaveDialog(window);
						    map.hideStationEventWindow();
						    if(saveOption == JFileChooser.APPROVE_OPTION)
						    {
						    	map.showStationEventWindow();
						       //saveFile.setCurrentDirectory(null);
						       //JOptionPane.showMessageDialog(window, saveFile.getSelectedFile().getName()+" "+saveFile.getSelectedFile().getPath());			
						       map.createJSONFile(saveFile.getSelectedFile().toString(),saveFile.getSelectedFile().getPath(),accordionMenu.getGlobalMarkerEvents(),accordionMenu.getStartEvents());
						       map.setSaved(true);
						       accordionMenu.clearContent();
						       resetStartContent();
							   accordionMenu.getStartEvents().imageEvents.clear();
							   accordionMenu.getStartEvents().audioEvents.clear();
							   accordionMenu.getStartEvents().videoEvents.clear();
							   accordionMenu.getStartEvents().messageEvents.clear();
						       map.resetMapContents();
						    }   
						    
						    
						}
						
						if(option==JOptionPane.NO_OPTION)
						{
							//accordionMenu.getGlobalMarkerEvents().clear();
							accordionMenu.clearContent();
							resetStartContent();
							accordionMenu.getStartEvents().imageEvents.clear();
							accordionMenu.getStartEvents().audioEvents.clear();
							accordionMenu.getStartEvents().videoEvents.clear();
							accordionMenu.getStartEvents().messageEvents.clear();
						    map.resetMapContents();
						}
						
					}
				}*/
				
				int option = JOptionPane.showConfirmDialog(null, "kankä spara file", "Spara", JOptionPane.YES_NO_CANCEL_OPTION);
				if(option==JOptionPane.OK_OPTION)
				{
				    SaveFile();
				    
				    map.resetMapContents();
				}	
				
				if(option==JOptionPane.NO_OPTION)
				{
					//accordionMenu.getGlobalMarkerEvents().clear();
					accordionMenu.clearContent();
					resetStartContent();
					accordionMenu.getStartEvents().imageEvents.clear();
					accordionMenu.getStartEvents().audioEvents.clear();
					accordionMenu.getStartEvents().videoEvents.clear();
					accordionMenu.getStartEvents().messageEvents.clear();
				    map.resetMapContents();
				}
			}
			
		});
		
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				//map.hideStationEventWindow();
				
				final VFSJFileChooser vfs1 = new VFSJFileChooser();
			     
			     vfs1.setAccessory(new DefaultAccessoriesPanel(vfs1));
			     vfs1.setFileHidingEnabled(false);
			     vfs1.setMultiSelectionEnabled(false);
			     vfs1.setFileSelectionMode(SELECTION_MODE.FILES_AND_DIRECTORIES);
			     
			    
			        
			     vfs1.addChoosableFileFilter(new MmFileFilter());
			     
			     vfs1.setFileFilter(new MmFileFilter());

			     // show the file dialog
			     RETURN_TYPE answer = vfs1.showOpenDialog(null);
				
				JFileChooser openFile = new JFileChooser();
				
				openFile.setAcceptAllFileFilterUsed(false);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JSON files", "json");
				
				openFile.addChoosableFileFilter(filter);
				openFile.setFileFilter(filter);
				
				int option = openFile.showOpenDialog(null);
				
				
                				
				if(option == JFileChooser.APPROVE_OPTION)
				{
					map.showStationEventWindow();
				    map.readJSONFileContents(openFile.getSelectedFile().getAbsolutePath(),accordionMenu);
				    map.setSaved(false);
				}    
				
				if(option == JFileChooser.CANCEL_OPTION)
				{
					map.showStationEventWindow();
				}
				
				
			}
			
		});
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SaveFile();
				
			}
			
		});
		
		save_as.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				map.hideStationEventWindow();
				
                JFileChooser saveFile = new JFileChooser();
				
				saveFile.setAcceptAllFileFilterUsed(false);
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JSON files", "json");
				
				saveFile.addChoosableFileFilter(filter);
				saveFile.setFileFilter(filter);
				
				int option = saveFile.showSaveDialog(null);
				
				//JOptionPane.showMessageDialog(null, "file name "+saveFile.getSelectedFile().getName());
				
				
				
				if(option == JFileChooser.APPROVE_OPTION)
				{	
					
					map.showStationEventWindow();
					String fileName = saveFile.getSelectedFile().toString();
					
					
					if(!fileName.contains(".json"))
					{	
						fileName = fileName+".json";
						
					}	
					
					File file = new File(fileName);
					
					String fileName1 = file.getName();
					
					//JOptionPane.showMessageDialog(null, fileName1);
					
					int fileIndex = fileName1.indexOf(".");
					
					fileName1 = fileName1.substring(0, fileIndex);
					
					
					//JOptionPane.showMessageDialog(null, "selected file "+saveFile.getSelectedFile()+"  "+file.getName());
					
					
					File dirFile = new File(saveFile.getSelectedFile().getParent()+"/"+fileName1);
					
					File save_file = new File(saveFile.getSelectedFile()+"/"+file.getName());
					saveFile.setSelectedFile(save_file);
					
					//JOptionPane.showMessageDialog(null, saveFile.getSelectedFile()+"  "+dirFile.isDirectory()+"  "+dirFile.getName()+" "+save_file.exists()+"  "+save_file.getPath());
					
					if(dirFile.isDirectory() && save_file.exists())
					{
						//File jsonFile = new File(dirFile.getName());
						int fileOption = JOptionPane.showConfirmDialog(null, "Filen finns redan. Vill du ersätta filen?","Spara" ,JOptionPane.YES_NO_OPTION);
						if(fileOption == JOptionPane.YES_OPTION)
						{
							map.createJSONFile(save_file.getPath(),(dirFile.getAbsolutePath().toString()),accordionMenu.getGlobalMarkerEvents(),accordionMenu.getStartEvents());
						    
						}
					}
					else
					{
						if(!dirFile.isDirectory())
						      dirFile.mkdir();
						map.createJSONFile(save_file.getPath(),(dirFile.getAbsolutePath().toString()),accordionMenu.getGlobalMarkerEvents(),accordionMenu.getStartEvents());
					    
					}
					
						
									   
				}   
				
				
				
				if(option == JFileChooser.CANCEL_OPTION)
				{	
					
					map.showStationEventWindow();
				}
				
				
			}
			
		});
		
		print.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event)
			{
				map.isPrintSelected=true;
				map.printMap();
				map.isPrintSelected=false;
				map.drawPoints();
			}
		});
		
		print_preview.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event)
			{
				JOptionPane.showMessageDialog(map, map);
			}
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				if(map.getSavedState())
				{	
					accordionMenu.clearContent();
					accordionMenu.getStartEvents().imageEvents.clear();
					accordionMenu.getStartEvents().audioEvents.clear();
					accordionMenu.getStartEvents().videoEvents.clear();
					accordionMenu.getStartEvents().messageEvents.clear();
					System.exit(0);
				}    
				else
				{
					int option = JOptionPane.showConfirmDialog(null, "kankä spara file", "Spara", JOptionPane.YES_NO_CANCEL_OPTION);
					if(option==JOptionPane.OK_OPTION)
					{	
					    JFileChooser saveFile = new JFileChooser();
					    int saveOption = saveFile.showSaveDialog(window);
					    map.hideStationEventWindow();
					    if(saveOption == JFileChooser.APPROVE_OPTION)
					    {
					    	map.bringTofront();
					       //saveFile.setCurrentDirectory(null);
					       //JOptionPane.showMessageDialog(window, saveFile.getSelectedFile().getName()+" "+saveFile.getSelectedFile().getPath());			
					       map.createJSONFile(saveFile.getSelectedFile().toString(),saveFile.getSelectedFile().getPath(),accordionMenu.getGlobalMarkerEvents(),accordionMenu.getStartEvents());
					       map.setSaved(true);
					       accordionMenu.clearContent();
					       resetStartContent();
						   accordionMenu.getStartEvents().imageEvents.clear();
						   accordionMenu.getStartEvents().audioEvents.clear();
						   accordionMenu.getStartEvents().videoEvents.clear();
						   accordionMenu.getStartEvents().messageEvents.clear();
						   System.exit(0);
					    }   
					    
					    
					}
					
					if(option==JOptionPane.NO_OPTION)
					{
						//accordionMenu.getGlobalMarkerEvents().clear();
						accordionMenu.clearContent();
						resetStartContent();
						accordionMenu.getStartEvents().imageEvents.clear();
						accordionMenu.getStartEvents().audioEvents.clear();
						accordionMenu.getStartEvents().videoEvents.clear();
						accordionMenu.getStartEvents().messageEvents.clear();
					    System.exit(0);
					}
					
				}
				
			}
			
		});
		
		
		JMenuItem editorHelp = new JMenuItem("Demo  D",KeyEvent.VK_D);
		
		help.add(editorHelp);
		
		editorHelp.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
				//window.setState(JFrame.ICONIFIED);
				
				try
				{
				   Desktop.getDesktop().browse(new URI("http://www.ida.liu.se/~nagta58/MinnesmarkDemo/Minnesmark.html"));
				}
				catch(Exception e)
				{
					
				}
			}
		});
		
	}
	
	public void SaveFile()
	{
		map.hideStationEventWindow();
		
        JFileChooser saveFile = new JFileChooser();
		
		saveFile.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JSON files", "json");
		
		saveFile.addChoosableFileFilter(filter);
		saveFile.setFileFilter(filter);
		
		int option = saveFile.showSaveDialog(null);
		
		//JOptionPane.showMessageDialog(null, "file name "+saveFile.getSelectedFile().getName());
		
		
		if(option == JFileChooser.APPROVE_OPTION)
		{	
			map.showStationEventWindow();
			String fileName = saveFile.getSelectedFile().toString();
						
			if(!fileName.contains(".json"))
			{	
				fileName = fileName+".json";
				
			}	
			
			File file = new File(fileName);
			
			String fileName1 = file.getName();
			
			//JOptionPane.showMessageDialog(null, fileName1);
			
			int fileIndex = fileName1.indexOf(".");
			
			fileName1 = fileName1.substring(0, fileIndex);
			
			
			//JOptionPane.showMessageDialog(null, "selected file "+saveFile.getSelectedFile()+"  "+file.getName());
			
			
			File dirFile = new File(saveFile.getSelectedFile().getParent()+"/"+fileName1);
			
			File save_file = new File(saveFile.getSelectedFile()+"/"+file.getName());
			saveFile.setSelectedFile(save_file);
			
			//JOptionPane.showMessageDialog(null, saveFile.getSelectedFile()+"  "+dirFile.isDirectory()+"  "+dirFile.getName()+" "+save_file.exists()+"  "+save_file.getPath());
			
			if(dirFile.isDirectory() && save_file.exists())
			{
				//File jsonFile = new File(dirFile.getName());
				int fileOption = JOptionPane.showConfirmDialog(null, "Filen finns redan. Vill du override","Spara" ,JOptionPane.YES_NO_OPTION);
				if(fileOption == JOptionPane.YES_OPTION)
				{
					map.createJSONFile(save_file.getPath(),(dirFile.getAbsolutePath().toString()),accordionMenu.getGlobalMarkerEvents(),accordionMenu.getStartEvents());
				    map.setSaved(true);
				}
			}
			else
			{
				if(!dirFile.isDirectory())
				      dirFile.mkdir();
				map.createJSONFile(save_file.getPath(),(dirFile.getAbsolutePath().toString()),accordionMenu.getGlobalMarkerEvents(),accordionMenu.getStartEvents());
			    map.setSaved(true);
			}
							   
		}   
		
		if(option == JFileChooser.CANCEL_OPTION)
		{	
			
			map.showStationEventWindow();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), System.getProperty("user.dir") + File.separator + "lib" + File.separator + "VLC.app" + File.separator + "Contents"+  File.separator + "MacOS"+File.separator + "lib");
        System.setProperty("jna.library.path", System.getProperty("user.dir") + File.separator + "lib" + File.separator + "VLC" + File.separator + "lib");
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        Insets ins = Toolkit.getDefaultToolkit().getScreenInsets(gc);
        
        
        
        int sw = gc.getBounds().width - ins.left - ins.right;
        int sh = gc.getBounds().height - ins.top - ins.bottom;
		
		//Toolkit toolKit = Toolkit.getDefaultToolkit();
		
		new MmEditorMain(new Dimension(sw,sh));
		
		 
             
	}

	
	private static class Listener implements AWTEventListener {
		
		MmMapViewer map1;
		boolean focused = true;
		static AWTEvent prevEvent;
	    
		
        public Listener(MmMapViewer map)
        {
        	map1 = map;
        	focused = true;
        }
        public void eventDispatched(AWTEvent event) {
            
            if(MouseInfo.getPointerInfo().getLocation().y<75 && event.toString().contains("FOCUS_LOST"))
            {
            	 //System.out.print(MouseInfo.getPointerInfo().getLocation() + " | ");
            	 //System.out.print("data  "+event);
            	 map1.bringTofront();
            }
            
            if((MouseInfo.getPointerInfo().getLocation().x<400 && MouseInfo.getPointerInfo().getLocation().y>75) && event.toString().contains("FOCUS_LOST"))
            {
            	 //System.out.print(MouseInfo.getPointerInfo().getLocation() + " | "+"\n");
            	 //JOptionPane.showMessageDialog(null, "Entered");
            	 System.out.print("data1  "+event);
            	 map1.bringTofront();
            }
            
            
            //System.out.println(event.toString());
            //map1.bringTofront();
            //System.out.println("event  " +event.getID());
            
            //int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            
            //System.out.println("screen height "+screenHeight);
            
            /*if(event.toString().contains("FOCUS_LOST") && (focused) && (MouseInfo.getPointerInfo().getLocation().y>700))
            {
            	//JOptionPane.showMessageDialog(null, "Exit");
            	map1.hideStationEventWindow();
            	focused=false;
            	Listener.prevEvent = event;
            }
            
            /*if((MouseInfo.getPointerInfo().getLocation().y>750))
            {
            	
            	
            	if(prevEvent!=null && prevEvent.toString().contains("FOCUS_GAINED"))
            	{
            		System.out.println("mouse position  "+MouseInfo.getPointerInfo().getLocation());
            		System.out.println("prev event "+prevEvent);
            		map1.showStationEventWindow();
            	}
            	
            	/*if(event.toString().contains("FOCUS_LOST") && (focused) && map1.isStationEventWindowHidden())
            	{
            		//System.out.println("mouse position show "+MouseInfo.getPointerInfo().getLocation());
            		map1.hideStationEventWindow();
            		focused=false;
            		
            	}
            	
            	prevEvent = event;
            	   
            }
            
            if(event.toString().contains("FOCUS_GAINED") && (!focused) && (MouseInfo.getPointerInfo().getLocation().y>700))
            {	
            	map1.showStationEventWindow();
            	//JOptionPane.showMessageDialog(null, "Enter");
            	focused = true;
            	
            	if(prevEvent!=null && prevEvent.toString().contains("FOCUS_GAINED") && (MouseInfo.getPointerInfo().getLocation().y>750))
            	{
            		map1.showStationEventWindow();
            		
            	}
            	
            	Listener.prevEvent = event;
            }*/ 	
            	
            
        }
        
        
    }
	
	
	

}


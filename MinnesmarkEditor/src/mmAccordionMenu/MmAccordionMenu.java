package mmAccordionMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.channels.FileChannel;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.*;

import org.jdesktop.swingx.mapviewer.GeoPosition;

import mmEvents.MmAddGlobalMarkers;
import mmLanguage.MmLanguage;
import mmMap.MmMapViewer;
import mmStationEvents.*;




public class MmAccordionMenu extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

		
	private List<MmAccordionMainItem> mainItems;
	
	
	JPanel selectedPanel = new JPanel();
	
	int menuHeight,menuItemHeight;
	
	MmAccordionMainItem menu;
	
	int current_index=0;
	
	MmMapViewer map;
	
	public MmAddGlobalMarkers markerEvents;
	
	JDialog frame1; 
	
	
	String text;
	
	MmStartEvents startEvents;
	
	JFrame mainWindow;
	
	int language;
	 
	
	public MmAccordionMenu()
	{
		this.setLayout(null);
		
		this.mainItems = new ArrayList<MmAccordionMainItem>();
		menuHeight=0;
		menuItemHeight=0;
		
		startEvents = new MmStartEvents();
		
	}
	
	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public JFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	
   public void setFrame()
   {
	   frame1 = new JDialog(mainWindow);
   }

    
	public void markerDialog()
	{
        
		
		markerEvents = new MmAddGlobalMarkers(frame1);
		
		markerEvents.setMainWindow(mainWindow);
	    
	    markerEvents.setOpaque(true);
	    
	    frame1.pack();
	    frame1.setSize(425, 150);
	    
	    frame1.setVisible(false);
	}
		
	
	public void addMenu(String text,String menuName)
	{
		
		MmAccordionMainItem menu1 = addAccordionMenuItem(text,menuName);
		menu1.addMouseListener(getDefaultMainMenuItemMouseAdapter());
		menu1.getItemPanel().setVisible(false);
		menu1.getSelectedIcon();
		add(menu1.getItemPanel());
		this.mainItems.add(menu1); 
			
	}
	
	public MmAccordionMainItem addAccordionMenuItem(String text,String menuName)
	{
		MmAccordionMainItem menu = new MmAccordionMainItem(text);
		menu.setName(menuName);
		add(menu);
		return menu;
	}
	
	
	public MmAccordionMainItem accordionItem(String text)
	{
		MmAccordionMainItem item = new MmAccordionMainItem(text);
		item.setName(text);
		add(item);
		return item;
	}
	
	
	
	public void addButtonItem(String menu,String text,String name)
	{
		MmAccordionMenuItems items = new MmAccordionMenuItems();
		items.addButtonItem(text,name);
		for(MmAccordionMainItem item: getMenuItems())
        {
			if(item.getName().equals(menu))
			{	
		       item.getItemPanel().addButtonPanel(items.getButton());
		       item.getItemPanel().updateUI();
		       item.getMenuItem().add(items);
			}   
        }    
		
		
	}
	
	public void addLabelItem(String menu,String text)
	{
		MmAccordionMenuItems items = new MmAccordionMenuItems();
		items.addLabelItem(text);
	
		for(MmAccordionMainItem item: getMenuItems())
        {
			if(item.getName().equals(menu))
			{
	 	        //items.setName(text);
		        item.getItemPanel().addMenuItem(items.getLabel());
		        item.getItemPanel().updateUI();
		        item.getMenuItem().add(items);	
			}
        }	
			
	}
	
	
	
	public void addLabelItems(String menu,ArrayList<JLabel> texts)
	{
		MmAccordionMenuItems items = new MmAccordionMenuItems();
		items.setLanguage(language);
		
		if(menu.equals("markers"))
		{
		  items.events = markerEvents;
		}  
		
		for(int i=0;i<texts.size();i++)
			items.addLabelItems(texts.get(i),menu);
	   System.out.println("Size "+texts.size());
		for(MmAccordionMainItem item: getMenuItems())
        {
			if(item.getName().equals(menu))
			{
			    item.getItemPanel().addMenuItem(items.getScrollPaneItem());
				item.getItemPanel().updateUI();
		        item.getMenuItem().add(items);	
			}
        }	
		
		
	}
	
		
	public void setMap(MmMapViewer map1)
	{
		map = map1;
	}
	
	
	public void addItems(String menu)
	{
		
		for(MmAccordionMainItem item: getMenuItems())
        {
			

			if(item.getName().equals("search"))
			{
	 	      
				item.getItemPanel().addPanel();
				item.getItemPanel().updateUI();
		       	
			}

			
			if(item.getName().equals("markers"))
			{
	 	      
				item.getItemPanel().addPanel();
				item.getItemPanel().updateUI();
		       	item.getMenuItem().get(0).addLabelItemActions();
			}
			
			if(item.getName().equals("start"))
			{
	 	       
				item.getItemPanel().addPanel();
				item.getItemPanel().updateUI();
		       	item.getMenuItem().get(0).addLabelItemActions();
		       	
			}
        }	
		
		
	}
	
	
	public void addLabelIconItem(String menu,String text)
	{
		MmAccordionMenuItems items = new MmAccordionMenuItems();
		items.addLabelIconItem(text);
		items.getLabel().addMouseListener(getMenuItemMouseAdapter());
		
		for(MmAccordionMainItem item: getMenuItems())
        {
			if(item.getName().equals(menu))
			{
	 	        item.getItemPanel().addMenuItem(items.getLabel());
		        item.getItemPanel().updateUI();
		        item.getMenuItem().add(items);
		        this.selectedPanel = item.getItemPanel();
			}
        }	
		
		
	}
	
	public void addTextFieldItem(String menu,String text,int length)
	{
		MmAccordionMenuItems items = new MmAccordionMenuItems();
		items.addTextFieldItem(text,length);
		items.addGeoFields();
		items.setMap(map);
		for(MmAccordionMainItem item: getMenuItems())
        {
			if(item.getName().equals(menu))
			{
		  		item.getItemPanel().addMenuItem(items.getSearchItemPanel());
		        item.getItemPanel().updateUI();
		        item.getMenuItem().add(items);
			}
        }	
		
	}
	
	/*public void addGeoFieldItem(String menu)
	{
		MmAccordionMenuItems items = new MmAccordionMenuItems();
		items.addGeoFields();
		for(MmAccordionMainItem item: getMenuItems())
        {
			if(item.getName().equals(menu))
			{
		  		item.getItemPanel().addMenuItem(items.getSearchItemPanel());
		        item.getItemPanel().updateUI();
		        item.getMenuItem().add(items);
			}
        }		
		
	}*/
	
	
	
	//returns the text of the corresponding menus textfields text
	public String getTextFieldText(String menu)
	{
		String textFieldText = null;
		for(MmAccordionMainItem item: getMenuItems())
        {
			if(item.getName().equals("start"))
			{
				textFieldText =  item.getMenuItem().get(0).getTextFieldsText();
				break;
			}
        }
		
		return textFieldText;
	}
	
	
	
	public void buttonActions(String menu)
	{
		
		for(final MmAccordionMainItem item: getMenuItems())
        {
			
			if(item.getName().equals("search"))
			{
				
		  	     JButton bt = item.getMenuItem().get(1).getButtonItem();
		  	    
		  	      bt.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {
						// TODO Auto-generated method stub
						
					   map.getMap().getMainMap().setAddressLocation(new GeoPosition(item.getMenuItem().get(0).getLatitude(),item.getMenuItem().get(0).getLongitude()));	
					   
					       		      
					}
		  	    	
		  	    });
			}     
			
			if(item.getName().equals("markers"))
			{
				
		  	     JButton bt = item.getMenuItem().get(1).getButtonItem();
		  	    
		  	      bt.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {
						// TODO Auto-generated method stub
					   int index = item.getMenuItem().get(0).getMarkerSelected(); 	
					   text =  item.getMenuItem().get(0).getMarkerSelectedText();
					   frame1.setLocation(5,100);
        		       frame1.setVisible(true);
        		       //JOptionPane.showMessageDialog(null, text);
        		       markerEvents.addStation(text,index);
        		       markerEvents.setLabel(item.getMenuItem().get(0).getMarkerSelectedLabel());
        		       item.getMenuItem().get(0).updatePanel();
					       		      
					}
		  	    	
		  	    });
		  	      
		  	    
		  	    
		  	    JButton bt1 = item.getMenuItem().get(2).getButtonItem();
		  	    
		  	    bt1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
					    
						int markerIndex = -1;
						if(!markerEvents.getStations().isEmpty())
						{
							int index = item.getMenuItem().get(0).getMarkerSelected();
							
							String selectText = new String();
							
							selectText = item.getMenuItem().get(0).getMarkerSelectedText();
							//JOptionPane.showMessageDialog(null, item.getMenuItem().get(0).getMarkerSelectedText());
							
							selectText = convertString(selectText);
																					
							for(int i=0;i<markerEvents.getStations().size();i++)
							{
								if(markerEvents.getStations().get(i).getMediaText().contains(":"))
								{
									String[] strngs = markerEvents.getStations().get(i).getMediaText().split(":");  
									
									
									String markerText = new String();
									markerText = markerEvents.getStations().get(i).getMediaText();
									markerText = convertString(markerText);
									
									selectText = item.getMenuItem().get(0).getMarkerSelectedLabel().getText();
									
									selectText = convertString(selectText);
									
									if(markerText.equals(selectText))
									{
										markerEvents.getStations().remove(i);
										item.getMenuItem().get(0).getMarkerSelectedLabel().setText(strngs[0]);
										markerEvents.setLabel(item.getMenuItem().get(0).getMarkerSelectedLabel());
										break;
									}
								}
							}
							
							item.getMenuItem().get(0).updatePanel();
							
							/*String[] texts = markerEvents.getStations().get(index).getMediaText().split(":");
							
							if(texts.length==2)
							{
								String text = markerEvents.getStations().get(index).getMediaText().replace(":"+texts[1], "");
								item.getMenuItem().get(0).setMarkerSelectedText(text);
								markerEvents.getStations().get(index).setMediaText(text);
							}
							
							markerEvents.getStations().remove(index);*/
						}
						
					}
		  	    	
		  	    });
			}
			
			
        }
	}
	
	public void setLanguageText(String text,String menu)
	{
		if(this.mainItems.get(0).getName().equals(menu))
		{
			this.mainItems.get(0).setLanguageText(MmLanguage.language[language][2]);
			JButton bt = this.mainItems.get(0).getMenuItem().get(1).getButtonItem();
			bt.setText(MmLanguage.language_button[language][0]);
		}
		
		if(this.mainItems.get(1).getName().equals(menu))
		{
			this.mainItems.get(1).setLanguageText(MmLanguage.language[language][3]);
			JButton bt = this.mainItems.get(1).getMenuItem().get(1).getButtonItem();
			bt.setText(MmLanguage.language_button[language][1]);
			JButton bt1 = this.mainItems.get(1).getMenuItem().get(2).getButtonItem();
			bt1.setText(MmLanguage.language_button[language][2]);
		}
		
		if(this.mainItems.get(2).getName().equals(menu))
		{
			this.mainItems.get(2).setLanguageText(MmLanguage.language[language][4]);
			JButton bt = this.mainItems.get(2).getMenuItem().get(1).getButtonItem();
			bt.setText(MmLanguage.language_button[language][1]);
			JButton bt1 = this.mainItems.get(2).getMenuItem().get(2).getButtonItem();
			bt1.setText(MmLanguage.language_button[language][2]);
		}
		
		markerEvents.setLanguage(language);
		markerEvents.setLanguageText();
	}
	
	public void setSearchText(String text)
	{
		this.mainItems.get(0).getMenuItem().get(0).getTextField().setText(text);
	}
	
	public void setMarkersText(ArrayList<String> texts)
	{
		
		this.mainItems.get(1).getMenuItem().get(0).setMarkersText(texts);
	}
	
	public void setStartTexts(ArrayList<String> texts)
	{
		
		this.mainItems.get(2).getMenuItem().get(0).setMarkersText(texts);
	}
	
	
	
	
	public String convertString(String string) {
		
	    string = Normalizer.normalize(string, Normalizer.Form.NFD);
	    string = string.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	    
	    return string;
	}
	
	public void addMenuItem(String menu,String itemName,String path,int index)
	{
	
		//JOptionPane.showMessageDialog(null, path);
		for(final MmAccordionMainItem item: getMenuItems())
        {
						
		    if(item.getName().equals("start"))
		    {
		    	JLabel lb  =  item.getMenuItem().get(0).getLabels().get(index);
		    	lb.setText(itemName);
		    	lb.setName(path);
		    	break;
		    }
        }    
	}
	
	public void addMarkerMenuItem(String menu,String itemName,int index)
	{
		for(final MmAccordionMainItem item: getMenuItems())
        {
			
		    if(item.getName().equals(menu))
		    {
		        JLabel lb  =  item.getMenuItem().get(0).getLabels().get(index);
		        lb.setText(lb.getText()+":"+itemName+" Mediefiler");
		        lb.setName(lb.getName());
		        //item.getMenuItem().get(0).getScrollPaneItem().revalidate();
		        break;
		    }
        }    
	}
	
	
	public void startMenuButtonActions()
	{
		for(final MmAccordionMainItem item: getMenuItems())
        {
		    if(item.getName().equals("start"))
		    {
	  	        JButton bt = item.getMenuItem().get(1).getButtonItem();
	  	    
	  	        bt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					int index = item.getMenuItem().get(0).getMarkerSelected();
					
					JLabel lb  =  item.getMenuItem().get(0).getLabels().get(index);
					
				    JFileChooser openFile = new JFileChooser();
					int option = openFile.showOpenDialog(null);
						
					if(option == JFileChooser.APPROVE_OPTION)
					{
						   
						if(index==0)
						{
							String string = openFile.getSelectedFile().getName(); 
							
							if((string.contains(".jpg"))||(string.contains(".png"))||(string.contains(".bmp")))
							{
								lb.setOpaque(true);
								lb.setForeground(Color.black);
								lb.setBackground(Color.white);
								lb.setText(openFile.getSelectedFile().getName());
								lb.setName(openFile.getSelectedFile().getName());
								startEvents.addText(openFile.getSelectedFile().getName(), openFile.getSelectedFile().getAbsolutePath());
							}
							else
							{
								JOptionPane.showMessageDialog(null, MmLanguage.language_mediaevents[language][11]);
							}
						}
						else
						{	
						   lb.setOpaque(true);
						   lb.setForeground(Color.black);
						   lb.setBackground(Color.white);
						   lb.setText(openFile.getSelectedFile().getName());
						   lb.setName(openFile.getSelectedFile().getName());
						   startEvents.addText(openFile.getSelectedFile().getName(), openFile.getSelectedFile().getAbsolutePath());
						   
				        }
				    	
					}	
				    
				}
	  	    	
	  	    });
	  	    
	  	    JButton bt1 = item.getMenuItem().get(2).getButtonItem();
	  	    
	  	    bt1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					int index = item.getMenuItem().get(1).getMarkerSelected();
					
					item.getMenuItem().get(1).removeSelectedItem(index);
					
				    	
				}
	  	    	
	  	    });
	  	  }
        }	    
	}
	
	 public void backgroundPaint(String menu,Color c) {
		 for(MmAccordionMainItem item: getMenuItems())
	     {
			if(item.getName().equals(menu))
			{
	           item.setBackground(c);
			}
	     }	
	 } 
	 
	 public ArrayList<MmGlobalMarkerEvents> getGlobalMarkerEvents()
	 {
		 return markerEvents.getStations();
	 }
	 
	 public MmAddGlobalMarkers getMarkers()
	 {
		 return markerEvents;
	 }
	 
	 public void printMarker()
	 {
		 try
		 {
			PrintRequestAttributeSet printAttrs = new HashPrintRequestAttributeSet();
			
			printAttrs.add(new Copies(1));
			
			PrintService pntSer[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PNG,printAttrs);
			
			PrintService ptrSer = pntSer[0];
			
			DocPrintJob print = ptrSer.createPrintJob();
			
			FileInputStream ins = new FileInputStream("Users/Umapathi/Desktop/MinnesmarkEditor/marker1.png"); 
			
			Doc doc = new SimpleDoc(ins, DocFlavor.INPUT_STREAM.PNG, null);
		    print.print(doc, printAttrs);
		    ins.close();
			
		 }
		 catch(Exception e)
		 {
			 
		 }
	 }
	 
	 public void clearContent()
	 {
		 
		 //markerEvents.getStations().clear();
		 
		 for(final MmAccordionMainItem item: getMenuItems())
	     {
			if(item.getName().equals("search"))
			{
				item.getMenuItem().get(0).getTextField().setText("");
			}
			
			if(item.getName().equals("markers"))
			{
				markerEvents.getStations().clear();
				item.getMenuItem().get(0).resetContent();
			}
			
			if(item.getName().equals("start"))
			{
				item.getMenuItem().get(0).resetStartContent();
			}
			
	     } 		
	 }
	 
	 public MmAddGlobalMarkers getGlobalMarkers()
	 {
		 return markerEvents;
	 }
	 
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        int y=0;
	        
	       	        
	        menuHeight=40;
	        menuItemHeight=40;
	        
	        for(int i=0;i<mainItems.size();i++)
	        {
	        	mainItems.get(i).setSize(this.getWidth(),menuHeight);
	        	mainItems.get(i).setLocation(0, y);
	        	
	        	if(mainItems.get(i).getMenuItem().size()!=0 && mainItems.get(i).selected)
	        	{
	        	   y+=40;
	        	   if(mainItems.get(i).getMenuItem().size()==2 && mainItems.get(i).getMenuItem().get(0).getLabels().isEmpty())
	        	   {
	        		   int menuItemsSize= mainItems.get(i).getMenuItem().size();
	        		   mainItems.get(i).getItemPanel().setLocation(0, y);
	        		   mainItems.get(i).getItemPanel().setVisible(true);
	        		   mainItems.get(i).getItemPanel().setSize(this.getWidth()-5,(menuItemsSize+4)*40);
	        		   y+=(menuItemsSize+4)*40;
	        	   }
	        	   else if(mainItems.get(i).getMenuItem().get(0).getLabels().size()==18)
	        	   {	
	        		  //System.out.println("data1 "+mainItems.get(i).getMenuItem().size()); 
	        		  int menuItemsSize= mainItems.get(i).getMenuItem().get(0).getLabels().size()-4;
	        		  mainItems.get(i).getItemPanel().setVisible(true); 
	        	      mainItems.get(i).getItemPanel().setSize(this.getWidth()-5,(menuItemsSize)/2*40);
	        	      
	        	      mainItems.get(i).getItemPanel().setLocation(5, y+5);
		        	   y+=(menuItemsSize)/2*40;
	        	   }
	        	   else if(!mainItems.get(i).getMenuItem().get(0).getLabels().isEmpty())
	        	   {	
	        		  //System.out.println("data2 "+mainItems.get(i).getMenuItem().size()); 
	        		  int menuItemsSize= mainItems.get(i).getMenuItem().get(1).getLabels().size()+8;
	        		  mainItems.get(i).getItemPanel().setVisible(true); 
	        	      mainItems.get(i).getItemPanel().setSize(this.getWidth()-5,(menuItemsSize)/2*40);
	        	      
	        	      mainItems.get(i).getItemPanel().setLocation(5, y+5);
		        	   y+=(menuItemsSize)/2*40;
	        	   }
	        	   
	        	   
	        	}
	        	else
	        	{
	        		y+=40;
	        	}
	        }
	        
	       
	        
	 }
	 
	 private MouseAdapter getMenuItemMouseAdapter() {
	        return new MouseAdapter() {

	            @Override
	            public void mousePressed(MouseEvent e) {
	            	JLabel label = (JLabel) e.getSource();
	            	
	            	JFileChooser fileChooser = new JFileChooser();
	            	int val = fileChooser.showDialog(MmAccordionMenu.this, "open");
	     	        if (val == JFileChooser.APPROVE_OPTION) {
	     	    	   File file = fileChooser.getSelectedFile();
	     	  	       label.setText(file.getName());
	     	  	       System.out.println(label.getName());
	     	  	       uploadFile(file,label.getName());
	     	       }  
	               
	            }
	        };
 }
	 
	 private MouseAdapter getDefaultMainMenuItemMouseAdapter() {
	        return new MouseAdapter() {

	            @Override
	            public void mousePressed(MouseEvent e) {
	                MmAccordionMainItem item = (MmAccordionMainItem) e.getSource();
	                
	                System.out.println(" selected item "+item);
	                item.selected = !item.selected;
	                item.setSelectedMenuIcon();
	                for(int i=0;i<mainItems.size();i++)
	                {	
	                	mainItems.get(i).getItemPanel().setVisible(false);
	                	mainItems.get(i).getItemPanel().updateUI();
	                    repaint();
	                }   
	               
	            }
	        };
    }
	 
	  
	 
	 public List<MmAccordionMainItem> getMenuItems() {
		 
		 return mainItems;
	 }
	 
	 public void uploadFile(File file,String str)
	 {
		 FileChannel src = null,des=null;
		 try {
		 	  src = new FileInputStream(file).getChannel();
		 	  des = new FileOutputStream(str+"/"+file.getName()).getChannel();
		 	  try {
		 		des.transferFrom(src, 0, src.size());
		 	} catch (IOException e) {
		 		// TODO Auto-generated catch block
		 		e.printStackTrace();
		 	}
		 } catch (FileNotFoundException e) {
		 	// TODO Auto-generated catch block
		 	e.printStackTrace();
		 }

		 finally
		 {
		 	try {
		 		src.close();
		 		des.close();
		 	} catch (IOException e) {
		 		// TODO Auto-generated catch block
		 		e.printStackTrace();
		 	}
		 	
		 }
	 }
	 
	 public MmStartEvents getStartEvents()
	 {
		 return startEvents;
	 }
	 
	 

}

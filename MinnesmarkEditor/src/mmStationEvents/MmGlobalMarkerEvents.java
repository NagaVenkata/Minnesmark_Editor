package mmStationEvents;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import mmObjWriter.*;

import org.json.*;

import javax.swing.*;



public class MmGlobalMarkerEvents {

	private String type,markerName,mediaText;
	private int markerIndex = -1;
		
    private ArrayList<MmMarkerEvent> markerEvents;
    private ArrayList<MmAudioEvent> audioEvents;
    private ArrayList<MmVideoEvent> videoEvents;
    private ArrayList<MmPanoramaEvent> panoramaEvents;
    private ArrayList<MmImageEvent> imageEvents;
    private ArrayList<MmModelEvent> modelEvents;
    private ArrayList<MmMessageEvent> messageEvents;
    
    private ArrayList<Integer> collectItems;
    
    private ArrayList<JLabel> labels;
    
    MmMarkerEvent markerEvent;
    
    
    int prev_index=0,current_index;
    
    String urlLinkText,saveFilePath;
    
    int currentLabelIndex=0;
    
    boolean isLastMarker;
    
   
	
	public MmGlobalMarkerEvents()
	{
		this.labels = new ArrayList<JLabel>();
		
		
		imageEvents = new ArrayList<MmImageEvent>();
		markerEvents = new ArrayList<MmMarkerEvent>();
		audioEvents = new ArrayList<MmAudioEvent>();
		videoEvents = new ArrayList<MmVideoEvent>();
		panoramaEvents = new ArrayList<MmPanoramaEvent>();
		modelEvents = new ArrayList<MmModelEvent>();
		messageEvents = new ArrayList<MmMessageEvent>();
		collectItems = new ArrayList<Integer>();
		
		markerEvent = new MmMarkerEvent();
		
		this.markerIndex = -1;
		
	}
	
	public MmGlobalMarkerEvents(String name)
	{
		this.markerName = name; 
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	

	public boolean isLastMarker() {
		return isLastMarker;
	}

	public void setLastMarker(boolean isLastMarker) {
		this.isLastMarker = isLastMarker;
	}
	
	
	public void setMarkerName(String name)
	{
		this.markerName = name;
				
	}
	
	
	public void setMediaText(String text)
	{
		this.mediaText = text;
	}
	
	public String getMediaText()
	{
		
		return this.mediaText;
	}

	
	public void setMarkerIndex(int index)
	{
		this.markerIndex = index;
		
	}
	
	public void setLabels(ArrayList<JLabel> label)
	{
	    for(int i=0;i<label.size();i++)
	    {
	    	this.labels.add(label.get(i));
	    }
			
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getMarkerName()
	{
		
		if(markerEvent.getEventName().contains(this.markerName+"_Model"))
			return markerEvent.getEventName();
		
		return this.markerName;
	}
	
	
	
	public int getMarkerIndex()
	{
		return this.markerIndex;
	
	}
	
		
	public ArrayList<JLabel> getLabels()
	{
		return this.labels;
	}
	
	/*
	 * returns the events file names associated with marker
	 */
	
	public String getMarkerMediaEvents()
	{
		String eventsText = "";
		
		if(!labels.isEmpty())
		{	
		   if(!labels.get(0).getName().equals("label"))
		      eventsText ="<html>";
		
		   for(int i=0;i<labels.size();i++)
		   {
			  if(!labels.get(i).getName().equals("label"))
			  {
				eventsText += labels.get(i).getText()+"<br/>";
				
			  }
		   }
		
		  if(eventsText.contains("<html>"))
		      eventsText +="</html>";
		}
		
		return eventsText;
	}
	
	public void setLabelsText(String text,String path)
	{
			    
		JLabel lb = new JLabel(text);
		lb.setName(path);
		
		int count =-1;
		
		for(int i=0;i<labels.size();i++)
		{
			if(labels.get(i).getName().equals("label"))
			{
				count = i;
				break;
			}
		}
		
		if(count<4)
		{
			labels.get(count).setText(text);
			labels.get(count).setName(path);
		}
		else
		{	
		   labels.add(lb);
		}   
		
			
	}
	
	public int getNumberOfEvents()
	{
			    
				
		int count = 0;
		
		for(int i=0;i<labels.size();i++)
		{
			if(!labels.get(i).getName().equals("label"))
			{
				
				count++;
				
			}
		}
		
		return count;		
			
	}
	
	
	public void addMouseEvents()
	{
		for(int i=0;i<labels.size();i++)
		{
			final int index = i;
			labels.get(i).addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent event) {
					// TODO Auto-generated method stub
					
					if(labels.get(prev_index).isOpaque())
					{
						labels.get(prev_index).setOpaque(true);
					}
					
					current_index=index;
					labels.get(index).setOpaque(true);
					labels.get(index).setBackground(Color.lightGray);
					prev_index = current_index;
					
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
		}
	}
	
	public void writeJson(String saveFilePath)
	{
				
		
		this.saveFilePath = saveFilePath;
		
		
		
		markerEvent.setEventName(this.markerName);
		markerEvent.setMarkerIndex(markerIndex+1);
		markerEvent.setDestinationPath(saveFilePath);
		
			
		//writeMarkerFile(saveFilePath);
			   
		JLabel lb = null; 
			   
		int i=0;
			    
			   
		while(currentLabelIndex<labels.size())
		{
				       
			System.out.println("current index "+currentLabelIndex);
			
			if(labels.get(currentLabelIndex).getText().isEmpty())
			{
				System.out.println("label index "+labels.get(currentLabelIndex).getText());
				System.out.println("Data");
					      
				break;
			}
				   
				   
			if(currentLabelIndex>=labels.size())
			{
				System.out.println("Data");
					      
				break;
			}
				   
				   
			if(currentLabelIndex<labels.size())
			{	  
				   
				lb = labels.get(currentLabelIndex);
							         
				System.out.println("label text "+lb.getText());
				
				
				   
			    if(((lb.getText().contains(".jpg"))||(lb.getText().contains(".png"))||(lb.getText().contains(".bmp"))) && !(lb.getText().contains("Panorama")) && !(lb.getText().contains("Model")))
			    { 	
			    	  
			    	 
			    	 
			    	 MmImageEvent imageEvent = new MmImageEvent(); 
			    	 imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_image");
			    	 markerEvent.addActions(imageEvent.getEventName());
					 String[] attrs = lb.getText().split(":");
					  
					 if(attrs.length<=1)
					 {	   
						   imageEvent.setImageFile(attrs[0]);
						   imageEvent.setCollectItem(false);
					 }
					 
					 if(attrs.length==2)
					 { 	   
						   imageEvent.setImageFile(attrs[0]);
					       imageEvent.setCollectItem(true);
					       collectItems.add(new Integer(collectItems.size()+1));
					 }
					   
					  				   
					  imageEvent.setSourcePath(lb.getName());
					  imageEvent.setDestinationPath(this.saveFilePath);
					  imageEvent.makeJSONObject();
					  imageEvent.JSONActions();
					  imageEvents.add(imageEvent);
			    	  currentLabelIndex++;
			    	  System.out.println("currentLabelIndex "+currentLabelIndex+"  "+labels.size());
			    	  if(currentLabelIndex<labels.size())
			    	  {	
			    		   if(!createImageEvent(imageEvent))
			    	    	   break;
			    	  }   
			    	  else
			    	  {
			    		 if(isLastMarker)
			    		 {
			    			 imageEvent.addActions("Done");
			    		 }
			    		 break;
			    	  }		 
			    	  
			    	 			            
			      } 
			    
			      			      
			      if((lb.getText().contains("Panorama")))
			      {
			    	  System.out.println("index in audio  "+i+"  "+currentLabelIndex+" "+labels.get(currentLabelIndex).getText());
			    	  MmPanoramaEvent panoramaEvent = new MmPanoramaEvent();
			    	  panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_panorama");
			    	  markerEvent.addActions(panoramaEvent.getEventName());
			    	  String[] attrs = lb.getText().split(":");
					   if(attrs.length<=2)
					   {	   
						   panoramaEvent.setPanoramaFile(attrs[0]);
					       panoramaEvent.setCollectItem(false);
					   }    
					   if(attrs.length==3)
					   { 	   
						   panoramaEvent.setPanoramaFile(attrs[0]);
						   panoramaEvent.setCollectItem(true);
						   collectItems.add(new Integer(collectItems.size()+1));
					   }
					   panoramaEvent.setSourcePath(lb.getName());
			    	  
			    	  panoramaEvent.setDestinationPath(this.saveFilePath);
			    	  panoramaEvent.makeJSONObject();
			    	  panoramaEvent.JSONActions();
			    	  panoramaEvents.add(panoramaEvent);
			    	  currentLabelIndex++;
			    	  if(currentLabelIndex<labels.size())
			    	  {	  
				          if(!createPanoramaEvent(panoramaEvent))
				        	  break;
			    	  } 
			    	  else
			    	  {
			    		  if(isLastMarker)
				    	  {
				    		  panoramaEvent.addActions("Done");
				    	  } 
			    		  break;
			    	  } 	 
			      }
			      
			      lb = labels.get(currentLabelIndex);
			      System.out.println("index in audio  "+i+"  "+currentLabelIndex+" "+lb.getText());
			      			      
			   
			      if(lb.getText().contains(".mp3")||lb.getText().contains(".m4a")||lb.getText().contains(".aiff"))
			      {	  
			    	  System.out.println("index in audio  "+i+"  "+currentLabelIndex+" "+labels.get(currentLabelIndex).getText());
			    	  MmAudioEvent audioEvent = new MmAudioEvent();
			    	  audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_audio");
			    	  markerEvent.addActions(audioEvent.getEventName());
					  audioEvent.setAudioFileName(lb.getText());
					  String[] attrs = lb.getText().split(":");
					   if(attrs.length<=1)
					   {	   
						   audioEvent.setAudioFileName(attrs[0]);
					       audioEvent.setCollectItem(false);
					   }    
					   if(attrs.length==2)
					   { 	   
						   audioEvent.setAudioFileName(attrs[0]);
					       audioEvent.setCollectItem(true);
					       collectItems.add(new Integer(collectItems.size()+1));
					   }
					   
					  audioEvent.setSourcePath(lb.getName());
					  audioEvent.setDestinationPath(this.saveFilePath);
					  audioEvent.makeJSONObject();
					  audioEvent.JSONActions();
			    	  audioEvents.add(audioEvent);
			    	  currentLabelIndex++;
			    	  if(currentLabelIndex<labels.size())
			    	  {	  
				           if(!createAudioEvent(audioEvent))
				        	   break;
			    	  } 
			    	  else
			    	  {		  
			    		  if(isLastMarker)
				    	  {
				    		  audioEvent.addActions("Done");
				    	  } 
			    		  break;
			    	  }	 
			    	  
			      }   
			      
			      lb = labels.get(currentLabelIndex);
			      
			      if(lb.getText().contains(".m4v"))
			      { 	  
			    	  System.out.println("index in loop2 "+i+"  "+currentLabelIndex);
					  MmVideoEvent videoEvent = new MmVideoEvent();
					  videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_video");
					  markerEvent.addActions(videoEvent.getEventName());
					  String[] attrs = lb.getText().split(":");
					   if(attrs.length<=1)
					   {	   
						   videoEvent.setVideoFileName(attrs[0]);
					       videoEvent.setCollectItem(false);
					   }    
					   if(attrs.length==2)
					   { 	   
						   videoEvent.setVideoFileName(attrs[0]);
					       videoEvent.setCollectItem(true);
					   }
					  videoEvent.setSourcePath(lb.getName());
					  videoEvent.setDestinationPath(this.saveFilePath);
					  videoEvent.makeJSONObject();
					  videoEvent.JSONActions();
			    	  videoEvents.add(videoEvent);
			    	  currentLabelIndex++;
			    	  if(currentLabelIndex<labels.size())
			    	  {	
			    	     if(!createVideoEvent(videoEvent))
			    	    	 break;
			    	  }   
			    	  else
			    	  {	  
			    		  if(isLastMarker)
				    	  {
				    		  videoEvent.addActions("Done");
				    	  } 
			    		  break;
			    	  }	 
			    	  
			      }	 
			      
			      
			      lb = labels.get(currentLabelIndex);
			      
			      if(lb.getText().contains(".obj") || lb.getText().contains("Model"))
			      { 	  
			    	     	  
			    	  String[] attrs = lb.getText().split(":");
			    	  
			    	  if(lb.getText().contains("Model"))
			    	  {
			    		  markerEvent.setEventName(this.markerName+"_Model");
			    		  
			    		  if(attrs.length==2)
			    		  {	  
			    		     MmObjWriter objWriter = new MmObjWriter(this.saveFilePath+"/osg_obj/"+attrs[0]);
			    		     objWriter.setImageSrcPath(lb.getName());
			    			 objWriter.beginSave(this.saveFilePath+"/osg_obj/"+attrs[0]);
			    			 markerEvent.setModelFile(objWriter.getFileName()+".obj");
			    			 objWriter.endSave();
			    		  }
			    		  
			    		  if(attrs.length==3)
					      { 	   
			    			  MmObjWriter objWriter = new MmObjWriter(this.saveFilePath+"/osg_obj/"+attrs[0]);
			    			  objWriter.setImageSrcPath(lb.getName());
				    		  objWriter.beginSave(this.saveFilePath+"/osg_obj/"+attrs[0]);
				    		  markerEvent.setModelFile(objWriter.getFileName()+".obj");
				    		  objWriter.endSave();
						      markerEvent.setCollectItem(true);
						      collectItems.add(new Integer(collectItems.size()+1));
					      }
			    		  
			    		  
			    	  }
			    	  
			    	  if(attrs.length<=1)
					  {	   
						   markerEvent.setCollectItem(false);
					  }    
					   
					  currentLabelIndex++;
			    	  if(currentLabelIndex>=labels.size())
			    	  {	
			    	     break;
			    	  }  
			     }
			      
			      lb = labels.get(currentLabelIndex);
			         		      
                  
			      
			      if(lb.getText().contains(".txt"))
			      { 	  
			    	   System.out.println("index in loop2 "+i+"  "+currentLabelIndex);
					   MmMessageEvent messageEvent = new MmMessageEvent();
					   messageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_message");
					   markerEvent.addActions(messageEvent.getEventName());
					   String[] attrs = lb.getText().split(":");
					   if(attrs.length<=1)
					   {	   
						   messageEvent.setMessageFile(attrs[0]);
					       messageEvent.setCollectItem(false);
					   }    
					   if(attrs.length==2)
					   { 	   
						  messageEvent.setMessageFile(attrs[0]);
					      messageEvent.setCollectItem(false);
					      collectItems.add(new Integer(collectItems.size()+1));
					   }
					   
					   messageEvent.setSourcePath(lb.getName());
					   messageEvent.setDestinationPath(this.saveFilePath);
					   messageEvent.makeJSONObject();
					   messageEvent.JSONActions();
			    	   messageEvents.add(messageEvent);
			    	   currentLabelIndex++;
			    	   
			    	   if(currentLabelIndex<labels.size())
			    	   {	
			    	     if(!createMessageEvent(messageEvent))
			    	    	 break;
			    	  }   
			    	  else
			    	  {	  
			    		  if(isLastMarker)
				    	  {
				    		  messageEvent.addActions("Done");
				    	  } 
			    		  break;
			    	  }	 
			    	  
			      }
			      
			      
				  } 
			      i=currentLabelIndex;
			     
			      if(i>=labels.size())
			      {	  
			    	  System.out.println("index in loop "+i+"  "+currentLabelIndex);
			    	  break;
			      }	 
			      
			      currentLabelIndex++;
			   }  
		
		       markerEvent.makeJSONObject();
		       markerEvent.JSONActions();
		
	}
	
	public void writeMarkerFile(String savePath)
	{
        FileChannel src = null,des=null;
		
		try 
		{
			  			  
			  File file = new File(savePath+"/markers");
			  
			  String desPath = "patt."+this.markerName;
			  
			  String sourcePath = System.getProperty("user.dir")+"/markers/"+"patt."+this.markerName;
			  
			  File desFile = new File(savePath+"/markers/"+desPath);
			  
			  if(!desFile.exists())
			  {	  
			  	  if(file.isDirectory())
			      {
				     src = new FileInputStream(sourcePath).getChannel(); 
			         des = new FileOutputStream(savePath+"/markers/"+desPath).getChannel();
			      }
			      else
			      {
				    file.mkdir();
				    src = new FileInputStream(sourcePath).getChannel();
				    des = new FileOutputStream(savePath+"/markers/"+desPath).getChannel();
			      }
			  	  
			  	try {
				    des.transferFrom(src, 0, src.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  }
			  
			  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally
		{
			try {
				   if(src!=null)
				   {	   
				      src.close();
				      des.close();
				   }   
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	public boolean createImageEvent(MmImageEvent imageEvent)
	{
		
		if(currentLabelIndex<labels.size())
		{
					
		    JLabel lb = labels.get(currentLabelIndex);
		   
		    if(labels.get(currentLabelIndex).getText().isEmpty())
		    {
		    	if(isLastMarker)
		    	{
		    		imageEvent.addActions("Done");
		    	} 
	    		imageEvent.JSONActions();
			    return false;
			    
		    } 
		
		    

		if((lb.getText().contains(".jpg"))||(lb.getText().contains(".png"))||(lb.getText().contains(".bmp")))
	    {
		      
		  	  System.out.println("label in image event"+lb.getText());  
			  
			  MmImageEvent nextImageEvent = new MmImageEvent();
			  	  
			  if(imageEvents.isEmpty())
				  nextImageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"image");
			  else
				  nextImageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(imageEvents.size())+"image");
			  
			        String[] attrs = lb.getText().split(":");
			        
			        if(attrs.length<=1)
			        {	
			           	nextImageEvent.setImageFile(attrs[0]);
			        	nextImageEvent.setCollectItem(false);
			           		
			        }   
			        
			        if(attrs.length==2)
			        {	
			        	nextImageEvent.setImageType(attrs[0]);
			        	nextImageEvent.setCollectItem(true);
			        	collectItems.add(new Integer(collectItems.size()+1));
			        }    
			        	
			           
			        nextImageEvent.setSourcePath(lb.getName());
			        nextImageEvent.setDestinationPath(this.saveFilePath);
			        
				    
				    currentLabelIndex++;
				    if(currentLabelIndex<labels.size())
				    {	
				    	//JOptionPane.showMessageDialog(null, "Entered image event");
				    	nextImageEvent.makeJSONObject();
				    	
				    				    	
				        nextImageEvent.JSONActions();
					    imageEvents.add(nextImageEvent);
					    imageEvent.addActions(nextImageEvent.getEventName());	
				        if(!createImageEvent(nextImageEvent))
				        	return false;
				       
				    }  
				    
				   
	        }		    
				  
		    if(lb.getText().contains("Panorama"))
			{
						   
					MmPanoramaEvent panoramaEvent = new MmPanoramaEvent();
					if(panoramaEvents.size()==0)
						panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_panorama");
					else
						panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_panorama");
						imageEvent.addActions(panoramaEvent.getEventName());
						imageEvent.makeJSONObject();
						panoramaEvents.add(panoramaEvent);
						String[] attrs = lb.getText().split(":");
						if(attrs.length<=2)
						{	   
						     panoramaEvent.setPanoramaFile(attrs[0]);
						     panoramaEvent.setCollectItem(false);
						}
						
						if(attrs.length==3)
						{ 	   
							panoramaEvent.setPanoramaFile(attrs[0]);
							panoramaEvent.setCollectItem(true);
							collectItems.add(new Integer(collectItems.size()+1));
						}	   
						   
						panoramaEvent.makeJSONObject();
						currentLabelIndex++;
					    if(!createPanoramaEvent(panoramaEvent))
					    	return false;
					    
					   }
		    
					   
					   if(lb.getText().contains(".mp3")||lb.getText().contains(".m4a")||lb.getText().contains(".aiff"))
				       {
						   
						   MmAudioEvent audioEvent = new MmAudioEvent();
						   if(audioEvents.size()==0)
							   audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_audio");
						   else
							   audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_audio");
						   		   
						   imageEvent.addActions(audioEvent.getEventName());
						   imageEvent.JSONActions();
						   audioEvents.add(audioEvent);
						   
						   String[] attrs = lb.getText().split(":");
						   if(attrs.length<=1)
						   {	   
							   audioEvent.setAudioFileName(attrs[0]);
						       audioEvent.setCollectItem(false);
						   }    
						   if(attrs.length==2)
						   { 	   
							   audioEvent.setAudioFileName(attrs[0]);
							   audioEvent.setCollectItem(true);
							   collectItems.add(new Integer(collectItems.size()+1));
						   }
						   audioEvent.setSourcePath(lb.getName());
						   audioEvent.setDestinationPath(this.saveFilePath);
					       audioEvent.setAudioFileName(lb.getText());
					       audioEvent.makeJSONObject();
					       currentLabelIndex++;
					       if(!createAudioEvent(audioEvent))
					    	   return false;
					       
					   }
					   
					   if(lb.getText().contains(".m4v"))
					   { 	  
							  MmVideoEvent videoEvent = new MmVideoEvent();
							  if(videoEvents.size()==0)
							     videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_video");
							  else
								  videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_video");
							   String[] attrs = lb.getText().split(":");
							   if(attrs.length<=1)
							   {	   
								   videoEvent.setVideoFileName(attrs[0]);
							       videoEvent.setCollectItem(false);
							   }    
							   if(attrs.length==2)
							   { 	   
								   videoEvent.setVideoFileName(attrs[0]);
								   videoEvent.setCollectItem(true);
								   collectItems.add(new Integer(collectItems.size()+1));
							   }
							  videoEvent.setVideoFileName(lb.getText());
							  videoEvent.setSourcePath(lb.getName());
							  videoEvent.setDestinationPath(this.saveFilePath);
							  imageEvent.addActions(videoEvent.getEventName());
							  imageEvent.JSONActions();
							  videoEvent.makeJSONObject();
					    	  videoEvents.add(videoEvent);
					    	  currentLabelIndex++;
					    	  if(currentLabelIndex<labels.size())
					    	  {	
					    	     if(!createVideoEvent(videoEvent))
					    	    	 return false;
					    	  }   
					      }	 
					   
					   
					   if(lb.getText().contains(".obj") || lb.getText().contains("Model"))
					   { 	  
							 MmModelEvent modelEvent = new MmModelEvent();
							 if(modelEvents.size()==0)
								  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_model");
							  else
								  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(modelEvents.size())+"_model"); 
							 
							 String[] attrs = lb.getText().split(":");
							   if(attrs.length<=1)
							   {	   
								   modelEvent.setModelFile(attrs[0]);
							       modelEvent.setCollectItem(false);
							   }    
							   if(lb.getText().contains("Model"))
							   {	   
							      if(attrs.length==2)
							      { 	   
								     modelEvent.setModelFile(attrs[0]);
								     modelEvent.setModelType(attrs[1]);
							         modelEvent.setCollectItem(false);
							      }
							      if(attrs.length==3)
							      { 	   
								     modelEvent.setModelFile(attrs[0]);
								     modelEvent.setCollectItem(true);
								     collectItems.add(new Integer(collectItems.size()+1));
							      }
							   }
							   else if(attrs.length==2)
							   { 	   
									modelEvent.setModelFile(attrs[0]);
									modelEvent.setCollectItem(true);
									collectItems.add(new Integer(collectItems.size()+1));
						       }
							  modelEvent.setSourcePath(lb.getName());
							  modelEvent.setDestinationPath(this.saveFilePath);
							  imageEvent.addActions(modelEvent.getEventName());
							  imageEvent.JSONActions();
							  modelEvent.makeJSONObject();
					    	  modelEvents.add(modelEvent);
					    	  currentLabelIndex++;
					    	  if(currentLabelIndex<labels.size())
					    	  {	
					    	     if(!createModelEvent(modelEvent))
					    	    	 return false;
					    	  }   
					    }
					      
					      
					      if(lb.getText().contains(".txt"))
						   { 	  
								  MmMessageEvent messageEvent = new MmMessageEvent();
								  messageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_message"); 
								  String[] attrs = lb.getText().split(":");
								  if(attrs.length<=1)
								  {	   
									  messageEvent.setMessageFile(attrs[0]);
									  messageEvent.setCollectItem(false);
								  }    
								  if(attrs.length==2)
								  { 	   
									  messageEvent.setMessageFile(attrs[0]);
									  messageEvent.setCollectItem(true);
									  collectItems.add(new Integer(collectItems.size()+1));
								  }
								  messageEvent.setSourcePath(lb.getName());
								  messageEvent.setDestinationPath(this.saveFilePath);
								  imageEvent.addActions(imageEvent.getEventName());
								  imageEvent.JSONActions();
								  imageEvent.makeJSONObject();
						    	  imageEvents.add(imageEvent);
						    	  currentLabelIndex++;
						    	  if(currentLabelIndex<labels.size())
						    	  {	
						    	     if(!createMessageEvent(messageEvent))
						    	    	 return false;
						    	  }   
						      }
		  
		}	  
		
		
		 if(labels.size()>=currentLabelIndex)
		 {	
				System.out.println("label index "+currentLabelIndex+" labels size "+labels.size());
				imageEvent.JSONActions();
				return false;
		 }
		
		
		
		
		return true;
		
		
	}
	
	public boolean createPanoramaEvent(MmPanoramaEvent panoramaEvent)
	{
		if(currentLabelIndex<labels.size())
		{	
		   JLabel lb = labels.get(currentLabelIndex);
		   
		   if(labels.get(currentLabelIndex).getText().isEmpty())
		   {
			   if(isLastMarker)
		       {
		    	   panoramaEvent.addActions("Done");
		       }
			   panoramaEvent.JSONActions();
			   return false;
		   }
		   
		   
		   if(lb.getText().contains("Panorama"))
	       {
			   
			   MmPanoramaEvent nextPanoramaEvent = new MmPanoramaEvent();
			   if(panoramaEvents.size()==0)
				   nextPanoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_panorama");
			   else
				   nextPanoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_panorama");
			   panoramaEvent.addActions(nextPanoramaEvent.getEventName());
			   panoramaEvent.JSONActions();
			   panoramaEvents.add(nextPanoramaEvent);
			   String[] attrs = lb.getText().split(":");
			   if(attrs.length<=2)
			   {	   
			       nextPanoramaEvent.setPanoramaFile(attrs[0]);
			       nextPanoramaEvent.setCollectItem(false);
			   }    
			   if(attrs.length==3)
			   { 	   
				   nextPanoramaEvent.setPanoramaFile(attrs[0]);
				   nextPanoramaEvent.setCollectItem(true);
				   collectItems.add(new Integer(collectItems.size()+1));
			   }	   
			   nextPanoramaEvent.setSourcePath(lb.getName());
			   nextPanoramaEvent.setDestinationPath(this.saveFilePath);
			   panoramaEvent.makeJSONObject();
		       currentLabelIndex++;
		       
		       if(currentLabelIndex<labels.size())
			    {	
			    	
			    	nextPanoramaEvent.makeJSONObject();
			    	
			    				    	
			        nextPanoramaEvent.JSONActions();
				    panoramaEvents.add(nextPanoramaEvent);
				    panoramaEvent.addActions(nextPanoramaEvent.getEventName());	
				    if(!createPanoramaEvent(nextPanoramaEvent))
				    	return false;
			       
			    }
		       
		   }
		   
		   if(lb.getText().contains(".mp3")||lb.getText().contains(".m4a")||lb.getText().contains(".aiff"))
	       {
			   
			   MmAudioEvent audioEvent = new MmAudioEvent();
			   if(audioEvents.size()==0)
				   audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_audio");
			   else
				   audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_audio");
			   		   
			   panoramaEvent.addActions(audioEvent.getEventName());
			   panoramaEvent.JSONActions();
			   audioEvents.add(audioEvent);
			   
			   String[] attrs = lb.getText().split(":");
			   if(attrs.length<=1)
			   {	   
				   audioEvent.setAudioFileName(attrs[0]);
			       audioEvent.setCollectItem(false);
			   }    
			   if(attrs.length==2)
			   { 	   
				   audioEvent.setAudioFileName(attrs[0]);
				   audioEvent.setCollectItem(true);
				   collectItems.add(new Integer(collectItems.size()+1));
			   }
			   audioEvent.setSourcePath(lb.getName());
			   audioEvent.setDestinationPath(this.saveFilePath);
		       audioEvent.setAudioFileName(lb.getText());
		       audioEvent.makeJSONObject();
		       currentLabelIndex++;
		       if(!createAudioEvent(audioEvent))
		    	   return false;
		       
		       
		   }
		   
		   if(lb.getText().contains(".m4v"))
		   { 	  
				  MmVideoEvent videoEvent = new MmVideoEvent();
				  if(videoEvents.size()==0)
				     videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_video");
				  else
					  videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_video");
				   String[] attrs = lb.getText().split(":");
				   if(attrs.length<=1)
				   {	   
					   videoEvent.setVideoFileName(attrs[0]);
				       videoEvent.setCollectItem(false);
				   }    
				   if(attrs.length==2)
				   { 	   
					   videoEvent.setVideoFileName(attrs[0]);
					   videoEvent.setCollectItem(true);
					   collectItems.add(new Integer(collectItems.size()+1));
				   }
				  videoEvent.setVideoFileName(lb.getText());
				  videoEvent.setSourcePath(lb.getName());
				  videoEvent.setDestinationPath(this.saveFilePath);
				  panoramaEvent.addActions(videoEvent.getEventName());
				  panoramaEvent.JSONActions();
				  videoEvent.makeJSONObject();
		    	  videoEvents.add(videoEvent);
		    	  currentLabelIndex++;
		    	  
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    	     if(!createVideoEvent(videoEvent))
		    	    	 return false;
		    	  }   
		      }	 
		   
		   
		   if(lb.getText().contains(".obj") || lb.getText().contains("Model"))
		   { 	  
				 MmModelEvent modelEvent = new MmModelEvent();
				 if(modelEvents.size()==0)
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_model");
				  else
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(modelEvents.size())+"_model"); 
				 
				   String[] attrs = lb.getText().split(":");
				   if(attrs.length<=1)
				   {	   
					   modelEvent.setModelFile(attrs[0]);
				       modelEvent.setCollectItem(false);
				   }    
				   if(lb.getText().contains("Model"))
				   {	   
				      if(attrs.length==2)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setModelType(attrs[1]);
				         modelEvent.setCollectItem(false);
				      }
				      if(attrs.length==3)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setCollectItem(true);
					     collectItems.add(new Integer(collectItems.size()+1));
				      }
				   }
				   else if(attrs.length==2)
				   { 	   
						modelEvent.setModelFile(attrs[0]);
						modelEvent.setCollectItem(true);
						collectItems.add(new Integer(collectItems.size()+1));
			       } 
				  modelEvent.setSourcePath(lb.getName());
				  modelEvent.setDestinationPath(this.saveFilePath);
				  panoramaEvent.addActions(modelEvent.getEventName());
				  panoramaEvent.JSONActions();
				  modelEvent.makeJSONObject();
		    	  modelEvents.add(modelEvent);
		    	  currentLabelIndex++;
		    	  
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    	     if(!createModelEvent(modelEvent))
		    	    	 return false;
		    	  }   
		    }
		      
		      		      
		      if(lb.getText().contains(".txt"))
			   { 	  
					  MmMessageEvent messageEvent = new MmMessageEvent();
					  messageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_message"); 
					  String[] attrs = lb.getText().split(":");
					  if(attrs.length<=1)
					  {	   
						  messageEvent.setMessageFile(attrs[0]);
						  messageEvent.setCollectItem(false);
					  }    
					  if(attrs.length==2)
					  { 	   
						  messageEvent.setMessageFile(attrs[0]);
						  messageEvent.setCollectItem(true);
						  collectItems.add(new Integer(collectItems.size()+1));
					  }
					  messageEvent.setSourcePath(lb.getName());
					  messageEvent.setDestinationPath(this.saveFilePath);
					  panoramaEvent.addActions(panoramaEvent.getEventName());
					  panoramaEvent.JSONActions();
					  panoramaEvent.makeJSONObject();
			    	  panoramaEvents.add(panoramaEvent);
			    	  currentLabelIndex++;
			    	  if(currentLabelIndex<labels.size())
			    	  {	
			    	     if(!createMessageEvent(messageEvent))
			    	    	 return false;
			    	  }   
			      }

		   
		
		      if((lb.getText().contains(".jpg"))||(lb.getText().contains(".png"))||(lb.getText().contains(".bmp"))||(lb.getText().contains("Panorama")))
		      {
		    	  
		    	  MmImageEvent imageEvent = new MmImageEvent();
			  	  
				  if(imageEvents.isEmpty())
					  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"Image");
				  else
					  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(imageEvents.size())+"Image");
				        String[] attrs = lb.getText().split(":");
				        
				        if(attrs.length<=1)
				        {	
				        	imageEvent.setImageFile(attrs[0]);
				           	imageEvent.setCollectItem(false);
				        }	
				           
				        if(attrs.length==2)
				        {	
				        	
				        	imageEvent.setImageFile(attrs[0]);
				        	imageEvent.setCollectItem(true);
				        	collectItems.add(new Integer(collectItems.size()+1));
				        }  	
				        imageEvent.setSourcePath(lb.getName());
				        imageEvent.setDestinationPath(this.saveFilePath);
				        imageEvent.makeJSONObject();
				        imageEvent.JSONActions();
					    imageEvents.add(imageEvent);
					    
					    panoramaEvent.addActions(imageEvent.getEventName());
					    panoramaEvent.JSONActions();
					    currentLabelIndex++;
					    if(!createImageEvent(imageEvent))
					    	return false;
			        
		      }
		
		   
		}
		
		if(labels.size()>=currentLabelIndex)
		{	
			System.out.println("label index "+currentLabelIndex+" labels size "+labels.size());
			
			panoramaEvent.JSONActions();
			return false;
		}
		
		return true;
	}
	
		
	public boolean createModelEvent(MmModelEvent modelEvent)
	{
		if(currentLabelIndex<labels.size())
		{	
		   JLabel lb = labels.get(currentLabelIndex);
		   
		   if(labels.get(currentLabelIndex).getText().isEmpty())
		   {
			   if(isLastMarker)
		       {
		    	   modelEvent.addActions("Done");
		       } 
			   modelEvent.JSONActions();
			   return false;
		   }
		   
		   if(lb.getText().contains("Panorama"))
	       {
			   
			   MmPanoramaEvent panoramaEvent = new MmPanoramaEvent();
			   if(panoramaEvents.size()==0)
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_panorama");
			   else
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_panorama");
			   modelEvent.addActions(panoramaEvent.getEventName());
			   modelEvent.JSONActions();
			   panoramaEvents.add(panoramaEvent);
			   
			   String[] attrs = lb.getText().split(":");
			   if(attrs.length<=2)
			   {	   
				   panoramaEvent.setPanoramaFile(attrs[0]);
			       panoramaEvent.setCollectItem(false);
			   }    
			   if(attrs.length==3)
			   { 	   
				   panoramaEvent.setPanoramaFile(attrs[0]);
				   panoramaEvent.setCollectItem(true);
				   collectItems.add(new Integer(collectItems.size()+1));
			   }
			   panoramaEvent.setSourcePath(lb.getName());
			   panoramaEvent.setDestinationPath(this.saveFilePath);
			   panoramaEvent.makeJSONObject();
			   currentLabelIndex++;
		       if(!createPanoramaEvent(panoramaEvent))
		    	   return false;
		       
		   }
		   
		   if(lb.getText().contains(".mp3")||lb.getText().contains(".m4a")||lb.getText().contains(".aiff"))
	       {
			   
			   MmAudioEvent audioEvent = new MmAudioEvent();
			   if(audioEvents.size()==0)
				   audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_audio");
			   else
				   audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_audio");
			   
			   modelEvent.addActions(audioEvent.getEventName());
			   modelEvent.JSONActions();
			   audioEvents.add(audioEvent);
			   String[] attrs = lb.getText().split(":");
			   if(attrs.length<=1)
			   {	   
				   audioEvent.setAudioFileName(urlLinkText+"/audios/"+attrs[0]);
			       audioEvent.setCollectItem(false);
			   }    
			   if(attrs.length==2)
			   { 	   
				   audioEvent.setAudioFileName(urlLinkText+"/audios/"+attrs[0]);
				   audioEvent.setCollectItem(true);
				   collectItems.add(new Integer(collectItems.size()+1));
			   }
			   audioEvent.setSourcePath(lb.getName());
			   audioEvent.setDestinationPath(this.saveFilePath);
		       audioEvent.makeJSONObject();
		       currentLabelIndex++;
		       if(!createAudioEvent(audioEvent))
		    	   return false;
		       
		   }
		   
		   if(lb.getText().contains(".m4v"))
		   { 	  
				  MmVideoEvent videoEvent = new MmVideoEvent();
				  if(videoEvents.size()==0)
				     videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_video");
				  else
					  videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_video"); 
				  
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=2)
				  {	   
					   videoEvent.setVideoFileName(urlLinkText+"/videos/"+attrs[0]);
				       videoEvent.setCollectItem(false);
				  }    
				  if(attrs.length==3)
				  { 	   
					   videoEvent.setVideoFileName(urlLinkText+"/videos/"+attrs[0]);
					   videoEvent.setCollectItem(true);
					   collectItems.add(new Integer(collectItems.size()+1));
				  }
				  videoEvent.setSourcePath(lb.getName());
				  videoEvent.setDestinationPath(this.saveFilePath);
				  modelEvent.addActions(videoEvent.getEventName());
				  modelEvent.JSONActions();
				  videoEvent.makeJSONObject();
		    	  videoEvents.add(videoEvent);
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    	     if(!createVideoEvent(videoEvent))
		    	    	 return false;
		    	  }   
		   }
		   
		   if(lb.getText().contains(".obj") || lb.getText().contains("Model") )
		   { 	  
				  MmModelEvent nextModelEvent = new MmModelEvent();
				  if(modelEvents.size()==0)
					  nextModelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_model");
				  else
					  nextModelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(modelEvents.size())+"_model");
				  
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  nextModelEvent.setModelFile(urlLinkText+"/objs/"+attrs[0]);
					  nextModelEvent.setCollectItem(false);
				  }    
				  if(lb.getText().contains("Model"))
				   {	   
				      if(attrs.length==2)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setModelType(attrs[1]);
				         modelEvent.setCollectItem(false);
				      }
				      if(attrs.length==3)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setCollectItem(true);
					     collectItems.add(new Integer(collectItems.size()+1));
				      }
				   }
				   else if(attrs.length==2)
				   { 	   
						modelEvent.setModelFile(attrs[0]);
						modelEvent.setCollectItem(true);
						collectItems.add(new Integer(collectItems.size()+1));
			       }
				  
				  nextModelEvent.setSourcePath(lb.getName());
				  nextModelEvent.setDestinationPath(this.saveFilePath);
				  modelEvent.JSONActions();
				  modelEvent.makeJSONObject();
		    	  modelEvents.add(nextModelEvent);
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    		  nextModelEvent.makeJSONObject();	
				      nextModelEvent.JSONActions();
					  modelEvents.add(nextModelEvent);
					  modelEvent.addActions(nextModelEvent.getEventName());	
					  if(!createModelEvent(nextModelEvent))
						  return false;
		    	  }   
		    }
		     
		      
		      if(lb.getText().contains(".txt"))
			   { 	  
					 
		    	  MmMessageEvent messageEvent = new MmMessageEvent();
				  messageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_message"); 
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					   messageEvent.setMessageFile(attrs[0]);
					   messageEvent.setCollectItem(false);
				  }
				  
				  if(attrs.length==2)
				  { 	   
					  messageEvent.setMessageFile(attrs[0]);
					  messageEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
					
				  messageEvent.setSourcePath(lb.getName());
				  messageEvent.setDestinationPath(this.saveFilePath);
				  modelEvent.addActions(modelEvent.getEventName());
				  modelEvent.JSONActions();
				  modelEvent.makeJSONObject();
			      modelEvents.add(modelEvent);
			      currentLabelIndex++;
			    	 
			      if(currentLabelIndex<labels.size())
			      {	
			    	   if(!createMessageEvent(messageEvent))
			    		   return false;
			      }   
			 }

		   
		
		      if((lb.getText().contains(".jpg"))||(lb.getText().contains(".png"))||(lb.getText().contains(".bmp"))||(lb.getText().contains("Panorama")))
		      {
                  
		    	  MmImageEvent imageEvent = new MmImageEvent();
			  	  
				  if(imageEvents.isEmpty())
					  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"Image");
				  else
					  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(imageEvents.size())+"Image");
				        String[] attrs = lb.getText().split(":");
				        
				        if(attrs.length<=1)
				        {	
				        	imageEvent.setImageFile(attrs[0]);
				        	imageEvent.setCollectItem(false);
				        }	
				           
				        if(attrs.length==2)
				        {	
				        	
				        	imageEvent.setImageFile(attrs[0]);
				        	imageEvent.setCollectItem(true);
				        	collectItems.add(new Integer(collectItems.size()+1));
				        }  	
				        
				        imageEvent.setSourcePath(lb.getName());
				        imageEvent.setDestinationPath(this.saveFilePath);
				        imageEvent.makeJSONObject();
				        imageEvent.JSONActions();
					    imageEvents.add(imageEvent);
					      
					    modelEvent.addActions(imageEvent.getEventName());
					    modelEvent.JSONActions();
					    currentLabelIndex++;
					    if(!createImageEvent(imageEvent))
					    	return false;
		     }
		
		   
		}
		
		if(labels.size()>=currentLabelIndex)
		{	
			System.out.println("label index "+currentLabelIndex+" labels size "+labels.size());
			
			modelEvent.JSONActions();
			return false;
		}
		
		return true;
	}
	
	public boolean createAudioEvent(MmAudioEvent audioEvent)
	{
		
		if(currentLabelIndex<labels.size())
		{	
		   	
		   JLabel lb = labels.get(currentLabelIndex);
		   
		   
		   if(labels.get(currentLabelIndex).getText().isEmpty())
		   {
			   if(isLastMarker)
		       {
		    	   audioEvent.addActions("Done");
		       }
			   audioEvent.JSONActions();
			   return false;
		   }
		   
		   if(lb.getText().contains("Panorama"))
	       {
			   
			   MmPanoramaEvent panoramaEvent = new MmPanoramaEvent();
			   if(panoramaEvents.size()==0)
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_panorama");
			   else
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_panorama");
			   audioEvent.addActions(panoramaEvent.getEventName());
			   audioEvent.JSONActions();
			   panoramaEvents.add(panoramaEvent);
			   
			   String[] attrs = lb.getText().split(":");
			   if(attrs.length<=2)
			   {	   
					 panoramaEvent.setPanoramaFile(attrs[0]);
				     panoramaEvent.setCollectItem(false);
			   }    
			   if(attrs.length==3)
			   { 	   
					 panoramaEvent.setPanoramaFile(attrs[0]);
					 panoramaEvent.setCollectItem(true);
					 collectItems.add(new Integer(collectItems.size()+1));
			   }
			   panoramaEvent.setSourcePath(lb.getName());
			   panoramaEvent.setDestinationPath(this.saveFilePath);
			   panoramaEvent.makeJSONObject();
			   currentLabelIndex++;
			   if(createPanoramaEvent(panoramaEvent))
				  return false; 
		       
		       
		       panoramaEvent.setSourcePath(lb.getName());
		   }
		   
		   
		   
		   if(lb.getText().contains(".mp3")||lb.getText().contains(".m4a")||lb.getText().contains(".aiff"))
	       {
			   
			   MmAudioEvent nextAudioEvent = new MmAudioEvent();
			   nextAudioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_audio");
			   audioEvent.addActions(nextAudioEvent.getEventName());
			   audioEvent.JSONActions();
			   audioEvents.add(nextAudioEvent);
			   
			   String[] attrs = lb.getText().split(":");
			   if(attrs.length<=1)
			   {	   
					nextAudioEvent.setAudioFileName(attrs[0]);
				    nextAudioEvent.setCollectItem(false);
			   }    
			
			   if(attrs.length==2)
			   { 	   
					nextAudioEvent.setAudioFileName(attrs[0]);
					nextAudioEvent.setCollectItem(true);
					collectItems.add(new Integer(collectItems.size()+1));
			   }
			   
			   nextAudioEvent.setSourcePath(lb.getName());
			   nextAudioEvent.setDestinationPath(this.saveFilePath);
		       
		       currentLabelIndex++;
		       
		       if(currentLabelIndex<labels.size())
		       {	   
		         nextAudioEvent.makeJSONObject();
		    	
		     	 		    	
		         nextAudioEvent.JSONActions();
			     audioEvents.add(nextAudioEvent);
			     audioEvent.addActions(nextAudioEvent.getEventName());	
			     if(!createAudioEvent(nextAudioEvent))
			    	 return false;
		         
		       }  
		   }
		   
		   if(lb.getText().contains(".m4v"))
		   { 	  
			      System.out.println("video event in audio "+lb.getText());
				  MmVideoEvent videoEvent = new MmVideoEvent();
				  if(videoEvents.size()==0)
				     videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_video");
				  else
					  videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_video");
				  
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  videoEvent.setVideoFileName(attrs[0]);
				      videoEvent.setCollectItem(false);
				  }    
				  if(attrs.length==2)
				  { 	   
					  videoEvent.setVideoFileName(attrs[0]);
					  videoEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
				  videoEvent.setSourcePath(lb.getName());
				  videoEvent.setDestinationPath(this.saveFilePath);
				  audioEvent.addActions(videoEvent.getEventName());
				  audioEvent.JSONActions();
				  videoEvent.makeJSONObject();
		    	  videoEvents.add(videoEvent);
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {
		    		 System.out.println("video event in audio "+lb.getText());
		    	     if(!createVideoEvent(videoEvent))
		    	    	 return false;
		    	  }   
		      }	   
		   
		   
		      if(lb.getText().contains(".obj") || lb.getText().contains("Model"))
		      { 	  
				 MmModelEvent modelEvent = new MmModelEvent();
				 if(modelEvents.size()==0)
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_model");
				  else
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(modelEvents.size())+"_model"); 
				  
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  modelEvent.setModelFile(attrs[0]);
				      modelEvent.setCollectItem(false);
				  }    
				  if(lb.getText().contains("Model"))
				   {	   
				      if(attrs.length==2)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setModelType(attrs[1]);
				         modelEvent.setCollectItem(false);
				      }
				      if(attrs.length==3)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setCollectItem(true);
					     collectItems.add(new Integer(collectItems.size()+1));
				      }
				   }
				   else if(attrs.length==2)
				   { 	   
						modelEvent.setModelFile(attrs[0]);
						modelEvent.setCollectItem(true);
						collectItems.add(new Integer(collectItems.size()+1));
			       }
				  modelEvent.setSourcePath(lb.getName());
				  modelEvent.setDestinationPath(this.saveFilePath);
				  audioEvent.addActions(modelEvent.getEventName());
				  audioEvent.JSONActions();
				  modelEvent.makeJSONObject();
		    	  modelEvents.add(modelEvent);
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    	     if(!createModelEvent(modelEvent))
		    	    	 return false;
		    	  }   
		     }
		      
		      		      
		      if(lb.getText().contains(".txt"))
			   { 	  
					  MmMessageEvent messageEvent = new MmMessageEvent();
					  messageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_message"); 
					  String[] attrs = lb.getText().split(":");
					  if(attrs.length<=1)
					  {	   
						  messageEvent.setMessageFile(attrs[0]);
						  messageEvent.setCollectItem(false);
					  }    
					  if(attrs.length==2)
					  { 	   
						  messageEvent.setMessageFile(attrs[0]);
						  messageEvent.setCollectItem(true);
						  collectItems.add(new Integer(collectItems.size()+1));
					  }
					  messageEvent.setSourcePath(lb.getName());
					  messageEvent.setDestinationPath(this.saveFilePath);
					  audioEvent.addActions(audioEvent.getEventName());
					  audioEvent.JSONActions();
					  audioEvent.makeJSONObject();
			    	  audioEvents.add(audioEvent);
			    	  currentLabelIndex++;
			    	  if(currentLabelIndex<labels.size())
			    	  {	
			    	     if(!createMessageEvent(messageEvent))
			    	    	 return false;
			    	  }   
			      }

		   
		
		      if((lb.getText().contains(".jpg"))||(lb.getText().contains(".png"))||(lb.getText().contains(".bmp"))||(lb.getText().contains("Panorama")))
		      {
                  
		    	  MmImageEvent imageEvent = new MmImageEvent();
			  	  
				  if(imageEvents.isEmpty())
					  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_Image");
				  else
					  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(imageEvents.size())+"_Image");
				        String[] attrs = lb.getText().split(":");
				        
				        if(attrs.length<=1)
				        {	
				        	imageEvent.setImageFile(attrs[0]);
				        	imageEvent.setCollectItem(false);
				        }	
				           
				        if(attrs.length==2)
				        {	
				        	
				        	imageEvent.setImageFile(attrs[0]);
				        	imageEvent.setCollectItem(true);
				        }  	
				        imageEvent.setSourcePath(lb.getName());
				        imageEvent.setDestinationPath(this.saveFilePath);
				        imageEvent.makeJSONObject();
				        imageEvent.JSONActions();
					    imageEvents.add(imageEvent);
					      
					    
					    audioEvent.addActions(imageEvent.getEventName());
					    audioEvent.JSONActions();
					    currentLabelIndex++;
					    if(!createImageEvent(imageEvent))
					    {
					    	return false;
					    }
		     }
		
		   
		}
		
		if(labels.size()>=currentLabelIndex)
		{	
			System.out.println("label index "+currentLabelIndex+" labels size "+labels.size());
			audioEvent.JSONActions();
			return false;
		}		
		
		return true;
	}
	
	public boolean createVideoEvent(MmVideoEvent videoEvent)
	{
		if(currentLabelIndex<labels.size())
		{	
		   JLabel lb = labels.get(currentLabelIndex);
		   
		   if(labels.get(currentLabelIndex).getText().isEmpty())
		   {
			   if(isLastMarker)
		       {
		    	   videoEvent.addActions("Done");
		       }
			   videoEvent.JSONActions();
			   return false;
		   }
		   
		   if(lb.getText().contains("Panorama"))
	       {
			   
			   MmPanoramaEvent panoramaEvent = new MmPanoramaEvent();
			   if(panoramaEvents.size()==0)
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_panorama");
			   else
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_panorama");
			   videoEvent.addActions(panoramaEvent.getEventName());
			   videoEvent.JSONActions();
			   panoramaEvents.add(panoramaEvent);
			      
			      String[] attrs = lb.getText().split(":");
				  if(attrs.length<=2)
				  {	   
					  panoramaEvent.setPanoramaFile(attrs[0]);
				      panoramaEvent.setCollectItem(false);
				  }    
				  if(attrs.length==3)
				  { 	   
					  panoramaEvent.setPanoramaFile(attrs[0]);
					  panoramaEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
			   panoramaEvent.setSourcePath(lb.getName());	  
			   panoramaEvent.setDestinationPath(this.saveFilePath);
			   panoramaEvent.makeJSONObject();
		       currentLabelIndex++;
		       if(!createPanoramaEvent(panoramaEvent))
		    	   return false;
		       
		   }
		   
		   if(lb.getText().contains(".mp3")||lb.getText().contains(".m4a")||lb.getText().contains(".aiff"))
	       {
			   
			   MmAudioEvent audioEvent = new MmAudioEvent();
			   if(audioEvents.size()==0)
				  audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_audio");
			   else	   
			      audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_audio");
			   videoEvent.addActions(audioEvent.getEventName());
			   videoEvent.JSONActions();
			   audioEvents.add(audioEvent);
			      
			      String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  audioEvent.setAudioFileName(attrs[0]);
				      audioEvent.setCollectItem(false);
				  }    
				  if(attrs.length==2)
				  { 	   
					  audioEvent.setAudioFileName(attrs[0]);
					  audioEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
			   audioEvent.setSourcePath(lb.getName());	  
			   audioEvent.setDestinationPath(this.saveFilePath);
		       audioEvent.makeJSONObject();
		       currentLabelIndex++;
		       if(!createAudioEvent(audioEvent))
		    	   return false;
		   }
		   
		   if(lb.getText().contains(".m4v"))
		   { 	  
				  MmVideoEvent nextVideoEvent = new MmVideoEvent();
				  nextVideoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_video"); 
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  nextVideoEvent.setVideoFileName(attrs[0]);
				      nextVideoEvent.setCollectItem(false);
				  }    
				  if(attrs.length==2)
				  { 	   
					  nextVideoEvent.setVideoFileName(attrs[0]);
					  nextVideoEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
				  nextVideoEvent.setSourcePath(lb.getName());
				  nextVideoEvent.setDestinationPath(this.saveFilePath);
				  
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    		  nextVideoEvent.makeJSONObject();
				    	
				      nextVideoEvent.JSONActions();
					  videoEvents.add(nextVideoEvent);
					  videoEvent.addActions(nextVideoEvent.getEventName());	
					  if(!createVideoEvent(nextVideoEvent))
						  return false;
				     	    	     
		    	  }   
		      }	
		   
		   
		      if(lb.getText().contains(".obj") || lb.getText().contains("Model"))
		      { 	  
				 MmModelEvent modelEvent = new MmModelEvent();
				 if(modelEvents.size()==0)
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_model");
				  else
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(modelEvents.size())+"_model"); 
				  
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  modelEvent.setModelFile(attrs[0]);
				      modelEvent.setCollectItem(false);
				  }    
				  if(lb.getText().contains("Model"))
				   {	   
				      if(attrs.length==2)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setModelType(attrs[1]);
				         modelEvent.setCollectItem(false);
				      }
				      if(attrs.length==3)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setCollectItem(true);
					     collectItems.add(new Integer(collectItems.size()+1));
				      }
				   }
				   else if(attrs.length==2)
				   { 	   
						modelEvent.setModelFile(attrs[0]);
						modelEvent.setCollectItem(true);
						collectItems.add(new Integer(collectItems.size()+1));
			       }
				  modelEvent.setSourcePath(lb.getName());
				  modelEvent.setDestinationPath(this.saveFilePath);
				  videoEvent.addActions(modelEvent.getEventName());
				  videoEvent.JSONActions();
				  modelEvent.makeJSONObject();
		    	  modelEvents.add(modelEvent);
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    	     if(!createModelEvent(modelEvent))
		    	    	 return false;
		    	  }   
		     }
		      
		     
		      
		       if(lb.getText().contains(".txt"))
			   { 	  
					  MmMessageEvent messageEvent = new MmMessageEvent();
					  messageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_message"); 
					  String[] attrs = lb.getText().split(":");
					  if(attrs.length<=1)
					  {	   
						  messageEvent.setMessageFile(attrs[0]);
						  messageEvent.setCollectItem(false);
					  }    
					  if(attrs.length==2)
					  { 	   
						  messageEvent.setMessageFile(attrs[0]);
						  messageEvent.setCollectItem(true);
						  collectItems.add(new Integer(collectItems.size()+1));
					  }
					  messageEvent.setSourcePath(lb.getName());
					  messageEvent.setDestinationPath(this.saveFilePath);
					  videoEvent.addActions(videoEvent.getEventName());
					  videoEvent.JSONActions();
					  videoEvent.makeJSONObject();
			    	  videoEvents.add(videoEvent);
			    	  currentLabelIndex++;
			    	  if(currentLabelIndex<labels.size())
			    	  {	
			    	     if(!createMessageEvent(messageEvent))
			    	    	 return false;
			    	  }   
			      }
		   
		
		      if((lb.getText().contains(".jpg"))||(lb.getText().contains(".png"))||(lb.getText().contains(".bmp"))||(lb.getText().contains("Panorama")))
		      {
			      if(currentLabelIndex<labels.size())
			      {
			    	  MmImageEvent imageEvent = new MmImageEvent();
				  	  
					  if(imageEvents.isEmpty())
						  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"Image");
					  else
						  imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(imageEvents.size())+"Image");
					        String[] attrs = lb.getText().split(":");
					        
					        if(attrs.length<=1)
					        {	
					        	imageEvent.setImageFile(attrs[0]);
					        	imageEvent.setCollectItem(false);
					        }	
					           
					        if(attrs.length==2)
					        {	
					        	
					        	imageEvent.setImageFile(attrs[0]);
					        	imageEvent.setCollectItem(true);
					        	collectItems.add(new Integer(collectItems.size()+1));
					        }  	
					        imageEvent.setSourcePath(lb.getName());
					        imageEvent.setDestinationPath(this.saveFilePath);
					        imageEvent.makeJSONObject();
					        imageEvent.JSONActions();
						    imageEvents.add(imageEvent);
						    videoEvent.addActions(imageEvent.getEventName());
						    videoEvent.JSONActions();
						    currentLabelIndex++;
					    	if(currentLabelIndex<labels.size())
					    	{	
					    	     if(!createImageEvent(imageEvent))
					    	    	 return false;
					    	}
			      }   
		     }
		
		   
		}
		
		
		if(labels.size()>=currentLabelIndex)
		{	
			System.out.println("label index "+currentLabelIndex+" labels size "+labels.size());
			videoEvent.JSONActions();
			return false;
		}
			
		
		return true;
	}
	
	
	public boolean createMessageEvent(MmMessageEvent messageEvent)
	{
		if(currentLabelIndex<labels.size())
		{	
		   JLabel lb = labels.get(currentLabelIndex);
		   
		   if(labels.get(currentLabelIndex).getText().isEmpty())
		   {
			   if(isLastMarker)
		       {
		    	   messageEvent.addActions("Done");
		       }
			   messageEvent.JSONActions();
			   return false;
		   }
		   
		   if(lb.getText().contains("Panorama"))
	       {
			   
			   MmPanoramaEvent panoramaEvent = new MmPanoramaEvent();
			   if(panoramaEvents.size()==0)
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_panorama");
			   else
				   panoramaEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_panorama");
			   messageEvent.addActions(panoramaEvent.getEventName());
			   messageEvent.JSONActions();
			   panoramaEvents.add(panoramaEvent);
			      
			      String[] attrs = lb.getText().split(":");
				  if(attrs.length<=2)
				  {	   
					  panoramaEvent.setPanoramaFile(attrs[0]);
				      panoramaEvent.setCollectItem(false);
				  }    
				  if(attrs.length==3)
				  { 	   
					  panoramaEvent.setPanoramaFile(attrs[0]);
					  panoramaEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
			   panoramaEvent.setSourcePath(lb.getName());	  
			   panoramaEvent.setDestinationPath(this.saveFilePath);
			   panoramaEvent.makeJSONObject();
			   currentLabelIndex++;
		       if(!createPanoramaEvent(panoramaEvent))
		    	   return false;
		       
		   }
		   
		   if(lb.getText().contains(".mp3")||lb.getText().contains(".m4a")||lb.getText().contains(".aiff"))
	       {
			   
			   MmAudioEvent audioEvent = new MmAudioEvent();
			   if(audioEvents.size()==0)
				  audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_audio");
			   else	   
			      audioEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_audio");
			   messageEvent.addActions(audioEvent.getEventName());
			   messageEvent.JSONActions();
			   audioEvents.add(audioEvent);
			      
			      String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  audioEvent.setAudioFileName(attrs[0]);
				      audioEvent.setCollectItem(false);
				  }    
				  if(attrs.length==2)
				  { 	   
					  audioEvent.setAudioFileName(attrs[0]);
					  audioEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
			   audioEvent.setSourcePath(lb.getName());	  
			   audioEvent.setDestinationPath(this.saveFilePath);
		       audioEvent.makeJSONObject();
		       currentLabelIndex++;
		       if(!createAudioEvent(audioEvent))
		    	   return false;
		   }
		   
		   if(lb.getText().contains(".m4v"))
		   { 	  
				  MmVideoEvent videoEvent = new MmVideoEvent();
				  videoEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_video"); 
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  videoEvent.setVideoFileName(attrs[0]);
				      videoEvent.setCollectItem(false);
				  }    
				  if(attrs.length==2)
				  { 	   
					  videoEvent.setVideoFileName(attrs[0]);
					  videoEvent.setCollectItem(true);
					  collectItems.add(new Integer(collectItems.size()+1));
				  }
				  videoEvent.setSourcePath(lb.getName());
				  videoEvent.setDestinationPath(this.saveFilePath);
				  messageEvent.addActions(videoEvent.getEventName());
				  messageEvent.JSONActions();
				  videoEvent.makeJSONObject();
		    	  videoEvents.add(videoEvent);
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    	     if(!createVideoEvent(videoEvent))
		    	    	 return false;
		    	  }   
		      }	
		   
		   
		      if(lb.getText().contains(".obj") || lb.getText().contains("Model"))
		      { 	  
		    	
		    	  
				 MmModelEvent modelEvent = new MmModelEvent();
				 if(modelEvents.size()==0)
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_model");
				  else
					  modelEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(modelEvents.size())+"_model"); 
				  
				  String[] attrs = lb.getText().split(":");
				  if(attrs.length<=1)
				  {	   
					  modelEvent.setModelFile(attrs[0]);
				      modelEvent.setCollectItem(false);
				  }    
				  if(lb.getText().contains("Model"))
				   {	   
				      if(attrs.length==2)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setModelType(attrs[1]);
				         modelEvent.setCollectItem(false);
				      }
				      if(attrs.length==3)
				      { 	   
					     modelEvent.setModelFile(attrs[0]);
					     modelEvent.setCollectItem(true);
					     collectItems.add(new Integer(collectItems.size()+1));
				      }
				   }
				   else if(attrs.length==2)
				   { 	   
						modelEvent.setModelFile(attrs[0]);
						modelEvent.setCollectItem(true);
						collectItems.add(new Integer(collectItems.size()+1));
			       }
				  modelEvent.setSourcePath(lb.getName());
				  modelEvent.setDestinationPath(this.saveFilePath);
				  messageEvent.addActions(modelEvent.getEventName());
				  messageEvent.JSONActions();
				  modelEvent.makeJSONObject();
		    	  modelEvents.add(modelEvent);
		    	  currentLabelIndex++;
		    	  if(currentLabelIndex<labels.size())
		    	  {	
		    	     if(!createModelEvent(modelEvent))
		    	    	 return false;
		    	  }   
		     }
		      
		      
		      if(lb.getText().contains(".txt"))
			   { 	  
					  MmMessageEvent nextMessageEvent = new MmMessageEvent();
					  nextMessageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(videoEvents.size())+"_video"); 
					  String[] attrs = lb.getText().split(":");
					  
					  if(attrs.length<=1)
					  {	   
						  nextMessageEvent.setMessageFile(attrs[0]);
					      nextMessageEvent.setCollectItem(false);
					  }    
					  if(attrs.length==2)
					  { 	   
						  nextMessageEvent.setMessageFile(attrs[0]);
						  nextMessageEvent.setCollectItem(true);
						  collectItems.add(new Integer(collectItems.size()+1));
					  }
					  
					  nextMessageEvent.setSourcePath(lb.getName());
					  nextMessageEvent.setDestinationPath(this.saveFilePath);
					  
			    	  currentLabelIndex++;
			    	  if(currentLabelIndex<labels.size())
			    	  {	
			    		  nextMessageEvent.makeJSONObject();
					    	
					      nextMessageEvent.JSONActions();
						  messageEvents.add(nextMessageEvent);
						  messageEvent.addActions(nextMessageEvent.getEventName());	
						  if(!createMessageEvent(nextMessageEvent))
							  return false;
			    	  }   
			      }	
			   

		   
		
		      if((lb.getText().contains(".jpg"))||(lb.getText().contains(".png"))||(lb.getText().contains(".bmp")) &&(!(lb.getText().contains("Panorama") && !(lb.getText().contains("Model")))))
		      {
			      		    	  
		    	   MmImageEvent imageEvent = new MmImageEvent();
				   if(imageEvents.size()==0)
					   imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_image");
				   else
					   imageEvent.setEventName("marker"+Integer.toString(markerIndex+1)+"_"+Integer.toString(audioEvents.size())+"_image");
				   imageEvent.addActions(imageEvent.getEventName());
				   imageEvent.JSONActions();
				   imageEvents.add(imageEvent);
				      
				      String[] attrs = lb.getText().split(":");
					  if(attrs.length<=1)
					  {	   
						  imageEvent.setImageFile(attrs[0]);
						  imageEvent.setCollectItem(false);
					  }    
					  if(attrs.length==2)
					  { 	   
						  imageEvent.setImageFile(attrs[0]);
						  
						  imageEvent.setCollectItem(true);
						  collectItems.add(new Integer(collectItems.size()+1));
					  }
					  imageEvent.setSourcePath(lb.getName());	  
					  imageEvent.setDestinationPath(this.saveFilePath);
					  imageEvent.makeJSONObject();
					  messageEvent.addActions(imageEvent.getEventName());
					  messageEvent.JSONActions();
					  currentLabelIndex++;
			          if(!createImageEvent(imageEvent))
			        	  return false;
			       
		    	  
		    	    
		     }
		
		   
		}
		
		
		if(labels.size()>=currentLabelIndex)
		{	
			System.out.println("label index "+currentLabelIndex+" labels size "+labels.size());
			messageEvent.JSONActions();
			return false;
		}
			
		
		return true;
	}
	
	//returns number of media events attached to the marker 
	public int getActualLabelsCount()
	{
		int count=0;
		for(int i=0;i<labels.size();i++)
		{
			if(!labels.get(i).getName().equals("label"))
			{
				count++;
			}
		}
		
		return count;
	}
	
		
	public ArrayList<MmMarkerEvent> getMarkerEvents()
	{
		return markerEvents;
	}
	
	public MmMarkerEvent getMarker()
	{
		return markerEvent;
	}
	
	public void writeMarkerEvent(JSONArray jsonEvents)
	{
		jsonEvents.put(markerEvent.getMarkerEvent());
	}
	
	public int getCollectedItems()
	{
		return collectItems.size();
	}
	
	public void writeJsonObjects(JSONArray jsonEvents)
	{
				
		//for(int i=0;i<markerEvents.size();i++)
			//jsonEvents.put(markerEvents.get(i).getMarkerEvent());
		
		for(int i=0;i<imageEvents.size();i++)
			jsonEvents.put(imageEvents.get(i).getImageEvent());
		
		for(int i=0;i<audioEvents.size();i++)
			jsonEvents.put(audioEvents.get(i).getAudioEvent());
		
		for(int i=0;i<modelEvents.size();i++)
			jsonEvents.put(modelEvents.get(i).getModelEvent());
		
		for(int i=0;i<panoramaEvents.size();i++)
			jsonEvents.put(panoramaEvents.get(i).getPanoramaEvent());
		
		for(int i=0;i<videoEvents.size();i++)
			jsonEvents.put(videoEvents.get(i).getVideoEvent());
		
		for(int i=0;i<messageEvents.size();i++)
			jsonEvents.put(messageEvents.get(i).getMessageEvent());
		
		
	}
	
}


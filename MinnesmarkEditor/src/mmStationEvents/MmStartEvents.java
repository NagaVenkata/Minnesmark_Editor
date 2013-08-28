package mmStationEvents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class MmStartEvents {
	
	
    public ArrayList<MmAudioEvent> audioEvents;
    public ArrayList<MmVideoEvent> videoEvents;
    public ArrayList<MmImageEvent> imageEvents;
    public ArrayList<MmMessageEvent> messageEvents;
    
    ArrayList<String> labelsText;
    ArrayList<String> pathsText;
    
    public String[] eventNames = new String[4]; 
    
    JSONObject startEvent,attributes;
    
    public MmStartEvents()
    {
    	labelsText = new ArrayList<String>();
    	pathsText = new ArrayList<String>();
    	audioEvents = new ArrayList<MmAudioEvent>();
    	videoEvents = new ArrayList<MmVideoEvent>();
    	imageEvents = new ArrayList<MmImageEvent>();
    	messageEvents = new ArrayList<MmMessageEvent>();
    	
    	for(int i=0;i<4;i++)
    	{
    		eventNames[i] = "";
    	}
    	
    	
    }
    
        
    
    
    /*
     * sets the default labels size to four
     */
    public void setdefaultLabelsSize()
    {
    	
    	for(int i=0;i<4;i++)
    	{
    		pathsText.add("");
    	}
    }
    
    
    public void addText(String text,String path)
    {
    	labelsText.add(text);
    	pathsText.add(path);
    }
    
    //adds the event at paticular index
    public void addPaths(String path,int index)
    {
    	
    	pathsText.set(index,path);
    }
    
    public ArrayList<String> getTexts()
    {
    	return labelsText;
    }
    
    /*
     * returs the source path of the media files
     */
    public ArrayList<String> getTextPaths()
    {
    	return pathsText;
    }
    
    
    public void writeJson(String saveFilePath)
    {
    	for(int i=0;i<labelsText.size();i++)
    	{
    		String text = labelsText.get(i);
    		if((text.contains("jpg"))||(text.contains("png"))||(text.contains("bmp")))
    		{
    			if(i==0)
    			{	
    				createLedBackgroundImage(text,saveFilePath);
    			}
    			else
    			{
    				MmImageEvent imageEvent = new MmImageEvent();
     			   imageEvent.setEventName("start"+Integer.toString(i)+"_image");
     			   eventNames[i] = "start"+Integer.toString(i+1)+"_image";
     			   imageEvent.setImageFile(text);
     			   imageEvent.setSourcePath(pathsText.get(i));
 				   imageEvent.setDestinationPath(saveFilePath);
     			   imageEvent.makeJSONObject();
     			   
     			  if((i+1)<labelsText.size())
				  {
					  if(text.contains(".jpg")||text.contains(".png")||text.contains(".bmp"))
					  {	  
					       imageEvent.addActions("start"+labelsText.get(i+1)+"_image");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_image";
					  }     
					  if(text.contains(".mp3")||text.contains(".m4a")||text.contains(".aiff"))
					  {	  
						  
						  imageEvent.addActions("start"+Integer.toString(i+1)+"_audio");
						  eventNames[i+1] = "start"+Integer.toString(i+1)+"_audio";
					  }	  
					  if(text.contains(".m4v"))
					  {	  
						  imageEvent.addActions("start"+Integer.toString(i+1)+"_video");
						  eventNames[i+1] = "start"+Integer.toString(i+1)+"_video";
					  }	  
					  if(text.contains(".txt"))
					  {	  
						  imageEvent.addActions("start"+Integer.toString(i+1)+"_message");
						  eventNames[i+1] = "start"+Integer.toString(i+1)+"_message";
					  }		  
					  
				  }
     			   imageEvent.JSONActions();
     			   imageEvents.add(imageEvent);
    			}
    		}
    		
    		 if(text.contains(".mp3")||text.contains(".m4a")||text.contains(".aiff"))
		      {	  
		    	  //System.out.println("index in audio  "+i+"  "+currentLabelIndex+" "+labels.get(currentLabelIndex).getText());
		    	  MmAudioEvent audioEvent = new MmAudioEvent();
		    	  audioEvent.setEventName("start"+Integer.toString(i)+"_audio");
		    	  eventNames[i] = "start"+Integer.toString(i)+"_audio";
		    	  audioEvent.setAudioFileName(text);
				  audioEvent.setSourcePath(pathsText.get(i));
				  audioEvent.setDestinationPath(saveFilePath);
				  audioEvent.makeJSONObject();
				  if((i+1)<labelsText.size())
				  {
					  if(text.contains(".jpg")||text.contains(".png")||text.contains(".bmp"))
					  {	  
					       audioEvent.addActions("start"+Integer.toString(i+1)+"_image");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_image";
					  }     
					  if(text.contains(".mp3")||text.contains(".m4a")||text.contains(".aiff"))
					  {	  
					       audioEvent.addActions("start"+Integer.toString(i+1)+"_audio");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_audio";
					  }     
					  if(text.contains(".m4v"))
					  { 	  
					       audioEvent.addActions("start"+Integer.toString(i+1)+"_video");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_video";
					  }     
					  if(text.contains(".txt"))
					  {	  
					       audioEvent.addActions("start"+Integer.toString(i+1)+"_message");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_message";
					  }     
					  
				  }
				  audioEvent.JSONActions();
		    	  audioEvents.add(audioEvent);
		    	     	  
		      }   
		      
		      		      
		      if(text.contains(".m4v"))
		      { 	  
		    	  MmVideoEvent videoEvent = new MmVideoEvent();
				  videoEvent.setEventName("start"+Integer.toString(i)+"_video");
				  eventNames[i] = "start"+Integer.toString(i)+"_video";
				  videoEvent.setVideoFileName(text);
				  videoEvent.setSourcePath(pathsText.get(i));
				  videoEvent.setDestinationPath(saveFilePath);
				  videoEvent.makeJSONObject();
				  if((i+1)<labelsText.size())
				  {
					  if(text.contains(".jpg")||text.contains(".png")||text.contains(".bmp"))
					  {		  
					       videoEvent.addActions("start"+Integer.toString(i+1)+"_image");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_image";
					  }     
					  if(text.contains(".mp3")||text.contains(".m4a")||text.contains(".aiff"))
					  {	  
					       videoEvent.addActions("start"+Integer.toString(i+1)+"_audio");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_audio";
					  }     
					  if(text.contains(".m4v"))
					  {	  
						  videoEvent.addActions("start"+Integer.toString(i+1)+"_video");
						  eventNames[i+1] = "start"+Integer.toString(i+1)+"_video";
					  }	  
					  if(text.contains(".txt"))
					  {	  
						  videoEvent.addActions("start"+Integer.toString(i+1)+"_message");
						  eventNames[i+1] = "start"+Integer.toString(i+1)+"_audio";
					  }	  
					  
				  }
				  videoEvent.JSONActions();
		    	  videoEvents.add(videoEvent);
		    	  	    	   
		      }
		      
		      if(text.contains(".txt"))
		      { 	  
		    	 
				  MmMessageEvent messageEvent = new MmMessageEvent();
				  messageEvent.setEventName("start"+Integer.toString(i)+"_message");
				  eventNames[i] = "start"+Integer.toString(i)+"_message";				  
				  messageEvent.setSourcePath(pathsText.get(i));
				  messageEvent.setDestinationPath(saveFilePath);
				  messageEvent.makeJSONObject();
				  if((i+1)<labelsText.size())
				  {
					  if(text.contains(".jpg")||text.contains(".png")||text.contains(".bmp"))
					  {	  
					       messageEvent.addActions("start"+Integer.toString(i+1)+"_image");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_image";
					  }     
					  if(text.contains(".mp3")||text.contains(".m4a")||text.contains(".aiff"))
					  {	  
					       messageEvent.addActions("start"+Integer.toString(i+1)+"_audio");
					       eventNames[i+1] = "start"+Integer.toString(i+1)+"_audio";
					  }     
					  if(text.contains(".m4v"))
					  {	  
						  messageEvent.addActions("start"+Integer.toString(i+1)+"_video");
						  eventNames[i+1] = "start"+Integer.toString(i+1)+"_video";
					  }	  
					  if(text.contains(".txt"))
					  {	  
						  messageEvent.addActions("start"+Integer.toString(i+1)+"_message");
						  eventNames[i+1] = "start"+Integer.toString(i+1)+"_message";
					  }	  
					  
				  }
				  messageEvent.JSONActions();
		    	  messageEvents.add(messageEvent);
		    	  	    	  
		      }
    	}
    }
    
    public void createLedBackgroundImage(String text,String saveFilePath)
    {
    	try
    	{
    		startEvent = new JSONObject();
    		startEvent.put("name", "BackGroundImage");
    		startEvent.put("type", "backgroundimage");
    		
    		attributes = new JSONObject();
    		attributes.put("ledImage", text);
    		
    		startEvent.put("attributes",attributes);
    		
    		eventNames[0] = "BackGroundImage";
    		
    		writeFile(saveFilePath);
    		
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
    
    
    public void writeBackgroundImage(JSONArray jsonEvents)
    {
    	if(startEvent!=null)
    	  jsonEvents.put(startEvent);
    }
    
    
    public void writeJsonObjects(JSONArray jsonEvents)
	{
							
		for(int i=0;i<imageEvents.size();i++)
			jsonEvents.put(imageEvents.get(i).getImageEvent());
		
		for(int i=0;i<audioEvents.size();i++)
			jsonEvents.put(audioEvents.get(i).getAudioEvent());
		
			for(int i=0;i<videoEvents.size();i++)
			jsonEvents.put(videoEvents.get(i).getVideoEvent());
		
		for(int i=0;i<messageEvents.size();i++)
			jsonEvents.put(messageEvents.get(i).getMessageEvent());
		
		
	}
    
    public void writeFile(String destinationPath)
    {
        
    	FileChannel src = null,des=null;
		
		try 
		{
			  			  
			  File file = new File(destinationPath+"/images");
			  
			  String[] desPath = pathsText.get(0).split("/");
			  
			  if(file.isDirectory())
			  {
				  src = new FileInputStream(pathsText.get(0)).getChannel(); 
			      des = new FileOutputStream(destinationPath+"/images/"+desPath[desPath.length-1]).getChannel();
			  }
			  else
			  {
				  file.mkdir();
				  src = new FileInputStream(pathsText.get(0)).getChannel();
				  des = new FileOutputStream(destinationPath+"/images/"+desPath[desPath.length-1]).getChannel();
			  }
			  
			  
			  
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

}

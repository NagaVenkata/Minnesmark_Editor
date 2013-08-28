package mmStationEvents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.*;
import org.json.*;

public class MmAudioEvent {
	
	private String type,eventName,audioFile;
	public JTextField eventAudioName;
	JSONObject events,attributes,actions;
	boolean collectItem;
	
	String sourcePath,destinationPath;
	
	JSONArray action = new JSONArray();
	
	public MmAudioEvent()
	{
		events = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
	
	public MmAudioEvent(String name)
	{
		this.audioFile = name; 
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	public void setAudioFileName(String name)
	{
		this.audioFile = name;
	}
	
	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}
	
		
	public String getType()
	{
		return this.type;
	}
	
	public String getAudioFileName()
	{
		return this.audioFile;
	}
	
	public void setSourcePath(String src)
	{
		sourcePath = src;
	}
	
	public void setDestinationPath(String des)
	{
		destinationPath = des;
	}
	
	public String getSourcePath()
	{
		return sourcePath;
	}
	
	public String getDestinationPath()
	{
		return destinationPath;
	}
	
	
	public String getEventName()
	{
		return this.eventName;
	}
	
		
	public void setCollectItem(boolean collect)
	{
		collectItem = collect;
	}
	
	public boolean getCollectItem()
	{
		return collectItem;
	}
	
	public void makeJSONObject()
	{
		try {
			events.put("name", this.eventName);
			events.put("type", "audio");
			attributes.put("filename", this.audioFile);
			
			attributes.put("collectItem", collectItem);
			
			events.put("attributes", attributes);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addActions(String nextEventName)
	{
		action.put(nextEventName);
	}

	public void JSONActions()
	{
		
		try 
		{
			actions.put("audio-finished-playing", action);
			events.put("actions",actions);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileChannel src = null,des=null;
		try {
			  
			  
			  File file = new File(destinationPath+"/audios");
			  			  			  
			  File desFilePath = new File(sourcePath);
			  String desPath = desFilePath.getName();
			  
			  File desFile = new File(destinationPath+"/audios/"+desPath);
			  
			  if(!desFile.exists())
			  {	  
			     if(file.isDirectory())
			     {
				    src = new FileInputStream(sourcePath).getChannel(); 
			        des = new FileOutputStream(destinationPath+"/audios/"+desPath).getChannel();
			     }
			     else
			     {
				    file.mkdir();
				    src = new FileInputStream(sourcePath).getChannel();
				    des = new FileOutputStream(destinationPath+"/audios/"+desPath).getChannel();
			     }
			  }   
			  
			  
			  
			  try {
				  if(des!=null)
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
	
	public JSONObject getAudioEvent()
	{
		return events;
	}

}
